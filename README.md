**Instructions for running Tests:**


**Tools:**
Java 8,
J Unit,
Rest Assured,
Jackson,
Lombok

(Please ensure you have lombok and J unit intelliJ Plugins).

**Info**

I have created this simple framework, which is using J unit jupiter to run the test cases.

**How to run**

You can find all the task under `~/src/test/java/tasks/`

You can run the tests in 2 ways, by either running them individually (or by task) using the intelliJ play tool
or by running the command `mvn clean test`


The result will be shown at the end of the tests.

**Improvements for the future:**

- Move away from Junit and use a BDD languages like Cucumber/Gherkin.
- Abstract some of the code into a until classes for example the reest assured requests.
- Clean up and repetitively exposed string into constants.
- If this was to expand into separate libraries for any generic functionality that can be used acroess multiple frameworks.
- I would also implement Hooks (methods which will controll what happens before and after the scenrio's). 
- At the moment I have some code which will help to set up and tear down the tests, but this would be more well defined in a Hook. 
- Reporting, I would look for an appropriate reporting tool to make it easier for the understand the failers.
