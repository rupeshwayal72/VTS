# VTS
VTS_AUTOMATION_TEST
Purpose:
-- To build a Hybrid Test Automation Framework which can be used across different web based applications. In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly used for another web application without spending any extra effort. With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.

Tools
--
Eclipse Java EE
Java 7+
WebDriver 3.14
Apacha POI
Apache Maven
JUnit 4.10
TestNG
XML
Log4j

For step by step Selenium setup,Please go through
https://www.guru99.com/installing-selenium-webdriver.html

For step by step TestNG setup,Please go through
https://www.toolsqa.com/testng/install-testng/#:~:text=The%20following%20installation%20process%20uses,click%20the%20%E2%80%9CAdd%E2%80%9D%20button.


Ignore the external jar liabraries part because we used Apache maven for dependency management.
Just right click on the project and go to maven-->update maven, that's it.

After Successful TestNG insatllation,Right click on TestNG.xml file of this project and select execute as TestNG suite option.
For HTML Test report,check VTS_Automation_Test_Report.HTML



