package com.zzimkong.h2.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResultDto {
    private String userId;
    private String token;
    private Date issuedAt;
    private Date expiresAt;
}
