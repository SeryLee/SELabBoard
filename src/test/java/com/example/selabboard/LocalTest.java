package com.example.selabboard;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LocalTest {

    @Test
    void javaUtilTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        int dayOfYear = now.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear);
    }


}
