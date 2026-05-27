package com.seedxray.client.prediction;

public enum PredictionVisibilityStatus {
    PREDICTED_ONLY,
    PREDICTED_AND_CLIENT_MATCHES,
    PREDICTED_BUT_CLIENT_MASKED,
    CLIENT_ORE_NOT_PREDICTED,
    UNKNOWN_UNLOADED
}
