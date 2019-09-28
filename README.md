# wunderbackendtest

INTRODUCTION
------------

Thank you for downloading this project.  This download contains code to automate CRUD testcases of  dummyrestApi.


How to run:
--------------
1- simply clone it and run with 'mvn clean verify'. (you can also view beatuiflly generated reports on target/site/serenity/index.html)
2- you can also run it with TestRunner class.
3- In order to run specific feature file you just add specific feature file path in test runner for eg:
features={"src/test/resources/featurefile/Crud_Employee.feature"}
4- If you don't want to run few of them then you just need to uncomment "//,tags={"~@ignore"}" in test runner and also write "@ignore" on 
those scenarios in feature files. for eg

@ignore

Scenario Outline: verify that user is created ...

Technology Stack:
----------------
1-Java
2-RestAssured
3-Cucumber
4-Serenity (For reporting)
5-Junit
