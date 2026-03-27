package com.radiance.mixins.vanilla_resource_tracker;

import com.radiance.client.proxy.vulkan.TextureProxy;
import com.radiance.client.texture.IdentifierInputStream;
import com.radiance.mixin_related.extensions.vanilla_resource_tracker.INativeImageExt;
import java.io.InputStream;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NativeImage.class)
public abstract class NativeImageMixins implements INativeImageExt {

    @Unique
    private static final int RADIANCE_ALPHA_FULLY_OPAQUE = 0;
    @Unique
    private static final int RADIANCE_ALPHA_FULLY_TRANSPARENT = 1;
    @Unique
    private static final int RADIANCE_ALPHA_MIXED = 2;

    @Unique
    private int targetID = -1;

    @Unique
    private Identifier identifier = null;

    @Unique
    private NativeImage specularImage = null;

    @Unique
    private NativeImage normalImage = null;

    @Unique
    private NativeImage flagImage = null;

    @Unique
    private int specularUploadedLevelsMask = 0;

    @Unique
    private int normalUploadedLevelsMask = 0;

    @Unique
    private int flagUploadedLevelsMask = 0;

    @Inject(method = "read(Lnet/minecraft/client/texture/NativeImage$Format;Ljava/io/InputStream;)"
        +
        "Lnet/minecraft/client/texture/NativeImage;", at = @At(value = "RETURN"), cancellable = true)
    private static void readIdentifier(NativeImage.Format format, InputStream stream,
        CallbackInfoReturnable<NativeImage> cir) {
        NativeImage nativeImage = cir.getReturnValue();

        if (stream instanceof IdentifierInputStream) {
            Identifier identifier = ((IdentifierInputStream) stream).getResourceId();
            ((INativeImageExt) (Object) nativeImage).radiance$setIdentifier(identifier);
            cir.setReturnValue(nativeImage);
        } else {
            cir.setReturnValue(nativeImage);
        }
    }

    @Override
    public int radiance$getTargetID() {
        return targetID;
    }

    @Override
    public void radiance$setTargetID(int id) {
        this.targetID = id;
        if (id > 0) {
            TextureProxy.setTextureAlphaClass(id, radiance$detectAlphaClass());
        }
    }

    @Override
    public Identifier radiance$getIdentifier() {
        return identifier;
    }

    @Override
    public void radiance$setIdentifier(Identifier id) {
        this.identifier = id;
    }

    @Override
    public NativeImage radiance$getSpecularNativeImage() {
        return specularImage;
    }

    @Override
    public void radiance$setSpecularNativeImage(NativeImage image) {
        this.specularImage = image;
        this.specularUploadedLevelsMask = 0;
    }

    @Override
    public NativeImage radiance$getNormalNativeImage() {
        return normalImage;
    }

    @Override
    public void radiance$setNormalNativeImage(NativeImage image) {
        this.normalImage = image;
        this.normalUploadedLevelsMask = 0;
    }

    @Override
    public NativeImage radiance$getFlagNativeImage() {
        return flagImage;
    }

    @Override
    public void radiance$setFlagNativeImage(NativeImage image) {
        this.flagImage = image;
        this.flagUploadedLevelsMask = 0;
    }

    @Override
    public int neoVoxelRT$getSpecularUploadedLevelsMask() {
        return specularUploadedLevelsMask;
    }

    @Override
    public void neoVoxelRT$setSpecularUploadedLevelsMask(int uploadedLevelsMask) {
        this.specularUploadedLevelsMask = uploadedLevelsMask;
    }

    @Override
    public int neoVoxelRT$getNormalUploadedLevelsMask() {
        return normalUploadedLevelsMask;
    }

    @Override
    public void neoVoxelRT$setNormalUploadedLevelsMask(int uploadedLevelsMask) {
        this.normalUploadedLevelsMask = uploadedLevelsMask;
    }

    @Override
    public int neoVoxelRT$getFlagUploadedLevelsMask() {
        return flagUploadedLevelsMask;
    }

    @Override
    public void neoVoxelRT$setFlagUploadedLevelsMask(int uploadedLevelsMask) {
        this.flagUploadedLevelsMask = uploadedLevelsMask;
    }

    @Unique
    private int radiance$detectAlphaClass() {
        NativeImage self = (NativeImage) (Object) this;
        int width = self.getWidth();
        int height = self.getHeight();
        boolean sawOpaque = false;
        boolean sawTransparent = false;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int alpha = (self.getColorArgb(x, y) >>> 24) & 0xFF;
                if (alpha >= 250) {
                    sawOpaque = true;
                } else if (alpha <= 5) {
                    sawTransparent = true;
                } else {
                    return RADIANCE_ALPHA_MIXED;
                }

                if (sawOpaque && sawTransparent) {
                    return RADIANCE_ALPHA_MIXED;
                }
            }
        }

        if (sawOpaque) {
            return RADIANCE_ALPHA_FULLY_OPAQUE;
        }
        if (sawTransparent) {
            return RADIANCE_ALPHA_FULLY_TRANSPARENT;
        }
        return RADIANCE_ALPHA_MIXED;
    }
}
