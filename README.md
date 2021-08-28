## About

API was created to manage the website which was dedicated to solving quizzes. All quizzes, users and completion time are stored in the H2 database. All endpoints are secured and tolerant of user wrong input. The first step for the user is to register. Otherwise, a user wouldn't be able to use the app properly of ``401 (Unauthorized)`` reply. After registration user should put his account credentials into the HTTP request header based on Basic Auth. Users can create their quizzes, solve other user's quizzes and delete only their quizzes. Full API endpoints are presented below.

## API endpoints:

``/api/register`` - Accepts POST requests. In the request, the body user puts "email" and "password". It is used to authenticate the concrete user.

``/api/quizzes`` - Accepts GET requests. It is used to display a particular page of all stored quizzes. In the request, parameters should be located page parameter which describes with a page of all quizzes user wants to display. The default page size is set to 10.

``/api/quizzes`` - Accepts POST requests. Endpoint to add new quizz. In the request body user puts all question's details in format: 
`` { "title": "", "text": "", "options": [], "answer": [] }``. Answer could be empty or null because some questions musn't have correct answer. Other fields cannot be empty.

``/api/quizzes/completed`` - Accepts GET requests. The endpoint returns a particular page of solved quizzes and completion time. In request parameters, a user should specify the page number which should be displayed. The default page value is set to 0 and page size to 10.

``/api/quizzes/{id}`` - Accepts GET requests and return a particular quiz based on id specified in path variable. If a question doesn't exist response contains the ``404 (Not found)`` status code.

``/api/quizzes/{id}`` - Accepts DELETE requests. An endpoint is responsible for deleting a particular quiz. The user is not allowed to delete a quiz that he didn't create. Otherwise, the response will contain the ``403 (Forbbiden)`` status code. 

``/api/quizzes/{id}/solve`` - Accepts POST requests and is used to solve particular quiz. In the request body, a user should put his answers according to the question in the format ``{ "answer": [] }``.
