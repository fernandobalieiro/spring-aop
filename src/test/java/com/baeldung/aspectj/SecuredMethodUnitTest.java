package com.baeldung.aspectj;

import org.junit.jupiter.api.Test;

class SecuredMethodUnitTest {
    @Test
    void testMethod() throws Exception {
        SecuredMethod service = new SecuredMethod();
        service.unlockedMethod();
        service.lockedMethod();
    }
}
