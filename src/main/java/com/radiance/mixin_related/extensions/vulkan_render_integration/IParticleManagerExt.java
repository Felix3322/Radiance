package com.radiance.mixin_related.extensions.vulkan_render_integration;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;

public interface IParticleManagerExt {

    List<ParticleTextureSheet> radiance$getTextureSheets();

    Map<ParticleTextureSheet, Queue<Particle>> radiance$getParticles();

    default List<ParticleTextureSheet> neoVoxelRT$getTextureSheets() {
        return radiance$getTextureSheets();
    }

    default Map<ParticleTextureSheet, Queue<Particle>> neoVoxelRT$getParticles() {
        return radiance$getParticles();
    }
}
