package com.radiance.mixins.vulkan_render_integration;

import net.minecraft.client.texture.atlas.AtlasSource.SpriteRegion;
import net.minecraft.client.texture.atlas.AtlasSource.SpriteRegions;
import net.minecraft.client.texture.atlas.SingleAtlasSource;
import net.minecraft.client.texture.atlas.UnstitchAtlasSource;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(UnstitchAtlasSource.class)
public class UnstitchAtlasSourceMixins {

    @Redirect(method = "load(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/client/texture/atlas/AtlasSource$SpriteRegions;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/atlas/AtlasSource$SpriteRegions;add(Lnet/minecraft/util/Identifier;Lnet/minecraft/client/texture/atlas/AtlasSource$SpriteRegion;)V"))
    public void cancelPBRLoad(SpriteRegions instance, Identifier identifier,
        SpriteRegion spriteRegion) {
        if (identifier.getPath().endsWith("_s") || identifier.getPath().endsWith("_n")
            || identifier.getPath().endsWith("_f")) {
            return;
        }
        instance.add(identifier, spriteRegion);
    }
}
