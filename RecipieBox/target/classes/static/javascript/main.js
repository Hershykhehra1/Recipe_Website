var userId = 0;
let nameOfUser;
let tempMessageId;

var mainContent = {

	init: function() {
		this.showLoginForm();
		this.addListener();
	},

	addListener: function() {
		$("#registerSubmit").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.registerForm();
		}.bind(this));

		$("#backToLogin").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.showLoginForm();
		}.bind(this));
		$("#backToRegister").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.showRegisterForm();
		}.bind(this));
		$("#loginSubmit").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.loginForm();
		}.bind(this));

	},

	showLoginForm: function() {
		$('.errors').text('');
		$("#registerForm").hide();
		$("#loginForm").show();

	},

	showRegisterForm: function() {
		$('.errors').text('');
		$("#loginForm").hide();
		$("#registerForm").show();


	},

	clear: function() {
		$('.errors').text('');
		$("#registerForm").hide();
		$("#loginForm").hide();

	},

	loginForm: function() {
		let formData = {
			"email": $('#loginEmail').val(),
			"password": $('#loginPassword').val()
		}
		$.ajax({
			url: '/api/Login',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(formData),
			dataType: 'json',
			success: function(data) {
				$('.errors').text('');
				userId = data;
				mainContent.clear();
				messageContent.init();

			}.bind(this),
			error: function(xhr, status, error) {
				var errors = xhr.responseJSON.message;
				console.error('Error logging in:', error);
				$('.errors').text(errors);
			}

		});
	},


	registerForm: function() {
		let formData = {
			"email": $('#registerEmail').val(),
			"userName": $('#registerName').val(),
			"password": $('#registerPassword').val()
		}
		$.ajax({
			url: '/api/register',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(formData),
			dataType: 'json',
			success: function() {
				mainContent.showLoginForm();
				$('.errors').text('');
			}.bind(this),
			error: function(xhr, error, status) {
				var errors = xhr.responseJSON.message;
				console.error('Error logging in:', error);
				console.log("xhr:" + xhr);
				console.log("status:" + status);
				console.log("error:" + error);
				$('.errors').text(errors);

			}
		});
	},

}



function getMonth(month) {
	const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
	return months[parseInt(month, 10) - 1];
}
function formatDate(messageDate) {
	messageDate = messageDate.split("-");
	let formattedDate = messageDate[2] + ' ' + getMonth(messageDate[1]) + ' ' + messageDate[0];
	return formattedDate;
}




let messageContent = {
	init: function() {
		this.showDisplayMessage();
		this.addListener();
		this.getUserId();
		this.autoGenerateTable();
		console.log(userId);
		console.log(nameOfUser);
		this.setUserName();

		$("#welcomeMessage").text("Welcome " + nameOfUser);
	},


	addListener: function() {

		$("#addSubmit").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.addMessageForm();
		}.bind(this));

		$("#editSubmit").on("click", function(event) {
			$('.errors').text('');
			var messageId = $('#editMessageForm').val()
			this.showDisplayMessage(messageId);
			event.preventDefault();
			this.editMessageForm(messageId)
		}.bind(this));

		$("#displayAdd").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			messageContent.showAddMessage();
		}.bind(this));

		$("#logout").on("click", function() {
			$('.errors').text('');
			this.userLogout();
		}.bind(this));

		$(".back").on("click", function(event) {
			$('.errors').text('');
			event.preventDefault();
			this.showDisplayMessage();
		}.bind(this));
		$("#messageTable").on("click", ".deleteMessageBtn", function(event) {
			$('.errors').text('');
			event.preventDefault();
			var messageId = $(this).closest("tr").data("message-id");
			this.deleteMessage(messageId);
		}.bind(this));

	},

	showDisplayMessage: function() {
		$('.errors').text('');
		$("#editMessageForm").hide();
		$("#addMessageForm").hide();
		$("#messageDisplay").show();
	},

	showAddMessage: function() {
		$('.errors').text('');
		$("#editMessageForm").hide();
		$("#messageDisplay").hide();
		$("#addMessageForm").show();
	},

	showEditMessage: function() {
		$('.errors').text('');
		$("#messageDisplay").hide();
		$("#addMessageForm").hide();
		$("#editMessageForm").show();
	},


	autoGenerateTable: function() {
		$.ajax({
			url: '/api/messagesDto',
			contentType: 'application/json; charset=utf-8',
			type: 'GET',
			success: function(data) {
				$('.errors').text('');
				this.createMessageList(data, "#messageTable");


			}.bind(this),
			error: function() {
				$("#messageTable").empty().text("No Data in table");
				$(".errorMessage").text("No Data in table");
			}
		});
	},


	createMessageList: function(messages, targetDiv) {
		let messageList = $(targetDiv);
		messageList.empty();
		let table = $("<table class='table'></table>");
		let tableHeader = $("<thead><tr><th>User Name</th><th>Recipe Name</th><th>Date</th><th>Number of Ingredients</th></tr></thead>");
		let tableBody = $("<tbody></tbody>");

		messages.forEach(function(message) {
			$("#welcomeMessage").text("Welcome " + nameOfUser);
			let row = $("<tr></tr>");
			row.attr('data-message-id', message.id);
			let nameCell = $("<td></td>").text(message.userName);
			let recipeNameCell = $("<td></td>").text(message.recipeName);
			let formattedDate = formatDate(message.date);
			let dateCell = $("<td></td>").text(formattedDate);

			let numIngredients = message.recepie.split(',').length;
			let numIngredientsCell = $("<td></td>").text(numIngredients);

			row.append(nameCell, recipeNameCell, dateCell, numIngredientsCell);
			row.click(function() {
				if ($(this).next().hasClass("expanded")) {
					$(this).next().remove();
				} else {
					let expandedRow = $("<tr class='expanded'></tr>");
					let expandedCell = $("<td colspan='4'></td>");

					let ingredientsList = $("<ul></ul>");
					let ingredients = message.recepie.split(',');
					ingredients.forEach(function(ingredient) {
						let listItem = $("<li></li>").text(ingredient.trim());
						ingredientsList.append(listItem);
					});

					expandedCell.append(ingredientsList);
					if (userId === message.userId) {
						let editButton = $("<button class='btn btn-primary editMessageBtn'>Edit</button>");
						let deleteButton = $("<button class='btn btn-danger deleteMessageBtn'>Delete</button>");
						editButton.click(function(event) {
							$(this).off('click');
							messageContent.showEditMessage();
							$("#editMessageContent").val(message.recepie);
							$("#editRecipeName").val(message.recipeName);
							$('input[name="messageId"]').val(message.id);
						});

						deleteButton.click(function(event) {
							$(this).off('click');
							messageContent.deleteMessage(message.id);
						});

						expandedCell.append(editButton, deleteButton);
					}

					expandedRow.append(expandedCell);
					$(this).after(expandedRow);
				}
			});

			tableBody.append(row);
		});

		table.append(tableHeader, tableBody);
		messageList.append(table);
	},


	messageDisplay: function() {
		$.ajax({
			url: '/api/messages',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(formData),
			success: function() {
				console.log('Message added successfully');
				mainContent.showDisplayMessage();
				$('.error').text('')
			},
			error: function(xhr, status, error) {
				console.error('Error adding message:', error);
			}

		});
	},

	addMessageForm: function() {
		let formData = {
			"recipeName": $("#addRecipeName").val(),
			"recepie": $("#addMessageContent").val(),
			"userId": userId,
			"userName": nameOfUser
		}

		$.ajax({
			url: '/api/addmessages',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(formData),
			dataType: 'json',
			success: function(data) {
				$('.error').text('');
				console.log('Message added successfully');
				$("#addMessageForm").hide();
				this.autoGenerateTable();
				messageContent.showDisplayMessage();

			}.bind(this),
			error: function(xhr, status, error) {
				console.error('Error adding message:', error);
			}
		});
	},

	editMessageForm: function() {
		var messageId = $("input[name='messageId']").val()
		let formData = {
			"recepie": $("#editMessageContent").val(),
			"recipeName": $("#editRecipeName").val()
		}

		$.ajax({
			url: '/api/editmessages/' + userId + '/' + messageId,
			type: 'PATCH',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(formData),
			dataType: 'json',
			success: function(result) {
				$('.errors').text('');
				console.log("Message edited successfully");
				this.autoGenerateTable();
				this.showDisplayMessage();
			}.bind(this),
			error: function(xhr, status, error) {
				console.error('Error editing message:', error);
				$('.errors').text("Sorry, you don't have permission to edit this message.");
			}
		});
	},

	deleteMessage: function(messageId) {
		$.ajax({
			url: '/api/deleteMessage/' + messageId,
			type: 'DELETE',
			data: { userId: userId },
			success: function() {
				$('.errors').text('');
				console.log('Message deleted successfully');
				this.autoGenerateTable();
			}.bind(this),
			error: function(xhr, status, error) {
				console.error('Error deleting message:', error);
				$('.errors').text("Sorry, you don't have permission to delete this message.");
			}
		});
	},




	showError: function(message) {
		$('#errorMessage').text(message);
	},

	getUserId: function() {
		$.ajax({
			url: '/api/getUser/' + userId,
			type: 'GET',
			contentType: 'application/json',
			success: function(data) {
				nameOfUser = data;
				console.log("inner function: " + nameOfUser);
			},
			error: function(xhr, status, error) {
				console.error('Error adding message:', error);
			}


		})

	},


	userLogout: function() {
		window.location.href = '/';
	},

}
$(document).ready(function() {
	mainContent.init();
});