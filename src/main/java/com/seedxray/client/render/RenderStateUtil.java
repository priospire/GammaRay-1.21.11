package com.seedxray.client.render;

public final class RenderStateUtil {
    private static int lastRenderedPredicted;
    private static int lastRenderedDiagnostic;

    private RenderStateUtil() {
    }

    public static int lastRenderedPredicted() {
        return lastRenderedPredicted;
    }

    public static int lastRenderedDiagnostic() {
        return lastRenderedDiagnostic;
    }

    public static void setLastRenderedCounts(int predicted, int diagnostic) {
        lastRenderedPredicted = predicted;
        lastRenderedDiagnostic = diagnostic;
    }
}
