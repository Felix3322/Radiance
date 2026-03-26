package com.radiance.client.option;

import com.radiance.client.RadianceClient;
import com.radiance.client.pipeline.Pipeline;
import com.radiance.client.pipeline.Presets;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class Options {

    public static final String OPTION_PROPERTIES = "options.properties";

    public static final String CATEGORY_GAMEPLAY = "options.video.category.gameplay";
    public static final String CATEGORY_WINDOW = "options.video.category.window";
    public static final String CATEGORY_DLSS = "options.video.category.dlss";
    public static final String CATEGORY_RAY_TRACING = "options.video.category.ray_tracing";
    public static final String CATEGORY_UPSCALER = "options.video.category.upscaler";
    public static final String CATEGORY_TERRAIN = "options.video.category.terrain";
    public static final String CATEGORY_PIPELINE = "options.video.category.pipeline";

    public static final String DLSS_MODE_PERFORMANCE_TOOLTIP = "options.video.dlss_mode.performance.tooltip";
    public static final String DLSS_MODE_BALANCED_TOOLTIP = "options.video.dlss_mode.balanced.tooltip";
    public static final String DLSS_MODE_QUALITY_TOOLTIP = "options.video.dlss_mode.quality.tooltip";
    public static final String DLSS_MODE_DLAA_TOOLTIP = "options.video.dlss_mode.dlaa.tooltip";

    public static final String DLSS_MODE_PERFORMANCE = "options.video.dlss_mode.performance";
    public static final String DLSS_MODE_BALANCED = "options.video.dlss_mode.balanced";
    public static final String DLSS_MODE_QUALITY = "options.video.dlss_mode.quality";
    public static final String DLSS_MODE_DLAA = "options.video.dlss_mode.dlaa";

    public static final String DLSS_MODE_KEY = "options.video.dlss_mode";
    public static final String QUALITY_LEVEL_KEY = "options.video.quality_level";
    public static final String UPSCALER_TYPE_KEY = "options.video.upscaler_type";
    public static final String UPSCALER_QUALITY_KEY = "options.video.upscaler_quality";
    public static final String DENOISER_MODE_KEY = "options.video.denoiser_mode";
    public static final String HDR_OUTPUT_KEY = "options.video.hdr_output";
    public static final String RAY_BOUNCES_KEY = "options.video.ray_bounces";
    public static final String CHUNK_BUILDING_BATCH_SIZE_KEY = "options.video.chunk_building_batch_size";
    public static final String CHUNK_BUILDING_TOTAL_BATCHES_KEY = "options.video.chunk_building_total_batches";
    public static final String PIPELINE_SETUP_KEY = "options.video.pipeline_setup";

    public static final String UPSCALER_TYPE_NATIVE = "options.video.upscaler_type.native";
    public static final String UPSCALER_TYPE_FSR3 = "options.video.upscaler_type.fsr3";

    public static final String UPSCALER_QUALITY_NATIVEAA = "options.video.upscaler_quality.nativeaa";
    public static final String UPSCALER_QUALITY_QUALITY = "options.video.upscaler_quality.quality";
    public static final String UPSCALER_QUALITY_BALANCED = "options.video.upscaler_quality.balanced";
    public static final String UPSCALER_QUALITY_PERFORMANCE = "options.video.upscaler_quality.performance";
    public static final String QUALITY_LEVEL_FLUENT = "options.video.quality_level.fluent";
    public static final String QUALITY_LEVEL_PERFORMANCE = "options.video.quality_level.performance";
    public static final String QUALITY_LEVEL_BALANCED = "options.video.quality_level.balanced";
    public static final String QUALITY_LEVEL_QUALITY = "options.video.quality_level.quality";
    public static final String QUALITY_LEVEL_ULTRA = "options.video.quality_level.ultra";
    public static final String QUALITY_LEVEL_EXTREME = "options.video.quality_level.extreme";
    public static final String DENOISER_MODE_DLSS = "options.video.denoiser_mode.dlss";
    public static final String DENOISER_MODE_SVGF = "options.video.denoiser_mode.svgf";
    public static final String DENOISER_MODE_NRD = "options.video.denoiser_mode.nrd";
    public static final String DENOISER_MODE_TEMPORAL = "options.video.denoiser_mode.temporal";
    public static final String HDR_OUTPUT = "options.video.hdr_output";

    private static final String RAY_TRACING_MODULE = "render_pipeline.module.ray_tracing.name";
    private static final String DLSS_MODULE = "render_pipeline.module.dlss.name";
    private static final String FSR3_MODULE = "render_pipeline.module.fsr_upscaler.name";
    private static final String XESS_MODULE = "render_pipeline.module.xess_sr.name";
    private static final String RT_TERRAIN_MESHING_MODE =
        "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode";
    private static final String RT_GREEDY_MERGE_MAX_SPAN =
        "render_pipeline.module.ray_tracing.attribute.greedy_merge_max_span";
    private static final String RT_FAR_FIELD_GEOMETRY_MODE =
        "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode";
    private static final String RT_FAR_FIELD_START_DISTANCE_CHUNKS =
        "render_pipeline.module.ray_tracing.attribute.far_field_start_distance_chunks";
    private static final String RT_FAR_FIELD_MATERIAL_MODE =
        "render_pipeline.module.ray_tracing.attribute.far_field_material_mode";
    private static final String RT_GLASS_PATH_MODE =
        "render_pipeline.module.ray_tracing.attribute.glass_path_mode";
    private static final String RT_FOLIAGE_PATH_MODE =
        "render_pipeline.module.ray_tracing.attribute.foliage_path_mode";
    private static final String RT_DECORATION_PATH_MODE =
        "render_pipeline.module.ray_tracing.attribute.decoration_path_mode";
    private static final String RT_REFLECTION_RAY_MATERIAL_MODE =
        "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode";
    private static final String RT_DIFFUSE_GI_MODE =
        "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode";
    private static final String RT_TERRAIN_UPDATE_INTERVAL_FRAMES =
        "render_pipeline.module.ray_tracing.attribute.terrain_update_interval_frames";
    private static final String RT_ENTITY_UPDATE_INTERVAL_FRAMES =
        "render_pipeline.module.ray_tracing.attribute.entity_update_interval_frames";
    private static final String RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES =
        "render_pipeline.module.ray_tracing.attribute.block_entity_update_interval_frames";
    private static final String RT_PARTICLE_UPDATE_INTERVAL_FRAMES =
        "render_pipeline.module.ray_tracing.attribute.particle_update_interval_frames";
    private static final String RT_PARTICLE_CRIT_GLOW =
        "render_pipeline.module.ray_tracing.attribute.particle_crit_glow";
    private static final String RT_PARTICLE_DEATH_SMOKE_GLOW =
        "render_pipeline.module.ray_tracing.attribute.particle_death_smoke_glow";
    private static final String RT_PARTICLE_CRIT_GLOW_STRENGTH =
        "render_pipeline.module.ray_tracing.attribute.particle_crit_glow_strength";
    private static final String RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH =
        "render_pipeline.module.ray_tracing.attribute.particle_death_smoke_glow_strength";
    private static final String RT_NUM_RAY_BOUNCES =
        "render_pipeline.module.ray_tracing.attribute.num_ray_bounces";
    private static final String RT_PBR_SAMPLING_MODE =
        "render_pipeline.module.ray_tracing.attribute.pbr_sampling_mode";
    private static final String RT_USE_SHARC =
        "render_pipeline.module.ray_tracing.attribute.use_sharc";
    private static final String RT_BASIC_RADIANCE =
        "render_pipeline.module.ray_tracing.attribute.basic_radiance";
    private static final String RT_DIRECT_LIGHT_STRENGTH =
        "render_pipeline.module.ray_tracing.attribute.direct_light_strength";
    private static final String RT_INDIRECT_LIGHT_STRENGTH =
        "render_pipeline.module.ray_tracing.attribute.indirect_light_strength";
    private static final String FSR3_QUALITY_MODE =
        "render_pipeline.module.fsr_upscaler.attribute.quality_mode";
    private static final String FSR3_SHARPNESS =
        "render_pipeline.module.fsr_upscaler.attribute.sharpness";
    private static final String XESS_QUALITY_MODE =
        "render_pipeline.module.xess_sr.attribute.quality_mode";
    private static final String DLSS_MODE_ATTRIBUTE =
        "render_pipeline.module.dlss.attribute.mode";
    private static final String NRD_MODULE = "render_pipeline.module.nrd.name";
    private static final String NRD_MAX_ACCUMULATED_FRAME_NUM =
        "render_pipeline.module.nrd.attribute.max_accumulated_frame_num";
    private static final String NRD_MAX_FAST_ACCUMULATED_FRAME_NUM =
        "render_pipeline.module.nrd.attribute.max_fast_accumulated_frame_num";
    private static final String NRD_MAX_STABILIZED_FRAME_NUM =
        "render_pipeline.module.nrd.attribute.max_stabilized_frame_num";
    private static final String NRD_DIFFUSE_PREPASS_BLUR_RADIUS =
        "render_pipeline.module.nrd.attribute.diffuse_prepass_blur_radius";
    private static final String NRD_SPECULAR_PREPASS_BLUR_RADIUS =
        "render_pipeline.module.nrd.attribute.specular_prepass_blur_radius";
    private static final String NRD_MAX_BLUR_RADIUS =
        "render_pipeline.module.nrd.attribute.max_blur_radius";
    private static final String NRD_HIT_DISTANCE_RECONSTRUCTION_MODE =
        "render_pipeline.module.nrd.attribute.hit_distance_reconstruction_mode";
    private static final String NRD_ENABLE_ANTI_FIREFLY =
        "render_pipeline.module.nrd.attribute.enable_anti_firefly";
    private static final String TONE_MAPPING_MODULE = "render_pipeline.module.tone_mapping.name";
    private static final String TM_METHOD =
        "render_pipeline.module.tone_mapping.attribute.method";
    private static final String TM_ENABLE_AUTO_EXPOSURE =
        "render_pipeline.module.tone_mapping.attribute.enable_auto_exposure";
    private static final String TM_EXPOSURE_METERING_MODE =
        "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode";
    private static final String TM_SATURATION =
        "render_pipeline.module.tone_mapping.attribute.saturation";
    private static final String TM_WHITE_POINT =
        "render_pipeline.module.tone_mapping.attribute.white_point";
    public static int maxFps = 260;
    public static int inactivityFpsLimit = 260;
    public static boolean vsync = true;
    public static int qualityLevel = QualityLevel.BALANCED.getId();
    public static int dlssMode = 1;
    public static int upscalerType = 1;
    public static int upscalerQuality = 1;
    public static int denoiserMode = 1;
    public static boolean hdrOutput = false;
    public static int rayBounces = 4;
    public static int chunkBuildingBatchSize = 14;
    public static int chunkBuildingTotalBatches = 16;

    public static void readOptions() {
        Path path = RadianceClient.radianceDir.resolve(OPTION_PROPERTIES);
        if (!Files.exists(path)) {
//            System.out.println("Generating default options...");
            overwriteConfig();
            return;
        }

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            props.load(in);

            setMaxFps(Integer.parseInt(props.getProperty("maxFps", String.valueOf(maxFps))), false);
            setInactivityFpsLimit(Integer.parseInt(
                    props.getProperty("inactivityFpsLimit", String.valueOf(inactivityFpsLimit))),
                false);
            setVsync(Boolean.parseBoolean(props.getProperty("vsync", String.valueOf(vsync))),
                false);
            setHdrOutput(Boolean.parseBoolean(props.getProperty("hdrOutput",
                String.valueOf(hdrOutput))), false);
            qualityLevel = Integer.parseInt(
                props.getProperty("qualityLevel", String.valueOf(qualityLevel)));
            setRayBounces(Integer.parseInt(props.getProperty("rayBounces",
                String.valueOf(rayBounces))), false);
            setChunkBuildingBatchSize(Integer.parseInt(props.getProperty("chunkBuildingBatchSize",
                    String.valueOf(chunkBuildingBatchSize))),
                false);
            setChunkBuildingTotalBatches(
                Integer.parseInt(props.getProperty("chunkBuildingTotalBatches",
                    String.valueOf(chunkBuildingTotalBatches))), false);

            overwriteConfig();
//            System.out.println("Successfully read options: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void overwriteConfig() {
        Path path = RadianceClient.radianceDir.resolve(OPTION_PROPERTIES);
        Properties props = new Properties();
        props.setProperty("maxFps", String.valueOf(maxFps));
        props.setProperty("inactivityFpsLimit", String.valueOf(inactivityFpsLimit));
        props.setProperty("vsync", String.valueOf(vsync));
        props.setProperty("hdrOutput", String.valueOf(hdrOutput));
        props.setProperty("qualityLevel", String.valueOf(qualityLevel));
        props.setProperty("dlssMode", String.valueOf(dlssMode));
        props.setProperty("upscalerType", String.valueOf(upscalerType));
        props.setProperty("upscalerQuality", String.valueOf(upscalerQuality));
        props.setProperty("denoiserMode", String.valueOf(denoiserMode));
        props.setProperty("rayBounces", String.valueOf(rayBounces));
        props.setProperty("chunkBuildingBatchSize", String.valueOf(chunkBuildingBatchSize));
        props.setProperty("chunkBuildingTotalBatches", String.valueOf(chunkBuildingTotalBatches));

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (OutputStream out = Files.newOutputStream(path)) {
            props.store(out, "Options");
//            System.out.println("Options written to: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public native static void nativeSetMaxFps(int maxFps, boolean write);

    public static void setMaxFps(int maxFps, boolean write) {
        Options.maxFps = maxFps;
        nativeSetMaxFps(maxFps, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetInactivityFpsLimit(int inactivityFpsLimit, boolean write);

    public static void setInactivityFpsLimit(int inactivityFpsLimit, boolean write) {
        Options.inactivityFpsLimit = inactivityFpsLimit;
        nativeSetInactivityFpsLimit(inactivityFpsLimit, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetVsync(boolean vsync, boolean write);

    public static void setVsync(boolean vsync, boolean write) {
        Options.vsync = vsync;
        nativeSetVsync(vsync, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetChunkBuildingBatchSize(int chunkBuildingBatchSize,
        boolean write);

    public static void setChunkBuildingBatchSize(int chunkBuildingBatchSize, boolean write) {
        Options.chunkBuildingBatchSize = chunkBuildingBatchSize;
        nativeSetChunkBuildingBatchSize(chunkBuildingBatchSize, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetChunkBuildingTotalBatches(int chunkBuildingTotalBatches,
        boolean write);

    public static void setChunkBuildingTotalBatches(int chunkBuildingTotalBatches, boolean write) {
        Options.chunkBuildingTotalBatches = chunkBuildingTotalBatches;
        nativeSetChunkBuildingTotalBatches(chunkBuildingTotalBatches, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetHdrOutput(boolean hdrOutput, boolean write);

    public static void setHdrOutput(boolean hdrOutput, boolean write) {
        Options.hdrOutput = hdrOutput;
        nativeSetHdrOutput(hdrOutput, write);
        if (write) {
            overwriteConfig();
        }
    }

    public native static void nativeSetRayBounces(int rayBounces, boolean write);

    public static void setRayBounces(int rayBounces, boolean write) {
        Options.rayBounces = rayBounces;
        nativeSetRayBounces(rayBounces, write);
        if (write) {
            overwriteConfig();
        }
    }

    public static void applyQualityProfile(boolean rebuildPipeline) {
        applyQualityProfile(QualityLevel.fromId(qualityLevel), rebuildPipeline, false);
    }

    public static void setQualityLevel(QualityLevel qualityLevel, boolean write) {
        applyQualityProfile(qualityLevel, true, write);
    }

    private static void applyQualityProfile(QualityLevel level, boolean rebuildPipeline,
        boolean writeOptions) {
        QualityLevel resolvedLevel = level == null ? QualityLevel.BALANCED : level;
        Options.qualityLevel = resolvedLevel.getId();

        boolean pipelineChanged = false;
        if (Pipeline.INSTANCE.getModuleEntries() != null) {
            pipelineChanged |= preparePresetForQuality(resolvedLevel);
            if (!Pipeline.INSTANCE.getModules().isEmpty()) {
                pipelineChanged |= applyPipelineQualityProfile(resolvedLevel);
            }
        }

        if (writeOptions) {
            overwriteConfig();
        }
        if (pipelineChanged && rebuildPipeline) {
            Pipeline.savePipeline();
            Pipeline.build();
        }
    }

    private static boolean applyPipelineQualityProfile(QualityLevel level) {
        return switch (level) {
            case FLUENT -> applyFluentQualityProfile();
            case PERFORMANCE -> applyPerformanceQualityProfile();
            case BALANCED -> applyBalancedQualityProfile();
            case HIGH -> applyHighQualityProfile();
            case ULTRA -> applyUltraQualityProfile();
            case EXTREME -> applyExtremeQualityProfile();
        };
    }

    private static boolean preparePresetForQuality(QualityLevel level) {
        String presetName = selectPresetForQuality(level);
        if (presetName == null) {
            return false;
        }

        String activePreset = Pipeline.processPresetName(Pipeline.getActivePreset());
        if (Pipeline.getPipelineMode() == Pipeline.PipelineMode.PRESET
            && Objects.equals(activePreset, presetName)
            && !Pipeline.INSTANCE.getModules().isEmpty()) {
            return false;
        }

        Pipeline.preparePresetMode(presetName);
        return true;
    }

    private static String selectPresetForQuality(QualityLevel level) {
        if (Pipeline.isPresetAvailable(Presets.RT_DLSSRR.key)) {
            return Presets.RT_DLSSRR.key;
        }

        return switch (level) {
            case HIGH, ULTRA, EXTREME -> firstAvailablePreset(
                Presets.RT_NRD_XESS.key,
                Presets.RT_NRD_FSR.key,
                Presets.RT_NRD.key);
            default -> firstAvailablePreset(
                Presets.RT_NRD_FSR.key,
                Presets.RT_NRD_XESS.key,
                Presets.RT_NRD.key);
        };
    }

    private static String firstAvailablePreset(String... presetNames) {
        for (String presetName : presetNames) {
            if (Pipeline.isPresetAvailable(presetName)) {
                return presetName;
            }
        }
        return null;
    }

    private static boolean applyFluentQualityProfile() {
        setRayBounces(2, false);
        dlssMode = 0;
        upscalerQuality = 3;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "24");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.simplified_shell");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "32");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.flat_surface");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.water_glass_metal");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.low_cost_hybrid");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "4");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "0.42");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.20");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "2");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "5.8");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "16.5");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "44");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "2");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "44");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "34.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "56.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "116.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.performance");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.60");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.performance");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.performance");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.global");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "0.97");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "24.0");
        return changed;
    }

    private static boolean applyPerformanceQualityProfile() {
        setRayBounces(3, false);
        dlssMode = 0;
        upscalerQuality = 2;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "28");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.simplified_shell");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "40");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.flat_surface");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.water_glass_metal");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.low_cost_hybrid");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "3");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "0.50");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.26");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "3");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "5.55");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "17.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "54");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "3");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "58");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "30.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "50.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "102.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.balanced");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.66");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.balanced");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.performance");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.global");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "0.99");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "26.0");
        return changed;
    }

    private static boolean applyBalancedQualityProfile() {
        setRayBounces(4, false);
        dlssMode = 1;
        upscalerQuality = 1;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "30");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.exact_chunks");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "48");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.full_pbr");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.water_glass_metal");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.low_cost_hybrid");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "0.60");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.34");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "4");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "5.2");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "17.6");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "64");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "4");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "68");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "26.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "42.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "92.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.quality");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.74");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.quality");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.balanced");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.center");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "1.0");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "29.0");
        return changed;
    }

    private static boolean applyHighQualityProfile() {
        setRayBounces(6, false);
        dlssMode = 2;
        upscalerQuality = 0;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "34");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.exact_chunks");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "64");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.full_pbr");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.all_materials");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.full_ray_tracing");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "2");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "0.72");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.44");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "6");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "4.95");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "18.6");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "78");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "5");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "82");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "18.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "30.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "72.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.native");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.80");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.ultra_quality");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.quality");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.center");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "1.02");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "32.0");
        return changed;
    }

    private static boolean applyUltraQualityProfile() {
        setRayBounces(8, false);
        dlssMode = 2;
        upscalerQuality = 0;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "38");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.exact_chunks");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "72");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.full_pbr");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.all_materials");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.full_ray_tracing");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "0.82");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.54");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "8");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "4.8");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "19.5");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "90");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "6");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "94");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "14.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "24.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "60.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.native");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.84");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.ultra_quality_plus");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.quality");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.center");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "1.03");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "34.0");
        return changed;
    }

    private static boolean applyExtremeQualityProfile() {
        setRayBounces(16, false);
        dlssMode = 3;
        upscalerQuality = 0;
        denoiserMode = 2;

        boolean changed = false;
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_MESHING_MODE,
            "render_pipeline.module.ray_tracing.attribute.terrain_meshing_mode.coplanar_merge");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GREEDY_MERGE_MAX_SPAN, "42");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_GEOMETRY_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_geometry_mode.exact_chunks");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_START_DISTANCE_CHUNKS, "96");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FAR_FIELD_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.far_field_material_mode.full_pbr");
        changed |= setAttr(RAY_TRACING_MODULE, RT_GLASS_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_FOLIAGE_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DECORATION_PATH_MODE,
            "render_pipeline.module.ray_tracing.attribute.geometry_path_mode.blas");
        changed |= setAttr(RAY_TRACING_MODULE, RT_REFLECTION_RAY_MATERIAL_MODE,
            "render_pipeline.module.ray_tracing.attribute.reflection_ray_material_mode.all_materials");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIFFUSE_GI_MODE,
            "render_pipeline.module.ray_tracing.attribute.diffuse_gi_mode.full_ray_tracing");
        changed |= setAttr(RAY_TRACING_MODULE, RT_TERRAIN_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BLOCK_ENTITY_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_UPDATE_INTERVAL_FRAMES, "1");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_CRIT_GLOW_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PARTICLE_DEATH_SMOKE_GLOW_STRENGTH, "0.72");
        changed |= setAttr(RAY_TRACING_MODULE, RT_NUM_RAY_BOUNCES, "16");
        changed |= setAttr(RAY_TRACING_MODULE, RT_PBR_SAMPLING_MODE,
            "render_pipeline.module.ray_tracing.attribute.pbr_sampling.bilinear");
        changed |= setAttr(RAY_TRACING_MODULE, RT_USE_SHARC, "render_pipeline.true");
        changed |= setAttr(RAY_TRACING_MODULE, RT_BASIC_RADIANCE, "4.6");
        changed |= setAttr(RAY_TRACING_MODULE, RT_DIRECT_LIGHT_STRENGTH, "1.0");
        changed |= setAttr(RAY_TRACING_MODULE, RT_INDIRECT_LIGHT_STRENGTH, "21.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_ACCUMULATED_FRAME_NUM, "112");
        changed |= setAttr(NRD_MODULE, NRD_MAX_FAST_ACCUMULATED_FRAME_NUM, "8");
        changed |= setAttr(NRD_MODULE, NRD_MAX_STABILIZED_FRAME_NUM, "116");
        changed |= setAttr(NRD_MODULE, NRD_DIFFUSE_PREPASS_BLUR_RADIUS, "10.0");
        changed |= setAttr(NRD_MODULE, NRD_SPECULAR_PREPASS_BLUR_RADIUS, "18.0");
        changed |= setAttr(NRD_MODULE, NRD_MAX_BLUR_RADIUS, "44.0");
        changed |= setAttr(NRD_MODULE, NRD_HIT_DISTANCE_RECONSTRUCTION_MODE, "5x5");
        changed |= setAttr(NRD_MODULE, NRD_ENABLE_ANTI_FIREFLY, "render_pipeline.true");
        changed |= setAttr(FSR3_MODULE, FSR3_QUALITY_MODE,
            "render_pipeline.module.fsr_upscaler.attribute.quality_mode.native");
        changed |= setAttr(FSR3_MODULE, FSR3_SHARPNESS, "0.88");
        changed |= setAttr(XESS_MODULE, XESS_QUALITY_MODE,
            "render_pipeline.module.xess_sr.attribute.quality_mode.native");
        changed |= setAttr(DLSS_MODULE, DLSS_MODE_ATTRIBUTE,
            "render_pipeline.module.dlss.attribute.mode.dlaa");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_METHOD,
            "render_pipeline.module.tone_mapping.attribute.method.pbr_neutral");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_ENABLE_AUTO_EXPOSURE, "render_pipeline.true");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_EXPOSURE_METERING_MODE,
            "render_pipeline.module.tone_mapping.attribute.exposure_metering_mode.center");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_SATURATION, "1.04");
        changed |= setAttr(TONE_MAPPING_MODULE, TM_WHITE_POINT, "36.0");
        return changed;
    }

    private static boolean setAttr(String moduleName, String attributeName, String value) {
        return Pipeline.setModuleAttributeValue(moduleName, attributeName, value);
    }
}
