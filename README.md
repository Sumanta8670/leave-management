# 🚀 Leave Management System (Backend)

A **Spring Boot + PostgreSQL** backend for managing employee leave requests.  
It provides REST APIs to **add employees, apply for leave, approve/reject leave, and check leave balances**.  
Built with scalability and clean architecture in mind.

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA + Hibernate**
- **PostgreSQL 17**
- **Maven**
- **Swagger (Springdoc OpenAPI)**
- **Docker & Docker Compose (optional for local setup)**
- **JUnit & Mockito (testing)**

---

## ⚙️ Setup Steps

### 🔹 Local Development

1. **Clone the repo**
   ```bash
   git clone https://github.com/<your-username>/leave-management.git
   cd leave-management
2. **Configure Database**
- **Create a PostgreSQL database named leave_db**
   ```sql
  CREATE DATABASE leave_db;
3. **Update application.properties
   Local default:**
   ```ini
   spring.datasource.url=jdbc:postgresql://localhost:5432/leave_db
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
4. **Run the Application**
   ```bash
   mvn spring-boot:run
- The backend will start at 👉 http://localhost:8080
5. **Access Swagger UI**
    ```bash
   http://localhost:8080/swagger-ui.html
6. **Run with Docker**
    ```bash
   docker-compose up --build
- This will start:leave_app (Spring Boot app) on port 8080 & leave_db (Postgres DB) on port 5432

## 🌐 API Endpoints
### 👤 Employees

- POST /api/v1/employees → Create Employee

- GET /api/v1/employees/{id}/balance → Get Leave Balance

### 📝 Leave Requests

- POST /api/v1/employees/{empId}/leaves → Apply for Leave

- POST /api/v1/leaves/{leaveId}/approve → Approve Leave

- POST /api/v1/leaves/{leaveId}/reject → Reject Leave

- GET /api/v1/employees/{empId}/leaves → Get Employee’s Leaves

### 📖 Detailed request/response examples are available in Swagger UI.
## ✅ Edge Cases Handled

- ❌ Leave before employee’s joining date

- ❌ End date earlier than start date

- ❌ Leave days > available balance

- ❌ Overlapping leave requests

- ❌ Employee not found

- ❌ Duplicate email while creating employee

- ⚡ Concurrency handling with optimistic locking
## 📌 Assumptions

- One company-wide leave policy applies to all employees.

- Annual leave balance is manually assigned when creating an employee.

- HR/Admins are responsible for approving/rejecting leaves.

.

## 🚀 Potential Improvements

- Add user roles (Employee vs Admin/HR) with Spring Security & JWT.

- Add email/Slack notifications for leave approvals/rejections.

- Support carry-forward leave balances across years.

- Add analytics reports (leave usage trends).

- Deploy with Kubernetes for large scale (500+ employees).
## 🧪 Running Tests
1. 
    ```bash
    mvn test
