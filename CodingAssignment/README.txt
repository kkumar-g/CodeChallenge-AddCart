1. How to Run this Project:
--------------------------------
a. Import this project as maven project
b. Open Testng.xml in IDE and run it as a "TestNg suite"

2. Test Flow
-------------------------------
a. When you run test suite, chrome browser will be opened(make sure chrome version 75+ is installed in your pc)
b. Amzon.com site will be opened
c. In the search box, keyword "Headphones" will be entered and search btn will be clicked
d. Based on the element locators, code will identify the 'best seller' proudcts in the first page(3 results are there as of now)
e. Code will capture the url of each product and navigate to the product details page
f. Add to cart button will be clicked
g. Back button will be clicked to go back to results page
h. This cycle continues untill all the prdocuts are added to the cart
g. Browser will be closed
h. A html report will be genered under ..\CodingAssignment\test-output folder. Report name is:emailable-report.html. Report contains the status of the test and the flow of the test
i. A video file will be created in the base directory. It can be played via VLC player or any other advance video player. This video demonstrates the test flow of which is being run of the browser.

3. Project Structure:
----------------------
A. src/main/java
	It has 4 packages
	i.com.amazon.BaseClass package: This contains BaseClass.Java class. Functions related to Browser intialization and closing the browser will be part of this class.
	ii. com.amazon.Pages package: Page object mode design pattern has been used in this project. So, this package contains 3 classes. Each class is a reference to a page
	iii. com.amazon.Properties package: This package contains properties file which will be used in the base class. properties of the test url, browser name will be mentioned here.
	iv. com.amazon.util package: All the utility functions which are independent of the test flow will be added here. Ex function: takescreenshot
B. src/test/java
	It has one package
	com.amazon.TestCases package: This contains one class which contains the test case 

4. Libraries/Framewroks/Tools used
----------------------------------------
a. TestNG
b. maven POM.xml
c. JRE 1.8
d. Eclipse
f. Chrome 75+