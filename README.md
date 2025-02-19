# **Nexon - Social Network**  

🚀 **Nexon** is a modern and scalable social network designed to connect people through posts, likes, comments, and follows. Built with **Spring Boot** and **React**, it provides a seamless user experience with real-time notifications and authentication security.  

---

## 📌 **Table of Contents**  

- [Technologies Used](#technologies-used)
- [Installation & Setup](#installation--setup)
- [Authentication & Security](#authentication--security)
- [API Documentation](#api-documentation)
- [Entity-Relationship Diagram](#entity-relationship-diagram)
- [Frontend Repository](#frontend-repository)
- [Demo Video](#demo-video)

---

## ⚙️ **Technologies Used**  

### **Backend (Spring Boot)**  
- **Java 17**  
- **Spring Boot 3**  
- **Spring Security & JWT**  
- **Spring Data JPA (Hibernate)**  
- **PostgreSQL**  
- **Swagger (OpenAPI 3.0)**  
- **WebSockets for real-time notifications**  

### **Frontend (React)**  
- **React 18**  
- **React Router**  
- **React Toastify**  
- **Axios for API requests**  
- **Styled Components**  
- **Dark/Light Mode Support**  

---

## 🛠 **Installation & Setup**  

### **Backend Setup**  

1️⃣ Clone the repository:  
```bash
git clone https://github.com/nicolasm9808/nexon.git
cd Nexon-Backend
```  

2️⃣ Configure the database in `application.properties`:  
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nexon
spring.datasource.username=your_username
spring.datasource.password=your_password
```  

3️⃣ Run the application:  
```bash
mvn spring-boot:run
```  

By default, the backend runs on **http://localhost:8081**  

### **Frontend Setup**  

1️⃣ Clone the frontend repository:  
```bash
git clone https://github.com/nicolasm9808/nexon-frontend.git
cd nexon-frontend
```  

2️⃣ Install dependencies:  
```bash
npm install
```  

3️⃣ Start the development server:  
```bash
npm start
```  

The frontend will be available at **http://localhost:3000**  

---

## 🔐 **Authentication & Security**  

Nexon uses **JWT (JSON Web Tokens)** for secure authentication. Users must register and log in to access protected endpoints.  
- **Password hashing:** Spring Security with BCrypt  
- **Token-based authentication:** JWT stored in HTTP-only cookies  
- **Authorization:** Role-based access control for sensitive actions  

Example: Secure endpoint requiring authentication:  
```http
GET /api/users/me
Authorization: Bearer <your_jwt_token>
```  

---

## 📑 **API Documentation**  

The API is fully documented using **Swagger**. You can explore all endpoints here:  

🔗 **[API Documentation (Swagger UI)](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/nicolasm9808/nexon/main/docs/LuisNicolasMendezPalacios-nexon-1.0.0.yaml)**  

---

## 🗂 **Entity-Relationship Diagram**  

Below is the database schema for Nexon:  

![Entity-Relationship Diagram](https://github.com/nicolasm9808/nexon/blob/main/docs/ERD.png)


---

## 🔗 **Frontend Repository**  

The frontend code is available at:  
🔗 [Nexon Frontend Repository](https://github.com/nicolasm9808/nexon-frontend)  


## 🎥 **Demo Video**  

A video demonstration of the Nexon platform is available here:  

📹 **[Watch Demo Video](https://drive.google.com/drive/folders/1Je47R5TEBaCrys_SAzHvSBJHyTS_1VrT?usp=sharing)**  

---

## Contribution

Feel free to fork the repository and submit pull requests!

## License

This project is licensed under the MIT License.

## Contact

For any inquiries or issues, contact [Nicolás Méndez](https://github.com/nicolasm9808).

