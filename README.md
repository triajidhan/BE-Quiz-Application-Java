# BE-Quiz-Application

This is a mini project for a Quiz App on a site. Here Candidate users can take a quiz with 5 random questions provided by the application. Then you can immediately see the score obtained by the User Candidate.

- There're 5 Model/Entity.
  - User.
  - Question.
  - Answer.
  - Submission.
  - Report
- User has two roles namely Admin and User Candidate.
- User Candidate can take the quiz more than once.
- UUID is used for id/primary key.

## How To Use

- Clone
```bash
git clone https://github.com/triajidhan/BE-Quiz-Application.git
```
- Create a PostgreSQL Database with the name "quiz".
  - Make sure the database setting at application.properties are correct.
  - You can use other name, but you need to change the datasource in the application.properties.
- Run the project. Upon the first run, the tables will automatically created in the previously created database.
- Register user candidate using ``` POST /user-candidates ```, if you want to register as admin, fill in ``` userRole: "ADM" ```. If as a candidate user, fill in  ``` userRole: "CDT" ```
- Then login using ``` POST /login ```

## API Documentation
Swagger Open API Documentation. To use this, you must run the application.
```
http://localhost:8080/swagger-ui/index.html
```
### Login
#### Request
```http
POST /login
```
#### Request Body
```
{
  "userName": "string",
  "password": "string"
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userName`      | `string` | **Required**. User username|
| `password`      | `string` | **Required**. User password|

#### Response
```
{
  "id": "string",
  "fullName": "string",
  "token": "string",
  "userName": "string",
  "email": "string"
}
```
-------
### Users
#### Request
```http
GET /users
```
#### Response
```
{
  "data": [
    {
      "id": "string",
      "fullName": "string",
      "userName": "string",
      "email": "string",
      "roleName": "string",
      "roleCode": "string",
      "version": 0,
      "isActive": true
    }
  ]
}
```
-------
#### Request
```http
GET /users/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. User id to fetch|

#### Response
```
{
  "data": {
    "id": "string",
    "fullName": "string",
    "userName": "string",
    "email": "string",
    "roleName": "string",
    "roleCode": "string",
    "version": 0,
    "isActive": true
  }
}
```
-------
#### Request
```http
POST /users
```
#### Request Body
```
{
  "fullName": "string",
  "userName": "string",
  "password": "string",
  "email": "string",
  "roleCode": "string"
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fullName`      | `string` | **Required**. User fullname|
| `userName`      | `string` | **Required**. User username|
| `password`      | `string` | **Required**. User password|
| `email`      | `string` | **Required**. User email|
| `roleCode`      | `string` | **Required**. User role code(Admin = "ADM", User Candidate = "CDT")|

#### Response
```
{
  "data": {
    "id": "string"
  },
  "message": "string"
}
```
-------
#### Request
```http
PUT /users
```
#### Request Body
```
{
  "id": "string",
  "fullName": "string",
  "version": 0,
  "isActive": true
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. User candidate id to edit|
| `fullName`      | `string` | **Required**. User candidate fullname|
| `version`      | `integer` | **Required**. User candidate version|
| `isActive`      | `boolean` | **Required**. User candidate is active|

#### Response
```
{
  "data": {
    "id": "string",
    "version": 0
  },
  "message": "string"
}
```
-------
#### Request
```http
DELETE /users/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. User candidate id to fetch|

#### Response
```
{
  "message": "string"
}
```
-------

### Questions
#### Request
```http
GET /questions
```
#### Response
```
{
  "data": [
    {
      "id": "string",
      "question": "string",
      "version": 0,
      "isActive": true,
      "answers": [
        {
          "id": "string",
          "answer": "string",
          "isAnswer": true,
          "version": 0,
          "isActive": true
        }
      ]
    }
  ]
}
```
-------
#### Request
```http
GET /questions/random
```
#### Response
```
{
  "data": [
    {
      "id": "string",
      "question": "string",
      "version": 0,
      "isActive": true,
      "answers": [
        {
          "id": "string",
          "answer": "string",
          "isAnswer": true,
          "version": 0,
          "isActive": true
        }
      ]
    }
  ]
}
```
-------
#### Request
```http
GET /questions/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. Question id to fetch|

#### Response
```
{
  "data": {
    "id": "string",
    "question": "string",
    "version": 0,
    "isActive": true,
    "answers": [
      {
        "id": "string",
        "answer": "string",
        "isAnswer": true,
        "version": 0,
        "isActive": true
      }
    ]
  }
}
```
-------
#### Request
```http
POST /questions
```
#### Request Body
```
{
  "question": "string",
  "answers": [
    {
      "answer": "string",
      "isAnswer": true
    },
    {
      "answer": "string",
      "isAnswer": true
    },
    {
      "answer": "string",
      "isAnswer": true
    },
    {
      "answer": "string",
      "isAnswer": true
    }
  ]
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `question`      | `string` | **Required**. Question|
| `answers`      | `array` | **Required**. Multiple choice group|
| `answer`      | `string` | **Required**. Multiple choice|
| `isAnswer`      | `boolean` | **Required**. Correct answer|

#### Response
```
{
  "data": {
    "id": "string"
  },
  "message": "string"
}
```
-------
#### Request
```http
PUT /questions
```
#### Request Body
```
{
  "id": "string",
  "question": "string",
  "version": 0,
  "isActive": true
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. Question id to edit|
| `question`      | `string` | **Required**. Question|
| `version`      | `integer` | **Required**. Question version|
| `isActive`      | `boolean` | **Required**. Question is active|

#### Response
```
{
  "data": {
    "id": "string",
    "version": 0
  },
  "message": "string"
}
```
-------

### Answers
#### Request
```http
PUT /answers
```
#### Request Body
```
{
  "id": "string",
  "answer": "string",
  "version": 0
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. Answer id to edit|
| `answer`      | `string` | **Required**. Answer|
| `version`      | `integer` | **Required**. Answer version|

#### Response
```
{
  "data": {
    "id": "string",
    "version": 0
  },
  "message": "string"
}
```
-------
#### Request
```http
DELETE /answers/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. Answer id to fetch|

#### Response
```
{
  "message": "string"
}
```
-------

### Reports
#### Request
```http
GET /reports
```
#### Response
```
{
  "data": [
    {
      "id": "string",
      "fullName": "string",
      "score": 0,
      "version": 0,
      "submissions": [
        {
          "id": "string",
          "question": "string",
          "userAnswer": "string",
          "result": true,
          "version": 0,
          "isActive": true,
          "answers": [
            {
              "id": "string",
              "answer": "string",
              "isAnswer": true,
              "version": 0,
              "isActive": true
            }
          ]
        }
      ]
    }
  ]
}
```
-------
#### Request
```http
GET /reports/top-3
```
#### Response
```
{
  "data": [
    {
      "id": "string",
      "fullName": "string",
      "score": 0,
      "version": 0,
      "submissions": [
        {
          "id": "string",
          "question": "string",
          "userAnswer": "string",
          "result": true,
          "version": 0,
          "isActive": true,
          "answers": [
            {
              "id": "string",
              "answer": "string",
              "isAnswer": true,
              "version": 0,
              "isActive": true
            }
          ]
        }
      ]
    }
  ]
}
```
-------
#### Request
```http
GET /reports/user-candidates?id=
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required, Case-Insensitive**. User candidate id to filter query|

#### Response
```
{
  "data": [
    {
      "id": "string",
      "fullName": "string",
      "score": 0,
      "version": 0,
      "submissions": [
        {
          "id": "string",
          "question": "string",
          "userAnswer": "string",
          "result": true,
          "version": 0,
          "isActive": true,
          "answers": [
            {
              "id": "string",
              "answer": "string",
              "isAnswer": true,
              "version": 0,
              "isActive": true
            }
          ]
        }
      ]
    }
  ]
}
```
-------
#### Request
```http
GET /reports/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID string` | **Required**. Report id to fetch|

#### Response
```
{
  "data": {
    "id": "string",
    "fullName": "string",
    "score": 0,
    "version": 0,
    "submissions": [
      {
        "id": "string",
        "question": "string",
        "userAnswer": "string",
        "result": true,
        "version": 0,
        "isActive": true,
        "answers": [
          {
            "id": "string",
            "answer": "string",
            "isAnswer": true,
            "version": 0,
            "isActive": true
          }
        ]
      }
    ]
  }
}
```
-------
#### Request
```http
POST /reports
```
#### Request Body
```
{
  "userCandidateId": "string",
  "submissions": [
    {
      "answerId": "string"
    }
  ]
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userCandidateId`      | `string` | **Required**. User candidate id|
| `submissions`      | `array` | **Required**. Answer group|
| `answerId`      | `string` | **Required**. Selected answer|

#### Response
```
{
  "data": {
    "id": "string"
  },
  "message": "string"
}
```
