Dev Lean Coffee: Parameterized Tests
====================================

What we want to solve?
----------------------

* Testing algorithms with a large number of different inputs and expected outputs
* Keeping the tests as readable and understandable as possible
* Keeping the test output as readable and understandable as possible
* Keeping the tests as easy to debug as possible

Introducing our sample model
----------------------------

We'll have [Meetings](src/main/java/se/kry/dev/leancoffee/ptests/domain/Meeting.java) consisting of a `start` and `end`
datetime and that have a `overlapsWith` test method that checks if two meetings overlap.

We will focus on testing multiple configurations of meetings to verify overlap rules.

Naive method: writing everything
--------------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap1Test.java)

Pros

* It is straightforward
* Test output makes it clear which case failed

Cons

* Typing test cases is hugely repetitive
* We have to name independently each test case

Naive method: factorize!
------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap2Test.java)

Pros

* Everything is factorized, like a good developer should do!
* We do not have to find multiple names for methods

Cons

* If one of my test case fails, the test fails as a whole
* The test output does not contain any useful information if something fails
* Not that easy to read

JUnit 5: `@ParameterizedTest @CsvSource`
----------------------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap3Test.java)

Pros

* Factorize the tests while preserving the output and atomic failure
* Can perform simple conversions

Cons

* CSV input in annotation is not easy to read

JUnit 5: `@ParameterizedTest @CsvFileSource`
--------------------------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap4Test.java)

Pros

* Same advantages as `@CsvSource`
* Can rely on CSV editor for readability

Cons

* CSV file is outside the test file, which is not ideal for small test samples

JUnit 5: `@ParameterizedTest @MethodSource`
-------------------------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap5Test.java)

Pros

* More flexibility in Samples creation (as it is code)

JUnit 5: `@ParameterizedTest @ArgumentsSource`
----------------------------------------------

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlap6Test.java)

Pros

* More flexibility in Samples creation (as it is code)
* Can factorize providers between multiple tests

TestNG: `@DataProvider`
-----------------------

[TestNG](https://testng.org/doc/index.html) was the alternative Test framework for Java but seemed to have lost
momentum. It implemented data source methods for parameterized tests a long time ago.

[LET'S DEMO](src/test/java/se/kry/dev/leancoffee/ptests/domain/MeetingOverlapNGTest.java)

Pros

* Similar to `@Parameterized @MethodSource`

Cons

* Slightly more verbose because of the `new Object[]{}`

Spock
-----

[Spock](https://spockframework.org/) is a testing and specification framework for Groovy and Java.

[Groovy](https://groovy-lang.org/) is an optionally typed and dynamic language running on the JVM. It is especially
useful to write DSL and is actually the `Grrrrr` of Gradle.

[LET'S DEMO](src/test/groovy/se/kry/dev/leancoffee/ptests/domain/MeetingSpockTest.groovy)

Pros

* Specification is quite readable
* Formatting is out of the box for tables in IntelliJ

Cons

* Another language and tooling (but integrates well)

ScalaTest: `TableDrivenPropertyChecks`
--------------------------------------

[Scala](https://www.scala-lang.org/) combines object-oriented and functional programming in one concise, high-level
language. It has JVM & JS runtimes.

[ScalaTest](https://www.scalatest.org/) is the most flexible and most popular testing tool in the Scala ecosystem.

[LET'S DEMO](src/test/scala/se/kry/dev/leancoffee/ptests/domain/MeetingScalaTest.scala)

Pros

* Very flexible: can write the specs in different styles
* Beautiful and highly configurable output

Cons

* Another language, runtime and tooling