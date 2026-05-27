package com.seedxray.client.util;

import com.seedxray.client.mixin.KeyBindingAccessor;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class KeyNameResolver {
    private static final Map<Integer, String> KEY_NAMES = new HashMap<>();
    private static final Map<Integer, String> MOUSE_NAMES = new HashMap<>();

    static {
        for (int key = GLFW.GLFW_KEY_A; key <= GLFW.GLFW_KEY_Z; key++) {
            KEY_NAMES.put(key, Character.toString((char) ('A' + key - GLFW.GLFW_KEY_A)));
        }
        for (int key = GLFW.GLFW_KEY_0; key <= GLFW.GLFW_KEY_9; key++) {
            KEY_NAMES.put(key, Character.toString((char) ('0' + key - GLFW.GLFW_KEY_0)));
        }
        for (int key = GLFW.GLFW_KEY_F1; key <= GLFW.GLFW_KEY_F25; key++) {
            KEY_NAMES.put(key, "F" + (key - GLFW.GLFW_KEY_F1 + 1));
        }
        KEY_NAMES.put(GLFW.GLFW_KEY_SPACE, "Space");
        KEY_NAMES.put(GLFW.GLFW_KEY_TAB, "Tab");
        KEY_NAMES.put(GLFW.GLFW_KEY_ENTER, "Enter");
        KEY_NAMES.put(GLFW.GLFW_KEY_ESCAPE, "Esc");
        KEY_NAMES.put(GLFW.GLFW_KEY_BACKSPACE, "Backspace");
        KEY_NAMES.put(GLFW.GLFW_KEY_DELETE, "Delete");
        KEY_NAMES.put(GLFW.GLFW_KEY_INSERT, "Insert");
        KEY_NAMES.put(GLFW.GLFW_KEY_HOME, "Home");
        KEY_NAMES.put(GLFW.GLFW_KEY_END, "End");
        KEY_NAMES.put(GLFW.GLFW_KEY_PAGE_UP, "Page Up");
        KEY_NAMES.put(GLFW.GLFW_KEY_PAGE_DOWN, "Page Down");
        KEY_NAMES.put(GLFW.GLFW_KEY_UP, "Up");
        KEY_NAMES.put(GLFW.GLFW_KEY_DOWN, "Down");
        KEY_NAMES.put(GLFW.GLFW_KEY_LEFT, "Left");
        KEY_NAMES.put(GLFW.GLFW_KEY_RIGHT, "Right");
        KEY_NAMES.put(GLFW.GLFW_KEY_LEFT_SHIFT, "Left Shift");
        KEY_NAMES.put(GLFW.GLFW_KEY_RIGHT_SHIFT, "Right Shift");
        KEY_NAMES.put(GLFW.GLFW_KEY_LEFT_CONTROL, "Left Ctrl");
        KEY_NAMES.put(GLFW.GLFW_KEY_RIGHT_CONTROL, "Right Ctrl");
        KEY_NAMES.put(GLFW.GLFW_KEY_LEFT_ALT, "Left Alt");
        KEY_NAMES.put(GLFW.GLFW_KEY_RIGHT_ALT, "Right Alt");
        KEY_NAMES.put(GLFW.GLFW_KEY_LEFT_BRACKET, "[");
        KEY_NAMES.put(GLFW.GLFW_KEY_RIGHT_BRACKET, "]");
        KEY_NAMES.put(GLFW.GLFW_KEY_SEMICOLON, ";");
        KEY_NAMES.put(GLFW.GLFW_KEY_APOSTROPHE, "'");
        KEY_NAMES.put(GLFW.GLFW_KEY_COMMA, ",");
        KEY_NAMES.put(GLFW.GLFW_KEY_PERIOD, ".");
        KEY_NAMES.put(GLFW.GLFW_KEY_SLASH, "/");
        KEY_NAMES.put(GLFW.GLFW_KEY_BACKSLASH, "\\");
        KEY_NAMES.put(GLFW.GLFW_KEY_MINUS, "-");
        KEY_NAMES.put(GLFW.GLFW_KEY_EQUAL, "=");
        KEY_NAMES.put(GLFW.GLFW_KEY_GRAVE_ACCENT, "`");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_ENTER, "Num Enter");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_ADD, "Num +");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_SUBTRACT, "Num -");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_MULTIPLY, "Num *");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_DIVIDE, "Num /");
        KEY_NAMES.put(GLFW.GLFW_KEY_KP_DECIMAL, "Num .");
        for (int key = GLFW.GLFW_KEY_KP_0; key <= GLFW.GLFW_KEY_KP_9; key++) {
            KEY_NAMES.put(key, "Num " + (key - GLFW.GLFW_KEY_KP_0));
        }

        MOUSE_NAMES.put(GLFW.GLFW_MOUSE_BUTTON_LEFT, "Mouse Left");
        MOUSE_NAMES.put(GLFW.GLFW_MOUSE_BUTTON_RIGHT, "Mouse Right");
        MOUSE_NAMES.put(GLFW.GLFW_MOUSE_BUTTON_MIDDLE, "Mouse Middle");
        for (int button = GLFW.GLFW_MOUSE_BUTTON_4; button <= GLFW.GLFW_MOUSE_BUTTON_8; button++) {
            MOUSE_NAMES.put(button, "Mouse " + (button + 1));
        }
    }

    private KeyNameResolver() {
    }

    public static String keyBindingName(KeyBinding keyBinding) {
        if (keyBinding == null) {
            return "Unbound";
        }
        return keyName(((KeyBindingAccessor) keyBinding).seedxray$getBoundKey());
    }

    public static String keyName(InputUtil.Key key) {
        if (key == null || key == InputUtil.UNKNOWN_KEY) {
            return "Unbound";
        }
        int code = key.getCode();
        if (key.getCategory() == InputUtil.Type.MOUSE) {
            return MOUSE_NAMES.getOrDefault(code, "Mouse " + (code + 1));
        }
        if (key.getCategory() == InputUtil.Type.SCANCODE) {
            return "Scancode " + code;
        }
        return KEY_NAMES.getOrDefault(code, "Key " + code);
    }
}
