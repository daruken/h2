package com.zzimkong.h2.yogurt.domain;

import com.zzimkong.h2.yogurt.domain.type.YogurtType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Yogurt {
    private @Id String id;
    private String name;
    private YogurtType type;
    private double price;

    public Yogurt(String name, YogurtType type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
