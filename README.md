# Visual Testing with Applitools java using Selenium (Java)
This is a Java Mevan project with the dependencies:
  - Selenium
  - TestNG
  - WebDriverManager
  - Applitools

## How to run the code:
1. Open the projet with IntelliJ (or any other preferred IDE)
2. Locate the test files (under src/test/java folder)
3. Go to edit run configurations and click paramters tab
4. Create a variable named as "runnerType"
5. If you want to run on classic runner, set the value of the above parameter to "classic"
6. If you want to run on ultra-fast grid runner, set the value of the above parameter to "visual grid"
7. Now run the test

The Applitools Eyes integration related code-base is scripted in the TestBase class under the package "src/main/java" which supports running on classic runner and visual grid based on the parameter you set in run configurations.

A descriptive explanation about the code-base can be found on Medium.com: https://medium.com/geekculture/applitools-eyes-integration-with-selenium-java-173db0e391c7

## Feel free to Clone, Try-out & Optimize the code-base as your wish!
