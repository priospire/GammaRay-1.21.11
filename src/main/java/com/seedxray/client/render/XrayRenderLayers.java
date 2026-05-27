package com.seedxray.client.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.seedxray.client.XrayConstants;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public final class XrayRenderLayers {
    private static final RenderPipeline FILLED_NO_DEPTH_PIPELINE = RenderPipelines.register(RenderPipeline
            .builder(RenderPipelines.POSITION_COLOR_SNIPPET)
            .withLocation(Identifier.of(XrayConstants.MOD_ID, "pipeline/xray_filled_no_depth"))
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withDepthWrite(false)
            .withCull(false)
            .withBlend(BlendFunction.TRANSLUCENT)
            .withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS)
            .build());

    private static final RenderPipeline LINES_NO_DEPTH_PIPELINE = RenderPipelines.register(RenderPipeline
            .builder(RenderPipelines.RENDERTYPE_LINES_SNIPPET)
            .withLocation(Identifier.of(XrayConstants.MOD_ID, "pipeline/xray_lines_no_depth"))
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withDepthWrite(false)
            .withCull(false)
            .withBlend(BlendFunction.TRANSLUCENT)
            .withVertexFormat(VertexFormats.POSITION_COLOR_NORMAL, VertexFormat.DrawMode.LINES)
            .build());

    private static final RenderPipeline BLOCK_TEXTURE_NO_DEPTH_PIPELINE = RenderPipelines.register(RenderPipeline
            .builder(RenderPipelines.POSITION_TEX_COLOR_SNIPPET)
            .withLocation(Identifier.of(XrayConstants.MOD_ID, "pipeline/xray_block_texture_no_depth"))
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withDepthWrite(false)
            .withCull(false)
            .withBlend(BlendFunction.TRANSLUCENT)
            .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
            .build());

    private static final RenderLayer FILLED_NO_DEPTH = RenderLayer.of(
            "seed_xray_filled_no_depth",
            RenderSetup.builder(FILLED_NO_DEPTH_PIPELINE)
                    .translucent()
                    .expectedBufferSize(262144)
                    .build()
    );

    private static final RenderLayer LINES_NO_DEPTH = RenderLayer.of(
            "seed_xray_lines_no_depth",
            RenderSetup.builder(LINES_NO_DEPTH_PIPELINE)
                    .translucent()
                    .expectedBufferSize(262144)
                    .build()
    );

    private static final RenderLayer BLOCK_TEXTURE_NO_DEPTH = RenderLayer.of(
            "seed_xray_block_texture_no_depth",
            RenderSetup.builder(BLOCK_TEXTURE_NO_DEPTH_PIPELINE)
                    .texture("Sampler0", SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
                    .translucent()
                    .expectedBufferSize(262144)
                    .build()
    );

    private XrayRenderLayers() {
    }

    public static RenderLayer filledNoDepth() {
        return FILLED_NO_DEPTH;
    }

    public static RenderLayer linesNoDepth() {
        return LINES_NO_DEPTH;
    }

    public static RenderLayer blockTextureNoDepth() {
        return BLOCK_TEXTURE_NO_DEPTH;
    }
}
