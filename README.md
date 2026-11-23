# OrangeHRM Automation Testing Project

UI test automation framework for the [OrangeHRM Open Source Demo](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login), built with **Selenium WebDriver**, **TestNG** and the **Page Object Model (POM)**.

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 22 | Programming language |
| Selenium WebDriver 4 | Browser automation |
| TestNG | Test runner, assertions, listeners |
| Maven | Build and dependency management |
| Jackson | Reading test data from JSON |
| Log4j2 | Logging to console and file |
| Allure | HTML test reporting |

## Features

- **Page Object Model**: each page has its own class with its locators and actions.
- **Data-driven testing**: test data (usernames, passwords, employee names) is stored in `Test_Data.json`.
- **Configuration file**: environment settings (`baseurl`, `browser`, `explicit.wait`) are stored in `config.properties` and read with Java's `Properties` class.
- **Explicit waits only**: no `Thread.sleep()`.
- **Parallel execution**: `parallel="tests"` with `thread-count="2"` in `Runner.xml`.
- **ThreadLocal WebDriver**: `DriverManager` gives every thread its own independent browser.
- **Retry mechanism**: failed tests are retried up to 2 times using `IRetryAnalyzer`, attached to all tests through an `IAnnotationTransformer` listener.
- **Logging**: Log4j2 writes INFO steps and ERROR failures to `logs/test.log`.
- **Allure reporting**: static HTML report with test cases and execution results.

## Project Structure

```
Orange_HRM
├── pom.xml
├── Runner.xml                      # TestNG suite (parallel + listeners)
├── allure-report/                  # Generated static Allure report
└── src
    ├── main
    │   ├── java
    │   │   ├── Pages
    │   │   │   ├── BasePage.java
    │   │   │   ├── LoginPage.java
    │   │   │   ├── DashBoardPage.java
    │   │   │   ├── PIMPage.java
    │   │   │   ├── AddEmployeePage.java
    │   │   │   └── AdminPage.java
    │   │   └── Utils
    │   │       └── DataProvider.java   # Reads values from the JSON file
    │   └── resources
    │       ├── config.properties
    │       ├── Test_Data.json
    │       └── log4j2.xml
    └── test
        └── java
            ├── BaseTest.java           # Opens and closes the browser
            ├── DriverManager.java      # ThreadLocal<WebDriver>
            ├── RetryAnalyzer.java
            ├── RetryListener.java
            ├── LoginTest.java
            ├── DashBoardTest.java
            ├── PIMTest.java
            ├── AddEmployeeTest.java
            └── AdminTest.java
```

## Configuration

`config.properties`:

```properties
baseurl=https://opensource-demo.orangehrmlive.com/web/index.php/
browser=chrome
explicit.wait=10
```

`Test_Data.json` holds the login credentials and other test data, for example:

```json
{
  "validusername": "Admin",
  "validpassword": "admin123"
}
```

## Test Cases

| # | Test Case |
|---|-----------|
| 1 | Login with valid credentials |
| 2 | Login with invalid credentials |
| 3 | Login with empty fields |
| 4 | Search for an existing employee (PIM) |
| 5 | Search for a non-existing employee |
| 6 | Open the Add Employee page |
| 7 | Add an employee with an empty required field |
| 8 | End-to-end: add a new employee successfully |
| 9 | Verify the Admin > Add User page |
| 10 | Verify the OrangeHRM footer / branding link |
| 11 | Verify the sidebar menu UI |

## Prerequisites

- JDK 22
- Maven
- Google Chrome
- Allure command-line tool (only to generate and open the report)

## How to Run

Clone the repository:

```bash
git clone https://github.com/<your-username>/automation-testing-graduation-project.git
cd automation-testing-graduation-project
```

Run all tests (uses `Runner.xml`):

```bash
mvn clean test
```

You can also run `Runner.xml` directly from IntelliJ IDEA: right-click the file and choose **Run**.

## Parallel Execution

`Runner.xml` is configured as:

```xml
<suite name="OrangeHRM Suite" parallel="tests" thread-count="2">
```

Each `<test>` block runs in its own thread, and `DriverManager` (a `ThreadLocal<WebDriver>`) makes sure every thread uses its own browser instance.

## Retry Mechanism

`RetryAnalyzer` retries a failed test up to 2 times before it is marked as failed. `RetryListener` applies it to every test automatically, so no test needs `retryAnalyzer` written on it.

## Logging

Log4j2 is configured in `log4j2.xml`. Logs are written to the console and to `logs/test.log`, with the thread name included in each line.

## Allure Report

Generate the results by running the tests:

```bash
mvn test
```

Generate the static HTML report:

```bash
allure generate target/allure-results --clean -o allure-report
```

Open the report locally:

```bash
allure open allure-report
```

> The `allure-results` folder is not committed. Only the generated `allure-report` folder is pushed to the repository.

## Notes

The application under test is a public demo site that is shared with many users and is reset periodically. Because of that, the tests create the data they need (for example, a new employee with a unique name) instead of relying on existing records, and the retry mechanism reduces false failures caused by slow responses.

## Author

**Engy Henry**, QA Engineer
[LinkedIn](https://linkedin.com/in/engyhenry)
