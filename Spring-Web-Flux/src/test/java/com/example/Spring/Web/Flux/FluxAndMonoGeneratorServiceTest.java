package com.example.Spring.Web.Flux;

import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {
    


    @Test
    void namesFlux() {

        Flux<String> namesFLux = FluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFLux)
                //.expectNext("Jen", "Ken", "Stacy")
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void namesFluxToUpperCase() {

        Flux<String> namesFluxToUpperCase = FluxAndMonoGeneratorService.namesFluxToUpperCase();

        StepVerifier.create(namesFluxToUpperCase)
                .expectNext("JEN", "KEN", "STACY")
                .verifyComplete();
    }

    @Test
    void namesFluxFilterByStringLength(){
        Flux<String> namesFluxFilterByStringLength =
                FluxAndMonoGeneratorService.namesFluxFilterByStringLength(3);

        StepVerifier.create(namesFluxFilterByStringLength)
                .expectNext("Stacy")
                .verifyComplete();


    }

    @Test
    void animalNamesFlatMap() {

        Flux<String> animalNamesFlatMap = FluxAndMonoGeneratorService.animalNamesFlatMap();

        StepVerifier.create(animalNamesFlatMap)
                .expectNextCount(10)
                .verifyComplete();
    }
}
