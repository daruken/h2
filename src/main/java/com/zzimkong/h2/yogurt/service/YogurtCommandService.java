package com.zzimkong.h2.yogurt.service;

import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class YogurtCommandService {
    private final YogurtRepository yogurtRepository;

    YogurtCommandService(YogurtRepository yogurtRepository) {
        this.yogurtRepository = yogurtRepository;
    }

    public Mono<Yogurt> createYogurt(Yogurt yogurt) {
        return yogurtRepository.save(yogurt);
    }

    public Mono<Void> deleteYogurt(String id) {
        return yogurtRepository.deleteById(id);
    }
}
