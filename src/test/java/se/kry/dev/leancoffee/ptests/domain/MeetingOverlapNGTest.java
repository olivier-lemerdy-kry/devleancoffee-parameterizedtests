package se.kry.dev.leancoffee.ptests.domain;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Iterator;
import java.util.stream.Stream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MeetingOverlapNGTest {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  @DataProvider
  public Iterator<Object[]> meetingsOverlapDataIterator() {
    return Stream.of(
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(15, 0), true},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), JAN_1ST.atTime(15, 0), false},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(15, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true},
        new Object[] {JAN_1ST.atTime(12, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(13, 0), JAN_1ST.atTime(14, 0), true}
    ).iterator();
  }

  @Test(dataProvider = "meetingsOverlapDataIterator")
  public void meetings_overlap(LocalDateTime start1, LocalDateTime end1,
                               LocalDateTime start2, LocalDateTime end2,
                               boolean shouldOverlap) {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(meeting1.overlapsWith(meeting2), shouldOverlap);
  }
}
