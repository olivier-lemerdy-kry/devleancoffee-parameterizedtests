package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class MeetingOverlap2Test {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  @Test
  void meeting_overlap() {
    Stream.of(
        new MyArguments(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(15, 0), true),
        new MyArguments(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(15, 0), false),
        new MyArguments(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(15, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true),
        new MyArguments(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true)
    ).forEach(params ->
        doMeetingsOverlap(params.start1(), params.end1(), params.start2(), params.end2(), params.shouldOverlap()));
  }

  void doMeetingsOverlap(LocalDateTime start1, LocalDateTime end1,
                         LocalDateTime start2, LocalDateTime end2,
                         boolean shouldOverlap) {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(shouldOverlap, meeting1.overlapsWith(meeting2));
  }

}

record MyArguments(LocalDateTime start1, LocalDateTime end1,
                   LocalDateTime start2, LocalDateTime end2,
                   boolean shouldOverlap) {
}
