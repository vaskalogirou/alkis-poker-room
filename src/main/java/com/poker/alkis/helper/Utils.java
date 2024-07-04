package com.poker.alkis.helper;

import net.datafaker.Faker;

public class Utils {
    
    private static final Faker FAKER = Faker.instance();
    
    public static Faker faker() {
        return FAKER;
    }
    
}
