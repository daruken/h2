package com.zzimkong.h2.auth;

import com.zzimkong.h2.security.JwtTokenProvider;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthControllerTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void 토큰생성() {
        String jwt = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken("admin", "password"));
        Assert.hasLength(jwt);
    }
}
