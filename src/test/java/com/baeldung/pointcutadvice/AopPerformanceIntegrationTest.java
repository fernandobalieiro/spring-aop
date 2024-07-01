package com.baeldung.pointcutadvice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.baeldung.Application;
import com.baeldung.pointcutadvice.dao.FooDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class}, loader = AnnotationConfigContextLoader.class)
class AopPerformanceIntegrationTest {

    @BeforeEach
    void setUp() {
        logEventHandler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                messages.add(record.getMessage());
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };

        messages = new ArrayList<>();
    }

    @Autowired
    private FooDao dao;

    private Handler logEventHandler;

    private List<String> messages;

    @Test
    void givenPerformanceAspect_whenCallDaoMethod_thenPerformanceMeasurementAdviceIsCalled() {
        Logger logger = Logger.getLogger(PerformanceAspect.class.getName());
        logger.addHandler(logEventHandler);

        final String entity = dao.findById(1L);
        assertThat(entity, notNullValue());
        assertThat(messages, hasSize(1));

        String logMessage = messages.get(0);
        Pattern pattern = Pattern.compile("Execution of findById took \\d+ ms");
        assertTrue(pattern.matcher(logMessage).matches());
    }
}
