# RestAssured API Automation Testing Framework
This project provides a foundation for automated API testing using RestAssured and Maven, along with Allure reporting for comprehensive test results visualization.
## Features
- Parametrized testing
- Allure Reporting
- Parrallel exection capablity
- Test Lifecycle Hooks logging
- Logging (Log4J)
- Retry failed test cases
- Run as a Docker Service to execute the test and view the reports with [Allure Docker Service](https://github.com/fescobar/allure-docker-service)   

## Getting Started - Docker Service (Option 1)
1- Clone or Download the Project

2- Build the docker image
```
docker build -t restassured:parabank .
```  
3- Compose Docker Service
```
docker compose up
```
4- Generate the report through Allure Docker Service UI 

http://localhost:5252/
## Getting Started - Bare Metal (Option 2)
### Prerequisites
-Java 11

-Maven 4 

-Allure Report ([Installation guide]([url](https://allurereport.org/docs/gettingstarted-installation)))

### Steps

1- Clone or Download the Project

Run Tests:
Navigate to the root directory of the project and run
```
mvn clean test
```     

2- Generating and Serving Allure Report
Navigate to the /target dir in the command line and run:
```
allure generate
allure serve
```   

## Test Properties
The project utilizes a properties file (src/test/resources/test.properties) 

properties
    
    BASE_API_URL=https://parabank.parasoft.com/parabank/services

    BASE_WEB_URL=https://parabank.parasoft.com/parabank
    
    TEST_FILE_PATH =./src/test/resources/test.xlsx
    
    REQUEST_ALLOWED_TIME_MS=5000
    
    MAXIMUM_RETRIES=1

## Project Structure


```
  -src: 
     |main: 
     |
     | _test:
          |_ java:
          |  | - commons: This package contains base classes
          |  | - endpoints: This package contains classes specific to the API endpoints that are in the scope of the test.
          |  | - models: This package contains Java classes representing the data models used in the API and test data
          |  | - tests: This package contains your actual test cases, such as Test Transfer.java.
          |  | - utils: This package likely contains utility classes and helpers
          |  | - target: this is a directory generated by Maven and it will have the test reports and build artifacts
          |  | - pom.xml: Maven project model            
          |-resources: has the test.properties file and test.xlsx (test data) file
```            


## Screenshots
**Allure Report Service**
![App Screenshot](https://github.com/fsalamah/restAssuredParabank/blob/main/reportservice.png?raw=true)

