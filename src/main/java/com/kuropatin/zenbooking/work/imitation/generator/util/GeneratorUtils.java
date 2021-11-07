package com.kuropatin.zenbooking.work.imitation.generator.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneratorUtils {

    public static int randomInt(final int minInclusive, final int maxInclusive) {
        return RandomUtils.nextInt(minInclusive, maxInclusive + 1);
    }
}