package com.radiance.mixin_related.extensions.vulkan_render_integration;

import net.minecraft.client.render.chunk.BlockBufferAllocatorStorage;
import net.minecraft.client.render.chunk.SectionBuilder;
import net.minecraft.client.world.ClientWorld;

public interface IChunkBuilderExt {

    SectionBuilder radiance$getSectionBuilder();

    ClientWorld radiance$getWorld();

    BlockBufferAllocatorStorage radiance$getBuffers();

    default SectionBuilder neoVoxelRT$getSectionBuilder() {
        return radiance$getSectionBuilder();
    }

    default ClientWorld neoVoxelRT$getWorld() {
        return radiance$getWorld();
    }

    default BlockBufferAllocatorStorage neoVoxelRT$getBuffers() {
        return radiance$getBuffers();
    }
}
