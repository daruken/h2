package com.zzimkong.h2.yogurt;

import com.zzimkong.h2.yogurt.controller.YogurtController;
import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import com.zzimkong.h2.yogurt.domain.type.YogurtType;
import com.zzimkong.h2.yogurt.service.YogurtCommandService;
import com.zzimkong.h2.yogurt.service.YogurtQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = YogurtController.class)
@Import({YogurtCommandService.class, YogurtQueryService.class})
public class YogurtServiceUnitTest {

    @MockBean
    private YogurtRepository yogurtRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 요거트_생성_테스트() {
        Yogurt yogurt = new Yogurt("DEAN", YogurtType.PLAIN, 5000);
        Mockito.when(yogurtRepository.save(yogurt)).thenReturn(Mono.just(yogurt));

        webTestClient.post()
                .uri("/yogurts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(yogurt), Yogurt.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void 요거트_단일조회_테스트() {
        Yogurt yogurt = new Yogurt("Pio", YogurtType.GREEK, 2000);
        yogurt.setId("1");
        Mono<Yogurt> yogurtMono = Mono.just(yogurt);

        Mockito.when(yogurtRepository.findById("1")).thenReturn(yogurtMono);

        webTestClient.get()
                .uri("/yogurts/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Yogurt.class);

        Mockito.verify(yogurtRepository, times(1)).findById("1");
    }
}
