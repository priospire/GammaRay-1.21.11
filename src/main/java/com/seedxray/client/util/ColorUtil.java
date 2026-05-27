package com.seedxray.client.util;

public final class ColorUtil {
    private ColorUtil() {
    }

    public static float red(int argb) {
        return ((argb >> 16) & 0xFF) / 255.0F;
    }

    public static float green(int argb) {
        return ((argb >> 8) & 0xFF) / 255.0F;
    }

    public static float blue(int argb) {
        return (argb & 0xFF) / 255.0F;
    }

    public static float alpha(int argb) {
        return ((argb >>> 24) & 0xFF) / 255.0F;
    }

    public static int withAlpha(int argb, int alpha) {
        return (argb & 0x00FFFFFF) | ((alpha & 0xFF) << 24);
    }
}
