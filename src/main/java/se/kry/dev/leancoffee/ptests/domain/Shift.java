package se.kry.dev.leancoffee.ptests.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record Shift(@NotNull LocalDateTime start, @NotNull LocalDateTime end) {
  public Shift {
    requireNonNull(start);
    requireNonNull(end);
    if (start.isAfter(end)) {
      throw new StartIsAfterEndException(start, end);
    }
  }

  public boolean canAccept(@NotNull Meeting meeting) {
    return meeting.start().isAfter(start) && meeting.end().isBefore(end);
  }
}
