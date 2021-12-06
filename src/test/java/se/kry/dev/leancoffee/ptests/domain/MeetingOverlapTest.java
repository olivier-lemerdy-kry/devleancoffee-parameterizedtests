package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.jupiter.api.Test;

class MeetingOverlapTest {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  @Test
  void meeting_from_12_to_14_overlaps_with_meeting_from_13_to_15() {
    var meeting1 = new Meeting(JAN_1ST.atTime(LocalTime.NOON), JAN_1ST.atTime(14, 0));
    var meeting2 = new Meeting(JAN_1ST.atTime(13, 0), JAN_1ST.atTime(15, 0));

    assertTrue(meeting1.overlapsWith(meeting2));
  }

  @Test
  void meeting_from_12_to_13_does_not_overlap_with_meeting_from_14_to_15() {
    var meeting1 = new Meeting(JAN_1ST.atTime(LocalTime.NOON), JAN_1ST.atTime(13, 0));
    var meeting2 = new Meeting(JAN_1ST.atTime(14, 0), JAN_1ST.atTime(15, 0));

    assertFalse(meeting1.overlapsWith(meeting2));
  }

  @Test
  void meeting_from_12_to_15_overlaps_with_meeting_from_13_to_14() {
    var meeting1 = new Meeting(JAN_1ST.atTime(LocalTime.NOON), JAN_1ST.atTime(15, 0));
    var meeting2 = new Meeting(JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0));

    assertTrue(meeting1.overlapsWith(meeting2));
  }

  @Test
  void meeting_from_12_to_13_overlaps_with_meeting_from_13_to_14() {
    var meeting1 = new Meeting(JAN_1ST.atTime(LocalTime.NOON), JAN_1ST.atTime(13, 0));
    var meeting2 = new Meeting(JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0));

    assertTrue(meeting1.overlapsWith(meeting2));
  }
}
