package se.kry.dev.leancoffee.ptests.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record Meeting(@NotNull LocalDateTime start, @NotNull LocalDateTime end) {
  public Meeting {
    requireNonNull(start);
    requireNonNull(end);
    if (start.isAfter(end)) {
      throw new StartIsAfterEndException(start, end);
    }
  }

  public boolean overlapsWith(@NotNull Meeting meeting) {
    return !(meeting.end().isBefore(start) || meeting.start().isAfter(end));
  }
}
