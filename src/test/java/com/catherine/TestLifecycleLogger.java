package com.catherine;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

/**
 * Check out https://junit.org/junit5/docs/snapshot/user-guide/#writing-tests-test-interfaces-and-default-methods
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface TestLifecycleLogger {

    Logger logger = Logger.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    default void beforeAllTests() {
        logger.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests() {
        logger.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("About to execute [%s]",
                testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("Finished executing [%s]",
                testInfo.getDisplayName()));
    }

}