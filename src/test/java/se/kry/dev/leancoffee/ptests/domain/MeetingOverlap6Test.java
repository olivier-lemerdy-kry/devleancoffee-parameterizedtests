package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MeetingOverlap6Test {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  private static Stream<Arguments> streamMeetingsOverlapArguments() {
    return Stream.of(
        Arguments.of(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(15, 0), true),
        Arguments.of(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(15, 0), false),
        Arguments.of(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(15, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true),
        Arguments.of(JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true)
    );
  }

  @ParameterizedTest
  @MethodSource("streamMeetingsOverlapArguments")
  void meetings_overlap(LocalDateTime start1, LocalDateTime end1,
                        LocalDateTime start2, LocalDateTime end2,
                        boolean shouldOverlap) {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(shouldOverlap, meeting1.overlapsWith(meeting2));
  }
}
