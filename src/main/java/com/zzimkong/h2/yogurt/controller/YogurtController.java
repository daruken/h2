package com.zzimkong.h2.yogurt.controller;

import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.service.YogurtCommandService;
import com.zzimkong.h2.yogurt.service.YogurtQueryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yogurts")
public class YogurtController {
    private final YogurtCommandService yogurtCommandService;
    private final YogurtQueryService yogurtQueryService;

    public YogurtController(YogurtCommandService yogurtCommandService, YogurtQueryService yogurtQueryService) {
        this.yogurtCommandService = yogurtCommandService;
        this.yogurtQueryService = yogurtQueryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addYogurt(@RequestBody Yogurt yogurt) {
        yogurtCommandService.createYogurt(yogurt);
    }

    @GetMapping(value = "")
    public Flux<Yogurt> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        return yogurtQueryService.selectYogurts(PageRequest.of(page, size));
    }

    @GetMapping(value = "/{id}")
    public Mono<Yogurt> findYogurt(@PathVariable String id) {
        return yogurtQueryService.selectYogurtById(id);
    }
}
