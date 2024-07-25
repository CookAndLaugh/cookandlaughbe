package com.vansisto.cookandlaughbe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestcontainersTest extends AbstractTestcontainers {

    @Test
    void testDbStarted() {
        assertThat(POSTGRESQL_CONTAINER.isCreated());
        assertThat(POSTGRESQL_CONTAINER.isRunning());
    }
}
