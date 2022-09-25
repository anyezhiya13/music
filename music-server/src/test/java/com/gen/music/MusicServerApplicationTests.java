package com.gen.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicServerApplicationTests {

    public static void main(String[] args) {
        System.out.println(get());
    }

    static int get() {
        int i;
        try {
            i = 3;
            return i;
        } finally {
            i = 2;
        }
    }
}

