package com.radiance.mixins.vulkan_render_integration;

import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrashReport.class)
public class CrashReportMixins {

    @Inject(method = "initCrashReport", at = @At("HEAD"), cancellable = true)
    private static void radiance$skipCrashReportWarmup(CallbackInfo ci) {
        System.err.println("[Radiance] Skipping CrashReport.initCrashReport warmup to avoid OSHI/WMI startup hang");
        ci.cancel();
    }
}
