package com.example.baitapproduct.util;

import java.util.Random;

public class NumberRandom {
    public static int generate(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
