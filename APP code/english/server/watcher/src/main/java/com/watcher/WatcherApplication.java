package com.watcher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.watcher.dao")
public class WatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatcherApplication.class, args);
    }

}
