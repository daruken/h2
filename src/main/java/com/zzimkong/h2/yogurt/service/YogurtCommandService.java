package com.zzimkong.h2.yogurt.service;

import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class YogurtCommandService {
    private final YogurtRepository yogurtRepository;

    YogurtCommandService(YogurtRepository yogurtRepository) {
        this.yogurtRepository = yogurtRepository;
    }

    public void createYogurt(Yogurt yogurt) {
        yogurtRepository.save(yogurt).subscribe();
    }
}
