package com.poker.alkis.helper;

import net.datafaker.Faker;

public class Utils {
    
    private static final Faker FAKER = new Faker();
    
    public static Faker faker() {
        return FAKER;
    }
    
}
