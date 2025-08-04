# Car-Motorcycle-Web-Shop
A full-stack Java Dynamic Web Shop featuring admin and user roles. Users can browse, search, and add vehicles; admins manage vehicle data and statuses. Implements secure signup/login with bcrypt, AJAX-powered deletes, MVC architecture with Servlets, JSP, MySQL, and session-based access control.

# Car-Motorcycle-Web-shop

## Project Overview
This is a Java Dynamic Web Project implementing a full-featured web shop for vehicles with distinct admin and user roles.

- **Users** can search (by year, brand, type), and add new vehicles.
- **Admins** can add, edit, delete, and change the status of vehicles submitted by users.
- Secure authentication with signup and login using bcrypt for password hashing.
- AJAX-powered operations for seamless user experience.
- MVC architecture with clear separation of concerns:
  - DAO layer (`UserDao`, `VehicleDao`)
  - Servlets for controllers
  - JSP for views
- Session management preventing unauthorized access and disabling page caching after logout.
- MySQL database for persistent storage.
- Utility options to quickly initialize (`InitDB`) or empty (`EmptyDB`) the database.

## Technologies Used
- Java Servlets & JSP
- JDBC with MySQL
- BCrypt for password security
- AJAX for asynchronous operations
- MVC design pattern

## Features
- Role-based access: different views and permissions for admins and users.
- Vehicle management with CRUD operations.
- Secure user authentication.
- Responsive UI with JSP.
- Prevents caching to ensure session security.

---

Feel free to contribute or report issues!


