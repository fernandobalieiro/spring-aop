package com.baeldung.pointcutadvice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.baeldung.Application;
import com.baeldung.pointcutadvice.dao.FooDao;
import com.baeldung.pointcutadvice.events.FooCreationEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class}, loader = AnnotationConfigContextLoader.class)
class AopPublishingIntegrationTest {

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
    void givenPublishingAspect_whenCallCreate_thenCreationEventIsPublished() {
        Logger logger = Logger.getLogger(FooCreationEventListener.class.getName());
        logger.addHandler(logEventHandler);

        dao.create(1L, "Bar");

        String logMessage = messages.get(0);
        Pattern pattern = Pattern.compile("Created foo instance: " + Pattern.quote(new Foo(1L, "Bar").toString()));
        assertTrue(pattern.matcher(logMessage).matches());
    }
}
