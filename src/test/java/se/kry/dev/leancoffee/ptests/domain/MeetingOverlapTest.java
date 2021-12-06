package se.kry.dev.leancoffee.ptests.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.jupiter.api.Test;

class MeetingOverlapTest {

  @Test
  void two_different_days_with_same_hours_do_not_overlap() {
    var start1 = LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON);
    var meeting1 = new Meeting(start1, Duration.ofHours(1));
  }
}
