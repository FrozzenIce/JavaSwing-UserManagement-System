
# JavaSwing User Management System 🧩🗄️

A Java Swing desktop application for basic user management backed by a MySQL database.

This project is intended for learners with **basic Java and database knowledge** who want to understand how a Swing application connects to MySQL using JDBC.

---

## Tech Stack ⚙️

* Java (Swing)
* MySQL
* JDBC (MySQL Connector/J)
* IntelliJ IDEA / Eclipse

---

## Prerequisites ✅

Make sure the following are installed:

1. JDK 8 or higher
2. MySQL Server (8.x recommended)
3. MySQL Workbench
4. [MySQL Connector/J (JDBC Driver)](https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-9.6.0.zip)

---

## Getting the Project 🧪

### Clone the repository

```bash
git clone https://github.com/FrozzenIce/JavaSwing-UserManagement-System.git
cd JavaSwing-UserManagement-System
```

### Manual copy

If you manually copy the project, ensure:

* `src/` folder is included
* JDBC driver `.jar` is added
* `config.properties` is created manually

---

## Opening the Project in IntelliJ IDEA 💡

1. Open IntelliJ IDEA
2. File → Open
3. Select the project root
4. Wait for indexing to complete

### Add MySQL JDBC Driver

If not already added:

1. File → Project Structure
2. Modules → Dependencies
3. `+` → JARs or Directories
4. Select `mysql-connector-j-x.x.x.jar`
5. Apply → OK

---

## MySQL Database Setup 🛠️

### Start MySQL Server

Ensure MySQL is running:

* Windows: Services → MySQL80
* Linux: `systemctl status mysql`
* macOS: MySQL Preferences / Homebrew

---

### Configure MySQL Workbench

1. Open MySQL Workbench
2. Click `+` next to **MySQL Connections**
3. Enter:

   * Connection Name: `local-mysql`
   * Hostname: `localhost`
   * Port: `3306`
   * Username: `root`
4. Test Connection and save

---

### Create the Database

```sql
CREATE DATABASE IF NOT EXISTS user_management_db;
```

---

### Create Tables

* Copy this exact code after creating a database to create a table

Example table:

```sql
CREATE TABLE `project_java`.`userdetails` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(60) NOT NULL,
  `Phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE);
);
```

> Table and column names must match the Java code exactly.

---

## Configuration Using `config.properties` 🔐

### Create `config.properties`

In the **project root directory** (same level as `src/`):

```properties
db.url=jdbc:mysql://localhost:3306/user_management_db
db.user=root
db.password=YOUR_PASSWORD_HERE
```

---

### Verify Property Keys in Code

Check for:

```java
getProperty("db.url")
getProperty("db.user")
getProperty("db.password")
```

Keys must match exactly.

---

### Recommended Practice

* Commit: `config.example.properties`
* Ignore: `config.properties`

Add to `.gitignore`:

```gitignore
config.properties
```

---

## Running the Application ▶️

1. Locate the main class (`Main.java` or similar)
2. Right-click → Run

---

## Alternative: Hard-Coding Database Credentials ⚠️

### Locate Connection Code

Search for:

```java
DriverManager.getConnection
```
in DBConnection

### Hard-code credentials

```java
String url = "jdbc:mysql://localhost:3306/user_management_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
String username = "root";
String password = "YOUR_PASSWORD_HERE";

Connection conn = DriverManager.getConnection(url, username, password);
```

Remove any `Properties` loading if present.

> ⚠️ Not recommended for real projects.

---

## Common Errors 🩺

### No suitable driver found

* JDBC driver not added

### Access denied for user

* Wrong username or password

### Unknown database

* Database not created or wrong name

### Table doesn't exist

* Required tables missing

---

## Notes 📌

* `config.properties` is not auto-generated
* Tables must be created manually
* Names must match code exactly





