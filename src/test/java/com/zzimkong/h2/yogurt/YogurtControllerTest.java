package com.zzimkong.h2.yogurt;

import com.zzimkong.h2.yogurt.controller.YogurtController;
import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import com.zzimkong.h2.yogurt.domain.type.YogurtType;
import com.zzimkong.h2.yogurt.service.YogurtCommandService;
import com.zzimkong.h2.yogurt.service.YogurtQueryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = YogurtController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
@Import({YogurtCommandService.class, YogurtQueryService.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class YogurtControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private YogurtRepository yogurtRepository;

    @Test
    @Order(1)
    void 요거트_생성_테스트() {
        Yogurt yogurt = new Yogurt("POOH", YogurtType.PLAIN, 3000);
        yogurt.setId("2");

        Mockito.when(yogurtRepository.save(yogurt)).thenReturn(Mono.just(yogurt));

        webTestClient.mutateWith(csrf())
                .post()
                .uri("/yogurts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(yogurt)
                .exchange()
                .expectStatus()
                .isCreated();

        Mockito.verify(yogurtRepository, times(1)).save(yogurt);
    }

    @Test
    @Order(2)
    void 요거트_단일조회_테스트() {
        Yogurt yogurt = new Yogurt("POOH", YogurtType.PLAIN, 3000);
        yogurt.setId("2");

        Mockito.when(yogurtRepository.findById("2")).thenReturn(Mono.just(yogurt));

        webTestClient.mutateWith(csrf())
                .get()
                .uri("/yogurts/2")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Yogurt.class);

        Mockito.verify(yogurtRepository, times(1)).findById("2");
    }
}
