# Test Automation Framework

This repository contains a multi-module automation framework built using Selenium Webdriver 4, Java as Programming
language, Cucumber for BDD Gherkin style scripting, Maven for project and dependency management, TestNG for assertion
and parallel
execution and
ExtentReports for reporting.

## Modules

- test-automation : parent
- test-utils : child (contains all the common framework code)
- ui-tests : child (contains all ui tests, page objects, steps, features, runner, etc.)

## Prerequisites

Before running the automation framework, ensure you have the following software and their setup in place:

1. **Java 17 or higher**: Java Development Kit (JDK) 17 or a higher version should be installed on your system. You can
   download the latest JDK from the official Oracle(https://www.oracle.com/java/technologies/downloads/) or OpenJDK
   website. After installing Java, set the `JAVA_HOME` environment variable to the JDK installation path, and add
   the `bin` directory to your system's `PATH`, for detailed steps follow instructions given in this
   article (https://www.geeksforgeeks.org/how-to-set-java-path-in-windows-and-linux/).

2. **Maven**: Apache Maven should be installed on your system. You can download the latest Maven distribution from the
   official website (https://maven.apache.org/download.cgi) and follow the installation instructions. After installing
   Maven, make sure it is added to your system's `PATH`, for detailed steps follow instructions given in this
   article (https://www.tutorialspoint.com/maven/maven_environment_setup.htm)

3. **Integrated Development Environment (IDE)**: You need an IDE like IntelliJ IDEA (recommended) or Eclipse to work
   with the project. Download the latest version of the IDE of your choice and install the necessary plugins for Maven
   and TestNG support.

## Getting Started

Follow the steps below to set up the framework and run the UI automation tests:

1. Clone `test-automation` repository to your local machine
   using `git clone ${repository clone url}`
   command.

2. Open your preferred IDE (IntelliJ IDEA or Eclipse) and import the project `test-automation` folder as a Maven
   project.

3. Make sure to install the necessary Maven dependencies by running `mvn clean install` from the project root
   directory(`test-automation`).

4. Write your UI tests by organizing following in `ui-tests` module,
    - The Page Objects (create in package named `pages`, available inside `ui-tests/src/main/java`)
    - Cucumber step definitions (create in package named `steps`, available inside `ui-tests/src/test/java`)
    - Cucumber feature files (create in package named `features`, available inside `ui-tests/src/test/resources`)

5. Execute the UI tests using the TestNG runners available in the package `runner` which can be found
   in `src/test/java/`. For example, right click and run `RunCucumberTest` file. This will execute all the features
   associated in the runner file in 5 parallel browsers. For single browser run make `parallel = false` in runner file
   but do not commit it

6. After executing the tests, the Automation Report will be generated in `/ui-tests/target/reports` location, providing
   detailed test execution results along with failure screenshots.

## Maven execution

- `mvn clean test` command will run all the features/tests available in `ui-tests` module in 5 parallel browsers

- Following are some more maven system variables designed for our framework and their usages

    - `mvn clean test -DthreadCount=1` command will run all the features/tests available in `ui-tests` module in single
      browser sequentially, by default 5 parallel browser run is enabled
    - `mvn clean test -Dbrowser=${browserName}` to run executions in different browsers, replace the ${browserName}
      value with edge, chrome or firefox to select the browser of your choice
    - `mvn clean test -Denv=${environment}` to run executions in different environment, replace the ${environment} value
      with dev, uat or prod to select the environment of your choice
    - `mvn clean test -DremoteRun=${remoteRun}` to run executions in remote driver, default value is false, replace the
      ${remoteRun} value to true, if you want to run it on remote, this command is specially for dockerized pipeline run
      so avoid using in local if your system does not have local docker compose properly setup
    - `mvn clean test -DexecutionXml=${executionXml}` to run executions specified testng xml, replace the
      ${executionXml} value with any testng xml file available in executionXmls folder of ui-tests module
    - `mvn clean test -Dtest-user.username=${username} -Dtest-user.password=${password}` to run executions by externally
      providing test username and password, replace ${username} with test username/email and ${password} with test
      password
    - `mvn clean test -Dsupervisor.username=${username} -Dsupervisor.password=${password}` to run executions by
      externally providing supervisor username and password, replace ${username} with supervisor username/email and
      ${password} with supervisor password
    - `mvn clean test -Dcucumber.filter.tags=${tagName}` to execute only the test which have given tagname on them,
      replace ${tagName} with the tag of your choice. For example, @smoke, @regression keep in mind the mentioned tags
      has to be present in feature file in order for compiler to pick associated test cases
    - `mvn clean test -Dbrowser=edge -Denv=uat -Dtest-user.username=cmduser -Dtest-user.password=cmdpass -Dsupervisor.username=superuser -Dsupervisor.password=superpass -Dcucumber.filter.tags=@smoke -DremoteRun=false`
      this is how everything comes together in a single maven command
