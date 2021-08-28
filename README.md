## About

Api created to manage website dedicated to solve quizzes. All quizzes, users and completion time ale stored in the H2 database. All endoints are secured and tolerant of user wrong input. First step for user is to register. Otherwise user wouldn't be able to use the app properly of ``401 (Unauthorized)`` reply. After registration user should put his account credentials into HTTP request header based on Basic Auth. User can create his own quizzess, solve other user's quizzes and delete only his own quizzes. Full API endopints are presented below.

## API endpoints:

``/api/register`` - Accepts POST requests. In the request body user puts "email" and "password". It is used to authenticate concrete user.

``/api/quizzes`` - Accepts GET requests. It is used to display particular page of all stored quizzes. In the request parameters should be located page parameter which describes with page of all quizess user wants to display. Default page size is set to 10.

``/api/quizzes`` - Accepts POST requests. Endpoint to add new quizz. In the request body user puts all question's details in format: 
`` { "title": "", "text": "", "options": [], "answer": [] }``. Answer could be empty or null because some questions musn't have correct answer. Other fields cannot be empty.

``api/quizzes/completed`` - Accepts GET requests. Endpoint returns particular page of solved quizzes and completion time. In request parameters user should specify page number which should be displayed. Default page value is set to 0 and page size to 10.

``api/quizzes/{id}`` - Accepts GET requests and return particular quizz based on id specified in path variable. If question doesn't exists response contains ``404 (Not found)`` status code.

