package com.zzimkong.h2.yogurt.service;

import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class YogurtQueryService {
    private final YogurtRepository yogurtRepository;

    public YogurtQueryService(YogurtRepository yogurtRepository) {
        this.yogurtRepository = yogurtRepository;
    }

    public Flux<Yogurt> selectYogurts() {
        return yogurtRepository.findAll();
    }
}
