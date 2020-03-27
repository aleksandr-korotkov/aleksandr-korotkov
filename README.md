# aleksandr-korotkov
expression-handler-test

Use Calculator class from “Java basic” module and cover its methods with JUnit tests with both positive and negative scenarios. Group negative and positive tests cases into separate test suites.
Test the following functionality:
    1. Addition, subsctraction, multiplication, division
    2. Division should check parameters and throw IllegalArgumentException (division by zero etc.).
    3. Invalid input are being rejected with a meaningfull reason(empty strings, chars, few operators in a row (++), etc.)
Use hamcrest matchers to validate results.
