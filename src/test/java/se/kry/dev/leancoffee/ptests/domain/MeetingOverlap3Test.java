package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MeetingOverlap3Test {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  private final LocalDateTime start1;
  private final LocalDateTime end1;
  private final LocalDateTime start2;
  private final LocalDateTime end2;
  private final boolean shouldOverlap;

  public MeetingOverlap3Test(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2,
                             boolean shouldOverlap) {
    this.start1 = start1;
    this.end1 = end1;
    this.start2 = start2;
    this.end2 = end2;
    this.shouldOverlap = shouldOverlap;
  }

  @Parameters(name = "{index} meeting 1 from {0} to {1} and meeting 2 from {2} to {3} should overlap: {4}")
  public static Object[][] meetingsOverlapData() {
    return new Object[][] {
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(15, 0), true},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(15, 0), false},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(15, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true}
    };
  }

  @Test
  public void meetings_overlap() {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(shouldOverlap, meeting1.overlapsWith(meeting2));
  }

}
