package com.zzimkong.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

import java.io.FilterInputStream;
import java.util.ResourceBundle;

@SpringBootApplication
public class H2Application {
    public static void main(String[] args) {
        BlockHound.builder()
                .allowBlockingCallsInside(FilterInputStream.class.getName(), "read")
                .allowBlockingCallsInside(ResourceBundle.class.getName(), "getBundle")
                .install();

        SpringApplication.run(H2Application.class, args);
    }
}
