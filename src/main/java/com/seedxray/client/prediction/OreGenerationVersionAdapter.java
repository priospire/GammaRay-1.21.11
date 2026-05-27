package com.seedxray.client.prediction;

public final class OreGenerationVersionAdapter {
    public static final String MINECRAFT_VERSION = "1.21.11";
    public static final String ORE_PREDICTION_ACCURACY =
            "Vanilla 1.21.11 ore adapter: uses placed-feature population/decorator seeds, " +
            "height providers, ore/scattered_ore placement, local terrain sampling, and replacement checks. " +
            "Multiplayer client block states are not used to veto configured-seed predictions because PaperMC anti-xray may mask or spoof those states.";

    private OreGenerationVersionAdapter() {
    }
}
