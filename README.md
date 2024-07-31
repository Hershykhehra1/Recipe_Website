## Recipe Website Project Structure

The project is divided into several components:

### **Model**

- **Recipe.java**: This class represents a recipe with an ID, name, ingredients, and instructions. It includes validation for required fields and a method for comparing recipes.

### **Components**

- **DataComponent.java**: This component manages a list of recipe entries. It includes methods for adding, retrieving, and checking the existence of entries.

### **Controllers**

- **IndexController.java**: This controller handles requests for viewing, adding, updating, and deleting recipe entries. It includes methods for formatting recipe details and validating input.

### **Templates**

- **AddRecipe.ftlh**: A template for adding a new recipe entry. It includes a form for inputting the recipe name, ingredients, and instructions, with validation and error messages.
- **UpdateRecipe.ftlh**: A template for updating an existing recipe entry. It pre-fills the form with the current name, ingredients, and instructions of the recipe.
- **index.ftlh**: A template for displaying all recipe entries in a table format. It includes links for updating or deleting each entry.

### **How It Works**

1. **Adding a Recipe**: Users can navigate to the "Add Recipe" page, fill out the form, and submit it to add a new recipe to the collection.
2. **Editing a Recipe**: Each recipe in the collection has an "Update" link. Clicking this link takes the user to a form where they can update the name, ingredients, and instructions of the recipe.
3. **Deleting a Recipe**: Each recipe also has a "Delete" link. Clicking this link removes the recipe from the collection.
4. **Viewing Recipes**: The main recipe collection page displays all recipes in a table format, with options to update or delete each entry.

### **How to Run**

1. Make sure you have Java, Maven, and Spring installed in your IDE.
2. Run the Spring Boot app and navigate to http://localhost:8080/ to interact with the application.
