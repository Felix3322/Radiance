package com.radiance.mixin_related.extensions.vulkan_render_integration;

import org.joml.Vector3f;

public interface ILightMapManagerExt {

    float radiance$getAmbientLightFactor();

    float radiance$getSkyFactor();

    float radiance$getBlockFactor();

    boolean radiance$isUseBrightLightmap();

    Vector3f radiance$getSkyLightColor();

    float radiance$getNightVisionFactor();

    float radiance$getDarknessScale();

    float radiance$getDarkenWorldFactor();

    float radiance$getBrightnessFactor();

    default float neoVoxelRT$getAmbientLightFactor() { return radiance$getAmbientLightFactor(); }
    default float neoVoxelRT$getSkyFactor() { return radiance$getSkyFactor(); }
    default float neoVoxelRT$getBlockFactor() { return radiance$getBlockFactor(); }
    default boolean neoVoxelRT$isUseBrightLightmap() { return radiance$isUseBrightLightmap(); }
    default Vector3f neoVoxelRT$getSkyLightColor() { return radiance$getSkyLightColor(); }
    default float neoVoxelRT$getNightVisionFactor() { return radiance$getNightVisionFactor(); }
    default float neoVoxelRT$getDarknessScale() { return radiance$getDarknessScale(); }
    default float neoVoxelRT$getDarkenWorldFactor() { return radiance$getDarkenWorldFactor(); }
    default float neoVoxelRT$getBrightnessFactor() { return radiance$getBrightnessFactor(); }
}
