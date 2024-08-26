package com.example.bootstrapposbackend.util;

import java.util.UUID;

public class UtilProcess {
    public static String generateId(){
        return UUID.randomUUID().toString();
    }

}
