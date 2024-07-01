package com.baeldung.logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:springAop-applicationContext.xml"})
class CalculatorIntegrationTest {

    @Autowired
    private SampleAdder sampleAdder;

    @Test
    void whenAddValidValues_returnsSucessfully() {
        final int addedValue = sampleAdder.add(12, 12);

        assertEquals(24, addedValue);
    }

    @Test
    void whenAddInValidValues_throwsException() {
        try {
            sampleAdder.add(12, -12);
        } catch (IllegalArgumentException e) {
            // Does nothing, expected
        } catch (Throwable e) {
            fail();
        }
    }

}
