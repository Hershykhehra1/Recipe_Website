<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="javascript/main.js" type="text/javascript"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link rel="stylesheet" type="text/css" href="./css/Styles.css">
<title>Login</title>
</head>
<body>

<body>
	<div class="container">
		<div id="loginForm" class="form-container">
			<div class="header">Login</div>
			<div>
				<label for="loginEmail">Email:</label> <input type="text"
					id="loginEmail" name="loginEmail"
					class="form-control form-control-lg" autocomplete="given-name">
			</div>
			<div>
				<label for="loginPassword">Password:</label> <input type="password"
					id="loginPassword" name="loginPassword"
					class="form-control form-control-lg"
					autocomplete="current-password">
			</div>
			<div class="errors"></div>
			<div class="center">
				<button type="submit" class="btn btn-primary" id="loginSubmit">Login</button>
				<button class="btn btn-primary" id="backToRegister">Register</button>
			</div>
		</div>

		<div id="registerForm" class="form-container">
			<div class="header">Register</div>
			<div>
				<label for="registerEmail">Email:</label> <input type="text"
					id="registerEmail" name="registerEmail"
					class="form-control form-control-lg" autocomplete="given-name">
			</div>
			<div>
				<label for="registerName">Name:</label> <input type="text"
					id="registerName" name="registerName"
					class="form-control form-control-lg" autocomplete="given-name">
			</div>
			<div>
				<label for="registerPassword">Password:</label> <input
					type="password" id="registerPassword" name="registerPassword"
					class="form-control form-control-lg" autocomplete="new-password">
			</div>
			<div class="errors"></div>
			<div class="center">
				<button class="btn btn-primary" id="backToLogin">Login</button>
				<button type="submit" class="btn btn-primary" id="registerSubmit">Register</button>
			</div>
		</div>

		<div id="messageDisplay">
			<div id="welcomeMessage"></div>
			<div class="center">
				<button id="displayAdd" class="btn btn-primary">Add Recipe</button>
				<button id="logout" class="btn btn-primary">Logout</button>
			</div>
			<br />
			<div class="center" id="messageTable"></div>
			<div class="errors"></div>
		</div>

		<div id="addMessageForm" class="form-container">
			<div class="center">
				<input type="hidden" name="userName" value="">
				<div class="header">Add Recipe</div>
				<input type="text" id="addRecipeName"
					class="form-control form-control-lg" placeholder="Recipe Name">
				<textarea id="addMessageContent" name="addMessageContent"
					class="form-control form-control-lg"
					placeholder="Recipe Description"></textarea>
				<div class="errors"></div>
				<div class="center">
					<button class="btn btn-primary back" id="addBack">Back</button>
					<button class="btn btn-primary" id="addSubmit">Add Recipe</button>
				</div>
			</div>
		</div>



		<div id="editMessageForm" class="form-container">
			<input id="editMessageInput" type="hidden" name="editMessageForm"
				value="">
			<div class="header">Edit Recipe</div>
			<input type="text" id="editRecipeName"
				class="form-control form-control-lg" placeholder="Recipe Name">
			<input type="hidden" name="messageId" value="">
			<textarea id="editMessageContent" name="editMessageContent"
				class="form-control form-control-lg"
				placeholder="Recipe Description"></textarea>
			<div class="errors"></div>
			<div class="center">
				<button class="btn btn-primary back" id="editBack">Back</button>
				<button class="btn btn-primary" id="editSubmit">Edit Recipe</button>
			</div>
		</div>
</body>
</html>