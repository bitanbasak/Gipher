## Gipher - Gif For All

## Problem Statement
Build a system to search for gifs or stickers, open the gif/sticker, add gif/sticker to user favourite list, comment on gif, recommend gifs.
## Requirements
The application needs to fetch gif data by registering with the following API link and get API Key required to call the API.
https://developers.google.com/gif/docs/v1/getting_started

-Example API:
		https://developers.google.com/gifs/docs/v1/getting_started		
	- A frontend where the user can register/login to the application. The login page should have a link for registration using which the user
	should be able to register. On Successful registration the user should be taken to the login page.
	- Proper navigation links for all the pages should be available within pages
	- Error handling should be implemented across pages. Appropriate messages should be displayed for the same.
	- Success messages should be displayed for the User Registration.
	- Logout feature should be implemented.
**User can add a gif to favourite list and should be able to view the favourite list.**

**User can recommend a gif to recommendation list and should be able to view global recommendations.**

## Modules
### UI (User interface) - should be able to
1. List trending gifs .
2. View or open a gif .
3. Search for a gif/sticker.
4. Add a gif into favourite list.
5. View favourite gifs.
6. View global recommendations from recommendations list
7. View gifs by categories (Actions, Anime,Cartoons,Emotions,Food/Drinks,Gaming,Holidays/Greetins,Games) .
8. User gets a welcome mail on registration.
9. User can comment on gifs after logging in. And the comments appear in the order of likes to that particular comment.
10. UI should be appealing and user friendly.

### AuthenticationService - should be able to manage users account.
### googleapiService - should be able to store all the favourite/recommendation list for a specific user
## Tech Stack
- Spring Boot
- MySQL, MongoDB
- Angular 8
- Docker, Docker Compose
- Java

## Flow of Modules
### Building frontend
1. Register/Login.
2. Show list of gifs based on trending - populating from external API.
3. Show gif details - populating from external API.
4. Build a view to show favorite list.
5. Add Pagination for trending/favorite/recommendation list.
6. Search for gifs by any keyword.
7. Display gif details
	for example - Gif name, gif/sticker.
8. Create a view for displaying gifs on search.
- Using Services to populate these data in views
- Stitching these views using Routes and Guards
- Unit Tests should be created for the Components and Services
- E2E scripts using protractor should be created for the functional flows
- Implement CI - continuous Integration (use,.gitlab-ci.yml)
- Dockerize the frontend (create dockerfile,docker-compose.yml and get it executed through docker compose)

### Note: FrontEnd and all the backend services should be dockerized
and run through docker-compose

### Building the UserService
- Creating a server in Spring Boot to facilitate user registration and login with MySQL as backend. Upon login, JWT token has to be generated. 	It has to be used in the Filters set in other services.- Writing swagger documentation.
- Unit Testing of the entire code which involves the positive and negative scenarios.
- Implement continuous Integration CI ( use .gitlab-ci.yml).
- Dockerize the UserService (create dockerfile, docker-compose.yml and get it executed through docker compose).

### Building the favourite Service
- Building a server in Spring Boot to facilitate CRUD operation over favorite gifs stored in MongoDB. JWT Filter should be
  applied for all the API calls of the googleapiservice. JWT token should be used to authorize the user access to all the resources.
- Writing Swagger Documentation.
- Write Unit Test Cases and get it executed.
- Implement CI - continuous Integration ( use .gitlab-ci.yml).
- Dockerize the favorite Service(create dockerfile and update the docker-compose.yml).

### Building the recommendation Service
- Building a server in Spring Boot to facilitate CRUD operation over recommended gifs stored in MongoDB. JWT Filter should be
  applied for all the API calls of the googleapiservice. JWT token should be used to authorize the user access to all the resources.
- Writing Swagger Documentation.
- Write Unit Test Cases and get it executed.
- Implement CI - continuous Integration ( use .gitlab-ci.yml).
- Dockerize the favorite Service(create dockerfile and update the docker-compose.yml).

### Building the comment Service
- Building a server in Spring Boot to facilitate CRUD operation over comments stored in MongoDB. JWT Filter should be
  applied for all the API calls of the googleapiservice. JWT token should be used to authorize the user access to all the resources.
- Writing Swagger Documentation.
- Write Unit Test Cases and get it executed.
- Implement CI - continuous Integration ( use .gitlab-ci.yml).
- Dockerize the favorite Service(create dockerfile and update the docker-compose.yml).

### Demonstrate the entire application
1. Make sure all the functionalities are implemented.
2. Make sure both the UI (Component and Services) and server side (For all layers) codes are unit tested.
3. All the Services are up and running using docker (Dockercompose should be used for running them)
4. E2E tests should be executed and shown.
