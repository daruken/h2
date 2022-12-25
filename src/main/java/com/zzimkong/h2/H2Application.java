package com.zzimkong.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import reactor.blockhound.BlockHound;

import java.io.FilterInputStream;
import java.util.ResourceBundle;

@SpringBootApplication
@ConfigurationPropertiesScan
public class H2Application {
    public static void main(String[] args) {
        BlockHound.builder()
                .allowBlockingCallsInside(FilterInputStream.class.getName(), "read")
                .allowBlockingCallsInside(ResourceBundle.class.getName(), "getBundle")
                .install();

        SpringApplication.run(H2Application.class, args);
    }
}
