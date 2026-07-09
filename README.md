# DevOps Project Setup Report

## Project Overview

This document describes the complete setup performed for the DevOps environment.

### Infrastructure

| Server | Purpose | Services |
|---------|----------|----------|
| Application Server | Jenkins + Java + Application Deployment | Jenkins, JDK 21 |
| Database Server | MySQL Database | MySQL Server |

---

# Environment Details

## Application Server

### Operating System
- Ubuntu Server

### Installed Software
- OpenJDK 21
- Jenkins
- Maven (Configured in Jenkins)
- Stage View Plugin

### Application Ports

| Service | Port |
|----------|------|
| Jenkins | 8080 |
| Application | 8085 |

---

# Application Server Setup

## Step 1: Update Package Repository

```bash
sudo apt update
```

---

## Step 2: Install Java (OpenJDK 21)

```bash
sudo apt install openjdk-21-jdk-headless -y
```

Verify Java

```bash
java -version
javac -version
```

---

## Step 3: Install Tree Utility

```bash
sudo apt install tree
```

Verify

```bash
tree
```

---

## Step 4: Configure Sudo Permissions

```bash
sudo visudo
```

Configured sudo permissions where required.

---

## Step 5: Add Jenkins Repository Key

```bash
sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc \
https://pkg.jenkins.io/debian-stable/jenkins.io-2026.key
```

---

## Step 6: Add Jenkins Repository

```bash
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" \
https://pkg.jenkins.io/debian-stable binary/ | \
sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
```

---

## Step 7: Update Repository

```bash
sudo apt update
```

---

## Step 8: Install Jenkins

```bash
sudo apt install jenkins
```

---

## Step 9: Retrieve Jenkins Initial Password

```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

---

# Jenkins Configuration

## Access Jenkins

```
http://<Application_Server_Public_IP>:8080
```

---

## Unlock Jenkins

Used the initial administrator password obtained from:

```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

---

## Installed Jenkins Plugins

- Maven Integration Plugin
- Pipeline Stage View Plugin

---

## Configured Global Tool

### Maven

Navigate to

```
Manage Jenkins
    ↓
Global Tool Configuration
    ↓
Maven
```

Configured Maven installation for pipeline builds.

---

# Database Server Setup

## Step 1: Update System

```bash
sudo apt update
sudo apt upgrade -y
```

---

## Step 2: Install MySQL

```bash
sudo apt install mysql-server -y
```

---

## Step 3: Verify Installation

```bash
sudo mysql --version
```

---

## Step 4: Login to MySQL

```bash
sudo mysql
```

---

## Step 5: Create Remote Root User

```sql
CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;

EXIT;
```

---

## Step 6: Enable Remote Connections

Open MySQL configuration file

```bash
sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
```

Locate

```text
bind-address = 127.0.0.1
```

Change to

```text
bind-address = 0.0.0.0
```

---

## Step 7: Restart MySQL

```bash
sudo systemctl restart mysql
```

---

# Connectivity

## Application Server

Runs:

- Jenkins
- Java Application

Application Port

```
8085
```

---

## Database Server

Runs:

- MySQL

Default Port

```
3306
```

Remote access enabled.

---

# Jenkins Pipeline

## Build Tool

- Maven

Used Maven to:

- Clean project
- Compile source code
- Package application
- Generate JAR/WAR artifact

Typical Maven Command

```bash
mvn clean package
```

---

## Jenkins Pipeline Stages

The following stages were executed in the Jenkins Pipeline:

### Stage 1

Checkout Source Code

Purpose:

- Pull project source code from the Git repository.

---

### Stage 2

Build

Purpose:

- Compile Java source code.
- Resolve Maven dependencies.

Command

```bash
mvn clean compile
```

---

### Stage 3

Test

Purpose:

- Execute unit tests.

Command

```bash
mvn test
```

---

### Stage 4

Package

Purpose:

- Generate deployable artifact.

Command

```bash
mvn package
```

---

### Stage 5

Deploy

Purpose:

- Deploy generated artifact to the application server running on port **8085**.

---

# Stage View Plugin

Installed Jenkins Stage View Plugin.

Purpose:

- Visualize each pipeline stage.
- Monitor build progress.
- Identify failed stages.
- Improve pipeline readability.

---

# Tools Used

| Tool | Purpose |
|------|----------|
| Ubuntu Server | Operating System |
| OpenJDK 21 | Java Runtime |
| Jenkins | CI/CD Automation |
| Maven | Build Tool |
| MySQL | Database |
| Git | Version Control |
| Stage View Plugin | Pipeline Visualization |

---

# Network Ports

| Port | Service |
|------|----------|
| 22 | SSH |
| 8080 | Jenkins |
| 8085 | Java Application |
| 3306 | MySQL |

---

# Project Workflow

```
Developer
     │
     ▼
Git Repository
     │
     ▼
Jenkins
     │
     ▼
Checkout Code
     │
     ▼
Maven Build
     │
     ▼
Run Tests
     │
     ▼
Package Application
     │
     ▼
Deploy Application
     │
     ▼
Application Server (8085)
     │
     ▼
MySQL Database Server (3306)
```

---

# Outcome

- Successfully configured an Application Server with Jenkins and Java.
- Installed and configured OpenJDK 21.
- Installed Jenkins and unlocked the administrator account.
- Configured Maven as the build tool in Jenkins.
- Installed the Stage View Plugin for pipeline visualization.
- Installed and configured MySQL on a dedicated Database Server.
- Enabled remote MySQL access.
- Configured the application to communicate with the remote database.
- Successfully deployed the Java application on port **8085** through the Jenkins CI/CD pipeline.

---

# Author

**Prepared By:** sak_shetty

**Role:** DevOps Engineer