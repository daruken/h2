package com.zzimkong.h2.yogurt;

import com.zzimkong.h2.yogurt.domain.Yogurt;
import com.zzimkong.h2.yogurt.domain.YogurtRepository;
import com.zzimkong.h2.yogurt.domain.type.YogurtType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class YogurtRepositoryTest {
    @Autowired
    private YogurtRepository yogurtRepository;

    @Test
    @Order(1)
    void 요거트_생성_테스트() {
        Yogurt yogurtEntity = new Yogurt("DEAN", YogurtType.PLAIN, 5000);
        yogurtEntity.setId("1");

        Publisher<Yogurt> setup = yogurtRepository.deleteAll().then(yogurtRepository.save(yogurtEntity));
        Mono<Yogurt> find = yogurtRepository.findById("1");
        Publisher<Yogurt> composite = Mono
                .from(setup)
                .then(find);

        StepVerifier
                .create(composite)
                .consumeNextWith(yogurt -> {
                    Assertions.assertNotNull(yogurt.getId());
                    Assertions.assertEquals(yogurt.getName(), yogurtEntity.getName());
                    Assertions.assertEquals(yogurt.getType(), yogurtEntity.getType());
                    Assertions.assertEquals(yogurt.getPrice(), yogurtEntity.getPrice());
                })
                .verifyComplete();
    }

    @Test
    @Order(2)
    void 요거트_삭제_테스트() {
        StepVerifier
                .create(yogurtRepository.deleteById("1"))
                .verifyComplete();
    }
}
