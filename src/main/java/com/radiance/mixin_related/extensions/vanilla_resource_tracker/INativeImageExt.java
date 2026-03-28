package com.radiance.mixin_related.extensions.vanilla_resource_tracker;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

public interface INativeImageExt {

    int neoVoxelRT$getTargetID();

    void neoVoxelRT$setTargetID(int id);

    Identifier neoVoxelRT$getIdentifier();

    void neoVoxelRT$setIdentifier(Identifier id);

    NativeImage neoVoxelRT$getSpecularNativeImage();

    void neoVoxelRT$setSpecularNativeImage(NativeImage image);

    NativeImage neoVoxelRT$getNormalNativeImage();

    void neoVoxelRT$setNormalNativeImage(NativeImage image);

    NativeImage neoVoxelRT$getFlagNativeImage();

    void neoVoxelRT$setFlagNativeImage(NativeImage image);

    int neoVoxelRT$getSpecularUploadedLevelsMask();

    void neoVoxelRT$setSpecularUploadedLevelsMask(int uploadedLevelsMask);

    int neoVoxelRT$getNormalUploadedLevelsMask();

    void neoVoxelRT$setNormalUploadedLevelsMask(int uploadedLevelsMask);

    int neoVoxelRT$getFlagUploadedLevelsMask();

    void neoVoxelRT$setFlagUploadedLevelsMask(int uploadedLevelsMask);

    default int radiance$getTargetID() {
        return neoVoxelRT$getTargetID();
    }

    default void radiance$setTargetID(int id) {
        neoVoxelRT$setTargetID(id);
    }

    default Identifier radiance$getIdentifier() {
        return neoVoxelRT$getIdentifier();
    }

    default void radiance$setIdentifier(Identifier id) {
        neoVoxelRT$setIdentifier(id);
    }

    default NativeImage radiance$getSpecularNativeImage() {
        return neoVoxelRT$getSpecularNativeImage();
    }

    default void radiance$setSpecularNativeImage(NativeImage image) {
        neoVoxelRT$setSpecularNativeImage(image);
    }

    default NativeImage radiance$getNormalNativeImage() {
        return neoVoxelRT$getNormalNativeImage();
    }

    default void radiance$setNormalNativeImage(NativeImage image) {
        neoVoxelRT$setNormalNativeImage(image);
    }

    default NativeImage radiance$getFlagNativeImage() {
        return neoVoxelRT$getFlagNativeImage();
    }

    default void radiance$setFlagNativeImage(NativeImage image) {
        neoVoxelRT$setFlagNativeImage(image);
    }
}
