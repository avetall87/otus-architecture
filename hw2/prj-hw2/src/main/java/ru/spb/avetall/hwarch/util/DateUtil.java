package ru.spb.avetall.hwarch.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
public class DateUtil {

    private final static String PATTERN = "dd.MM.yyyy HH:mm:ss";
    private final static String PATTERN_SHORT = "dd.MM.yyyy";
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private final static DateTimeFormatter FORMATTER_SHORT = DateTimeFormatter.ofPattern(PATTERN_SHORT);

    public static LocalDateTime toLocalDateTime(String date) {
        if (isEmpty(date)) {
            return null;
        }
        
        try {
            return (date.length() > 10) ? LocalDateTime.parse(date, FORMATTER) 
                                        : LocalDateTime.of(LocalDate.parse(date, FORMATTER_SHORT), 
                                                           LocalTime.of(0,0,0));
        } catch (DateTimeParseException ex) {
            log.error("toLocalDateTime exception", ex);
            return null;
        }
    }
    
    public static String localDateTimeToString (LocalDateTime localDateTime) {
        if (isNull(localDateTime)) {
            return "";
        }

        return localDateTime.format(FORMATTER);
    }

}
