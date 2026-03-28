package com.radiance.mixins.vulkan_render_integration;

import net.minecraft.client.main.Main;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Main.class)
public class MainMixins {

    @Redirect(method = "main([Ljava/lang/String;)V",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/util/crash/CrashReport;initCrashReport()V"))
    private static void radiance$skipCrashReportWarmup() {
        System.err.println("[Radiance] Skipping CrashReport.initCrashReport warmup to avoid OSHI/WMI startup hang");
    }

    @Redirect(method = "main([Ljava/lang/String;)V",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"))
    private static CrashReport radiance$logCrashCause(Throwable throwable, String message) {
        System.err.println("[Radiance] CrashReport.create stage: " + message);
        throwable.printStackTrace(System.err);
        return CrashReport.create(throwable, message);
    }
}
