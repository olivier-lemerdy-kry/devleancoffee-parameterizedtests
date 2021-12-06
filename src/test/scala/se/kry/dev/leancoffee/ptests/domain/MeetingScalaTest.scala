package se.kry.dev.leancoffee.ptests.domain

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.prop.TableDrivenPropertyChecks

import java.time.{LocalDate, Month}

class MeetingScalaTest extends AnyFreeSpec with TableDrivenPropertyChecks {

  val jan1st: LocalDate =
    LocalDate.of(2001, Month.JANUARY, 1)

  "Meetings" - {
    "should overlap be" - {

      val data = Table(
        ("Meeting 1 start", "Meeting 1 end", "Meeting 2 start", "Meeting 2 end", "Should overlap"),
        (jan1st.atTime(12, 0), jan1st.atTime(14, 0), jan1st.atTime(13, 0), jan1st.atTime(15, 0), true),
        (jan1st.atTime(13, 0), jan1st.atTime(15, 0), jan1st.atTime(12, 0), jan1st.atTime(14, 0), true),
        (jan1st.atTime(12, 0), jan1st.atTime(13, 0), jan1st.atTime(14, 0), jan1st.atTime(15, 0), false),
        (jan1st.atTime(14, 0), jan1st.atTime(15, 0), jan1st.atTime(12, 0), jan1st.atTime(13, 0), false),
        (jan1st.atTime(12, 0), jan1st.atTime(15, 0), jan1st.atTime(13, 0), jan1st.atTime(14, 0), true),
        (jan1st.atTime(13, 0), jan1st.atTime(14, 0), jan1st.atTime(12, 0), jan1st.atTime(15, 0), true),
        (jan1st.atTime(12, 0), jan1st.atTime(13, 0), jan1st.atTime(13, 0), jan1st.atTime(14, 0), true),
        (jan1st.atTime(13, 0), jan1st.atTime(14, 0), jan1st.atTime(12, 0), jan1st.atTime(13, 0), true),
      )

      forAll(data) { (start1, end1, start2, end2, shouldOverlap) =>
        val meeting1 = new Meeting(start1, end1)
        val meeting2 = new Meeting(start2, end2)

        s"$shouldOverlap for $meeting1 and $meeting2" in {
          meeting1.overlapsWith(meeting2) shouldBe shouldOverlap
        }
      }
    }
  }

}