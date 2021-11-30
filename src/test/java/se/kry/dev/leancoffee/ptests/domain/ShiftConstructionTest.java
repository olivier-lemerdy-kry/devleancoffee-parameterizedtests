package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import org.junit.jupiter.api.Test;

class ShiftConstructionTest {

  public static final LocalDateTime FIRST_OF_JANUARY_AT_NOON =
      LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON);

  public static final LocalDateTime LAST_OF_DECEMBER_AT_MIDNIGHT =
      LocalDate.of(2001, Month.DECEMBER, 31).atTime(LocalTime.MIDNIGHT);

  @Test
  void shift_with_null_start_should_fail() {
    assertThrows(NullPointerException.class, () ->
        new Shift(null, FIRST_OF_JANUARY_AT_NOON));
  }

  @Test
  void shift_with_null_end_should_fail() {
    assertThrows(NullPointerException.class, () ->
        new Shift(FIRST_OF_JANUARY_AT_NOON, null));
  }

  @Test
  void shift_with_start_after_end_should_fail() {
    assertThrows(StartIsAfterEndException.class, () ->
        new Shift(LAST_OF_DECEMBER_AT_MIDNIGHT, FIRST_OF_JANUARY_AT_NOON));
  }

  @Test
  void shift_with_start_equal_to_end_should_succeed() {
    var shift = new Shift(FIRST_OF_JANUARY_AT_NOON, FIRST_OF_JANUARY_AT_NOON);
    assertNotNull(shift);
  }

  @Test
  void shift_with_start_before_end_should_succeed() {
    var shift = new Shift(FIRST_OF_JANUARY_AT_NOON, LAST_OF_DECEMBER_AT_MIDNIGHT);
    assertNotNull(shift);
  }

}