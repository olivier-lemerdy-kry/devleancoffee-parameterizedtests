package se.kry.dev.leancoffee.ptests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class MeetingOverlap7Test {

  @ParameterizedTest
  @ArgumentsSource(MeetingOverlapArgumentsProvider.class)
  void meetings_overlap(LocalDateTime start1, LocalDateTime end1,
                        LocalDateTime start2, LocalDateTime end2,
                        boolean shouldOverlap) {
    var meeting1 = new Meeting(start1, end1);
    var meeting2 = new Meeting(start2, end2);

    assertEquals(shouldOverlap, meeting1.overlapsWith(meeting2));
  }
}
