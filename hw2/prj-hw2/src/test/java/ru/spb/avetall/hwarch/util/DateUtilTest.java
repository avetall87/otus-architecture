package ru.spb.avetall.hwarch.util;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateUtilTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void toLocalDateTime() {
        LocalDateTime etalon = LocalDateTime.of(2020,7,26,18,45, 37);

        LocalDateTime localDateTime = DateUtil.toLocalDateTime("26.07.2020 18:45:37");

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(localDateTime).isNotNull();
            softAssertions.assertThat(localDateTime).isEqualTo(etalon);
        });
    }

    @Test
    void toLocalDateTimeNull() {
        LocalDateTime localDateTime = DateUtil.toLocalDateTime(null);

        SoftAssertions.assertSoftly(softAssertions -> softAssertions.assertThat(localDateTime).isNull());
    }

    @Test
    void toLocalDateTimeWrongFormat() {
        LocalDateTime localDateTime = DateUtil.toLocalDateTime("26.07.2020 18:45:105");

        SoftAssertions.assertSoftly(softAssertions -> softAssertions.assertThat(localDateTime).isNull());
    }

}