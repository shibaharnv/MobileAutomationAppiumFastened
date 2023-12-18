# Mobile Test Automation Framework for Fastned App
## Using Appium, TestNG, and Maven for Seamless Mobile Testing Experiences



This test automation framework is designed for testing the FastNed mobile application using Appium, TestNG, and Maven. It facilitates the seamless execution of mobile test scenarios, ensuring robust and efficient testing of the FastNed app.



## Technology Stack

- Appium: Mobile application automation tool for testing Android and iOS applications.
- TestNG: Testing framework for Java.
- Maven:Build and project management tool.
- Java: Programming language used for test script development.
- Android: Mobile operating system for Android devices.

## Prerequisites
- Java 11
- Maven 3.9.0
- Git 2.33.0
- Android Platform.Version=14
- TestNG: 7.8.0
- FastNed APk file:6.10.5
  


## The project directory structure

```bash
+ reports                             Contains extent reports index.html file which shows the test results
src
 + main
    + java                          
      + nl.fastned                               
          + pageObjects.android           Contains page objects for Android application           
          + utils                         Contains utility classes like appium utils.
 + test
    + java                          
      + nl.fastned                   
        + TestUtils                       Contains utility classes for test automation like Email Test,ExtentReporterNG,Listeners.
        + BaseTest.Java                   Base test class with common setup and teardown
        + FastNedPageObjectTest.java      Test class for FastNed Android application
      + resources                   
         + data.properties               Configuration properties file
         + Fastned_6.10.5_Apkpure.apk     FastNed application APK file

+ jenkins.war                          Jenkins WAR file for Jenkins server setup
+ pom.xml                               Project Object Model file for Maven configuration
+ Regression_testng.xml                TestNG XML file for regression test suite configuration
+ Smoke_testng.xml                     TestNG XML file for smoke test suite configuration

``` 


### PreSetp up

- Download Android studio and inside android studio set up an emulator .Update these values ipAddress=127.0.0.1 ((accessible from the same host))
  port :4723(Appium server will listen for connections)
  AndroidDeviceName=(Name of emulator in android studio)
  Android_Home=(Android sdk path)
  Java_Home=(Java home path)
  Main_Js_Path=(Appium main.js) path in /src/test/resources/data.properties


## Installation and Test Execution

- Clone the repository with below command

```
git clone  https://gitlab.com/fastned-recruitment/qa/08122023-shibahar-nagarajan

```


Open the project in any IDE Eclipse/IntelliJ.

Navigate to "fastned" folder where we have pom.xml


Run the following command in Terminal and build the project. Maven will clean the project, compile the source code, run any necessary plugins, package the artifacts, and install them into the local Maven repository—all without executing tests.

```
$ mvn clean install -DskipTests=true
```

## Execute Tests in Local:

#### Tags explanation

```bash
  - Groups.                       -Usage
  smoke                        Smoke testing for the FastNed mobile app. Performs a series of actions such as clicking on buttons, entering text, selecting stations, and verifying station details.
  regression                   For demo purpose i have placed a testcase which will get failed after execution so that we can see the failure report
```


Run the below command where "-DRecipientList" parameter value can be updated to your email id.

Extent report path will be sent to the mentioned email id.

#### maven execution command :

```mvn test -PSmoke -DRecipientList="youremailid@domain.com"```


##  Executing testcases in CI/CD - Jenkins Integration

- Jenkins can be started by running the below command from the project directory

```java -jar jenkins.war```

- Jenkins will be started in localhost:8080 and create a free style project and configure the project and set this maven command to trigger the testcases

```test -PSmoke -DRecipientList="youremailid@domain.com"```

- Please refer screenshots for Jenkins CI/CD execution




##  Test Result Reports -Extent Reports


After executing tests, you can find the Extent Reports in the reports folder. You will see index.html that can be opened in chrome.Refer below screenshot


<img width="1920" alt="Screenshot 2023-12-18 at 10 16 56" src="https://github.com/shibaharnv/AzureDevops/assets/65211677/c4137744-3c52-440b-9fef-92642516b75c">



## Screenshots in Reports


In case of test failures, screenshots are attached to the reports, providing visual context to identified issues.


##  Email Integration


Extent report path will be sent via mail.Please refer the screenshot below





Thanks.

Incase of any queries reach out to shibaharn@gmail.com







