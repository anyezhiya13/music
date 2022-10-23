package com.gen.music;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.gen.music.mapper")
public class MusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }


}
