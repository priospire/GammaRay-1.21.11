package com.seedxray.client.config;

public enum DisplayMode {
    PREDICTION_ONLY,
    DIAGNOSTIC_ONLY,
    COMBINED;

    public DisplayMode next() {
        DisplayMode[] values = values();
        return values[(ordinal() + 1) % values.length];
    }

    public boolean showsPredictions() {
        return this == PREDICTION_ONLY || this == COMBINED;
    }

    public boolean showsDiagnostics() {
        return this == DIAGNOSTIC_ONLY || this == COMBINED;
    }
}
