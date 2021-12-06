package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MeetingOverlap3Test {

  @ParameterizedTest
  @CsvSource({
      "2001-01-01T12:00,2001-01-01T14:00,2001-01-01T13:00,2001-01-01T15:00,true",
      "2001-01-01T12:00,2001-01-01T13:00,2001-01-01T14:00,2001-01-01T15:00,false",
      "2001-01-01T12:00,2001-01-01T15:00,2001-01-01T13:00,2001-01-01T14:00,true",
      "2001-01-01T12:00,2001-01-01T13:00,2001-01-01T13:00,2001-01-01T14:00,true",
  })
  void meetings_overlap(LocalDateTime start1, LocalDateTime end1,
                        LocalDateTime start2, LocalDateTime end2,
                        boolean shouldOverlap) {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(shouldOverlap, meeting1.overlapsWith(meeting2));
  }

}
