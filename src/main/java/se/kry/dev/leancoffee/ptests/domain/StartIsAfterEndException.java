package se.kry.dev.leancoffee.ptests.domain;

import java.time.LocalDateTime;

public class StartIsAfterEndException extends IllegalArgumentException {
  public StartIsAfterEndException(LocalDateTime start, LocalDateTime end) {
    super(String.format("Start date %s cannot be after end date %s", start, end));
  }
}
