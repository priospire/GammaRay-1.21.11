package com.seedxray.client.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class MathUtil {
    private MathUtil() {
    }

    public static double squaredDistanceToCenter(Vec3d from, BlockPos pos) {
        double dx = from.x - (pos.getX() + 0.5D);
        double dy = from.y - (pos.getY() + 0.5D);
        double dz = from.z - (pos.getZ() + 0.5D);
        return dx * dx + dy * dy + dz * dz;
    }
}
