package se.kry.dev.leancoffee.ptests.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class MeetingOverlapArgumentsProvider implements ArgumentsProvider {

  private static final LocalDate JAN_1ST = LocalDate.of(2001, Month.JANUARY, 1);

  private final LocalDate day;

  public MeetingOverlapArgumentsProvider() {
    this(JAN_1ST);
  }

  public MeetingOverlapArgumentsProvider(@NotNull LocalDate day) {
    this.day = requireNonNull(day);
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(
        Arguments.of(day.atTime(12, 0), day.atTime(14, 0), day.atTime(13, 0), day.atTime(15, 0), true),
        Arguments.of(day.atTime(12, 0), day.atTime(13, 0), day.atTime(14, 0), day.atTime(15, 0), false),
        Arguments.of(day.atTime(12, 0), day.atTime(15, 0), day.atTime(13, 0), day.atTime(14, 0), true),
        Arguments.of(day.atTime(12, 0), day.atTime(13, 0), day.atTime(13, 0), day.atTime(14, 0), true)
    );
  }
}
