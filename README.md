# SLM Automation Framework

A brand-new Selenium Java Cucumber BDD automation framework for https://www.saucedemo.com/.

## Tech Stack
- Java 17
- Maven
- Selenium WebDriver
- Cucumber BDD
- TestNG
- WebDriverManager
- Jackson JSON test data
- Allure reporting
- Log4j2 logging

## Why TestNG instead of JUnit?
For this framework, **TestNG is the better choice** because it gives simpler and more controllable parallel execution with Cucumber:
- easy scenario parallelization using `@DataProvider(parallel = true)`
- strong suite control with `testng.xml`
- easier browser/thread parameterization in CI
- better scaling when you later add retries, listeners, and browser matrices

## Project Structure
```text
SLM-Automation-Framework/
├── pom.xml
├── README.md
├── .github/workflows/ui-tests.yml
├── src/main/java/com/saucedemo/framework
│   ├── config
│   ├── core
│   ├── driver
│   ├── pages
│   └── utils
└── src/test
    ├── java/com/saucedemo/tests
    │   ├── hooks
    │   ├── runners
    │   └── steps
    └── resources
        ├── allure.properties
        ├── config/config.yaml
        ├── features
        ├── log4j2.xml
        ├── testdata
        └── testng/testng.xml
```

## Run Tests
### Single-browser sequential run
```powershell
mvn clean test -Dbrowser=chrome -Dthreads=1
```

### Parallel run in one browser type
This runs multiple scenarios in parallel, all using Chrome.
```powershell
mvn clean test -Dbrowser=chrome -Dthreads=4
```

### Cross-browser run
Run one browser at a time:
```powershell
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

## Headless CI run
```powershell
mvn clean test -Dheadless=true -Dbrowser=chrome -Dthreads=2
```

## Generate Allure Report
```powershell
mvn allure:report
```

## Notes about parallel execution
- `threads=1` means one scenario at a time.
- `threads=4` means up to four scenarios can run at the same time.
- If you have 4 scenarios, 4 Chrome windows can open because each scenario gets its **own isolated WebDriver**.
- This is the correct and safe way to run Selenium in parallel.
- One physical browser process shared by many scenarios is **not recommended** because it causes test interference.

## Implemented sample coverage
- Valid login
- Invalid locked user login
- Inventory sorting
- Add to cart
- Remove from cart
- Checkout flow

