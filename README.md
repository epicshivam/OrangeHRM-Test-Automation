# OrangeHRM Test Automation

## Overview
This project automates test cases for the OrangeHRM application using Selenium WebDriver and TestNG. It includes tests for login functionality, employee management, file uploads, and leave applications.

## Technologies Used
- **Java** (Test scripting language)
- **Selenium WebDriver** (Browser automation)
- **TestNG** (Test framework)
- **ChromeDriver** (Browser driver for Chrome)

## Prerequisites
1. Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html).
2. Install [Eclipse IDE](https://www.eclipse.org/downloads/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/).
3. Add **TestNG** plugin to the IDE.
4. Install **Google Chrome** and download the latest [ChromeDriver](https://chromedriver.chromium.org/downloads).
5. Set up environment variables for Java and ChromeDriver.

## Project Structure
```
project-folder/
├── src/
│   ├── testCases/java/OrangeHrm.java
│   ├── resources/
│   ├── pom.xml (if using Maven)
│   ├── testng.xml (for TestNG execution)
│   ├── UploadFileAutoScript.exe (for file upload automation)
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/orangehrm-automation.git
   ```
2. Open the project in **Eclipse** or **IntelliJ IDEA**.
3. Add the required dependencies in **pom.xml** (if using Maven).
4. Ensure ChromeDriver is in the system path.
5. Run the tests using TestNG.

## Test Cases
### 1. Login Tests
- **loginInvalidTest()**: Tests invalid login attempt.
- **loginTest()**: Tests valid login attempt.

### 2. Employee Management
- **addEmployee()**: Adds a new employee.
- **searchEmployeeById()**: Searches for an employee using an ID.
- **ListEmployees()**: Lists all employees with pagination.
- **deleteEmployee()**: Deletes an employee.

### 3. File Upload
- **fileUpload()**: Uploads a file using an automation script.

### 4. Leave Application
- **applyLeave()**: Applies for leave using predefined data.

## Running Tests
- Execute tests using TestNG by running the `testng.xml` file.
- To run a specific test case, set `enabled = true` for that test in **OrangeHrm.java**.

## Author
**Shivam Singh**

## License
This project is licensed under the MIT License.

