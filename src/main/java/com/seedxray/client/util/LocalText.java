package com.seedxray.client.util;

import net.minecraft.text.Text;

public final class LocalText {
    private LocalText() {
    }

    public static Text text(String value) {
        return Text.literal(value);
    }
}
