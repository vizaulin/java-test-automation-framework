# Java Test Automation Framework

Demo Java UI & API test automation framework (Selenium, REST-assured, TestNG).
Designed to showcase architecture, maintainable code, and integration with modern tooling.

## 📌 NOTES

- This repository is a **demonstration** of automation architecture and code quality.
- Not for direct execution.
- This project uses a private PostgreSQL database for test data.
- The database is not included in the public version.

## 🚀 Stack

**Languages & Frameworks**

- Java 21
- Selenium WebDriver
- REST-assured
- TestNG

**Infrastructure & Tooling**

- Docker Compose
- Selenium Grid
- Allure Reports
- HikariCP (PostgreSQL client)
- Jenkins (CI example)

## ⭐ Key features

- UI automation examples using Selenium.
- API automation examples (REST-assured)
- Page Object based structure (Pages, Components, BasePage)
- Helper layer for reusable flows (LoginHelper, MainHelper, TemplatesHelper)
- Data-driven autotests (API)
- DB client example (HikariCP) for reading test data (PostgreSQL)
- Config enum with environment switching
- Docker Compose (Selenium Grid + Allure) example
- Jenkinsfile example for CI pipeline

## 📁 Project Structure

```text
.
src
├── main
│   ├── java
│   │   └── project
│   │       ├── config                      # Environment configuration logic
│   │       ├── core                        # Framework Core (POJOs, Request specs, Page Objects)
│   │       │   ├── api                     # API Models & Requests
│   │       │   └── pages.selenium          # Selenium Page Objects & Components
│   │       ├── database.client             # PostgreSQL client (HikariCP)
│   │       ├── helpers                     # Business logic helpers (Login, Templates)
│   │       ├── managers                    # Managers for Pages and Helpers
│   │       ├── utils                       # Utilities (e.g., SleepUtils) 
│   │       └── webdriver                   # WebDriver initialization & management
│   └── resources                           # Global configs (log4j, framework props)
│
├── test
│   ├── java
│   │   └── autotests
│   │       ├── api                         # Executable API Tests & DataProviders
│   │       ├── selenium                    # Executable UI Tests
│   │       └── settings                    # Test Listeners & BaseTest classes
│   └── resources
│       └── allure.properties
│
├── docker-compose.yml                      # Docker container orchestration: Selenium Grid + Allure
├── Jenkinsfile                             # Example CI pipeline
├── pom.xml                                 # Maven dependencies
├── testng.xml                              # TestNG suite configuration
└── README.md
```

## 🏗 CI/CD

**Example Jenkinsfile included, demonstrating a simple pipeline:**

- Checkout project
- Maven build (example)
- Launch Docker containers and start Selenium Grid
- Run automated tests
- Publish Allure reports
- Stop and remove Docker containers

## ⚙ Configuration

- Default config: `config.properties` (contains placeholder values)
- Test data is stored in a private PostgreSQL database (not included in the repository)

## 👤 Author

Kostiantyn Vizaulin  
LinkedIn: https://linkedin.com/in/vizaulin
