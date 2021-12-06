package se.kry.dev.leancoffee.ptests.domain

import spock.lang.Specification

import java.time.LocalDateTime

class MeetingSpockTest extends Specification {
    def "Meetings overlap"(String start1, String end1, String start2, String end2, boolean expectOverlap) {
        given:
        var meeting1 = new Meeting(
                LocalDateTime.parse(start1),
                LocalDateTime.parse(end1)
        )
        var meeting2 = new Meeting(
                LocalDateTime.parse(start2),
                LocalDateTime.parse(end2)
        )

        expect:
        meeting1.overlapsWith(meeting2) == expectOverlap

        where:
        start1             | end1               | start2             | end2               || expectOverlap
        "2001-01-01T12:00" | "2001-01-01T14:00" | "2001-01-01T13:00" | "2001-01-01T15:00" || true
        "2001-01-01T13:00" | "2001-01-01T15:00" | "2001-01-01T12:00" | "2001-01-01T14:00" || true
        "2001-01-01T12:00" | "2001-01-01T13:00" | "2001-01-01T14:00" | "2001-01-01T15:00" || false
        "2001-01-01T14:00" | "2001-01-01T15:00" | "2001-01-01T12:00" | "2001-01-01T13:00" || false
        "2001-01-01T12:00" | "2001-01-01T15:00" | "2001-01-01T13:00" | "2001-01-01T14:00" || true
        "2001-01-01T13:00" | "2001-01-01T14:00" | "2001-01-01T12:00" | "2001-01-01T15:00" || true
        "2001-01-01T12:00" | "2001-01-01T13:00" | "2001-01-01T13:00" | "2001-01-01T14:00" || true
        "2001-01-01T13:00" | "2001-01-01T14:00" | "2001-01-01T12:00" | "2001-01-01T13:00" || true
    }
}
