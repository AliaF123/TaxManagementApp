
# Tax Management Application

## Overview

This is a Java-based console application to manage tax rates for different municipalities over specific time periods. Users can query tax rates for a given municipality and date, and the system returns the applicable tax rate based on the priority of tax types (daily, weekly, monthly, yearly).

The application supports adding new tax records dynamically and uses clean code principles and object-oriented design for maintainability and extensibility.

---

## Features

- Store tax records for municipalities with validity periods.
- Support tax types: DAILY, WEEKLY, MONTHLY, YEARLY.
- Query tax rates by municipality and date.
- Priority-based tax rate selection (e.g., daily overrides monthly).
- Add new tax records for municipalities individually.
- Unit tests covering core functionality.
- Easy to extend with a real database if desired.

---

## Technologies

- Java 11
- Maven for build and dependency management
- JUnit 5 for testing
- SQLite JDBC driver (included but optional; current version uses in-memory/hardcoded data)

---

## Project Structure

```
taxapp/
│
├── README.md
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── java/com/taxapp/
│   │   │   ├── Main.java
│   │   │   ├── model/TaxRecord.java
│   │   │   ├── dao/TaxRecordDAO.java
│   │   │   └── service/TaxService.java
│   │   └── resources/
│   └── test/java/com/taxapp/
│       └── MainTest.java
```

---
## Application Design Summary 
Model:
TaxRecord class represents a tax entry with fields for municipality, start/end date, tax type, and rate.

Data Access Layer:
TaxRecordDAO handles all SQLite database interactions including creating tables, inserting, and querying tax records.

Service Layer:
TaxService encapsulates business logic for fetching applicable tax rates and adding new tax records, implementing tax type prioritization.

User Interface:
Main class provides a command-line interface (CLI) for querying tax rates and adding tax records.

Testing:
Unit tests (JUnit 5) ensure correct tax retrieval and priority behavior.

Build & Run:
Project uses Maven for dependency management, compilation, and packaging.

## How to Run

1. **Build the project**

   ```bash
   mvn clean package
   ```

2. **Run the application**

   ```bash
   java -jar target/taxapp-1.0-SNAPSHOT.jar
   ```

3. **Usage**

   - The application prompts for a municipality name and a date (format: YYYY-MM-DD).
   - It will display the applicable tax rate for the input.
   - You can add new tax records when prompted.

---

## Example

Input:

```
Enter municipality: Copenhagen
Enter date (YYYY-MM-DD): 2025-05-15
```

Output:

```
Applicable Tax Rate: 0.1 (DAILY)
```

---

## Testing

Run unit tests with:

```bash
mvn test
```

---

## Assumptions

- Tax records do not overlap with the same priority level for a municipality.
- Tax types have fixed priority: DAILY > WEEKLY > MONTHLY > YEARLY.
- Input dates are valid and formatted correctly.
- The application currently uses in-memory data but can be extended to use a database.

---

## Running app

 java -jar target/taxapp-1.0-SNAPSHOT.jar

