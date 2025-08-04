<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit User - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      body {
        background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
        color: #fff;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .form-box {
        background-color: rgba(0, 0, 0, 0.4);
        padding: 2rem;
        border-radius: 20px;
        box-shadow: 0 0 20px rgba(255, 255, 255, 0.1);
        width: 100%;
        max-width: 400px;
      }
      .form-box h2 {
        margin-bottom: 1.5rem;
        font-weight: 600;
        text-align: center;
      }
      .top-left {
        position: absolute;
        top: 20px;
        left: 20px;
      }
    </style>
  </head>
  <body>
    <a href="dashboard.html" class="text-light top-left">&larr; Back to Dashboard</a>

    <div class="form-box">
      <h2>Edit User</h2>
      <form method="post" action="/edituser">
        <input type="hidden" name="id" value="1"> <!-- ID korisnika -->

        <div class="mb-3">
          <input type="text" class="form-control" placeholder="Username" name="username" value="User1" required>
          <div id="usernameError" class="text-danger small mt-1"></div>
        </div>

        <div class="mb-3">
          <input type="password" class="form-control" placeholder="New Password" name="password">
          <div id="passwordError" class="text-danger small mt-1"></div>
        </div>

        <div class="mb-3">
          <input type="password" class="form-control" placeholder="Retype Password" name="retypePassword">
          <div id="retypePasswordError" class="text-danger small mt-1"></div>
        </div>

        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" value="true" id="isAdmin" name="isAdmin" checked>
          <label class="form-check-label" for="isAdmin">Is Admin</label>
        </div>

        <button type="submit" class="btn btn-light w-100">Update User</button>
      </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>