package com.baeldung;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomAnnotationIntegrationTest {

    @Autowired
    private Service service;

    @Test
    void shouldApplyCustomAnnotation() throws InterruptedException {
        service.serve();
    }

}
