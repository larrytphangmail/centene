

**** REQUIRED ****

1.  Oracle JDK 8
2.  MySQL database server and MySQL Workbench
3.	Create the following directory and copy the required files into it:
	a. C:\WORKSPACE\DEPLOYMENT>
	b. Copy files into the above derectory:
	   1. centene-challenge.jar
	   2. centene-application.properties
	   3. Run the JAR from command-line: 
			java -jar centene-challenge.jar --spring.config.location=centene-application.properties

4.	Add "RestClient" add-ons/extension to Firefox browser or any RESTFul webservice client tool for testing 






5.	Below are the following test use-cases:


GET method:  http://localhost:8080/get-all-enrollees/activation-status/1
GET method:  http://localhost:8080/get-all-enrollees
GET method:  http://localhost:8080/get-all-enrollees/enrollee-id/1

POST method: http://localhost:8080/add-enrollee

{
    "name":"Larry Thoai Phan",
    "activationStatus":true,
    "birthDate":"10/10/2020",
    "phoneNumber":"999-999-9999"
}

POST method: http://localhost:8080/add-enrollees
[
    {
        "name":"Tam Larry Thoai Phan",
        "activationStatus":false,
        "birthDate":"10/10/2020",
        "phoneNumber":"999-999-9999"
    },
    {
        "name":"Tam Larry Thoai Phan",
        "activationStatus":true,
        "birthDate":"10/10/2020",
        "phoneNumber":"999-999-9999",
        "dependents":[]
    }
]


POST method: http://localhost:8080/delete-enrollee
{
    "id":1
}

POST method: http://localhost:8080/delete-enrollee/id/{id}

POST method: http://localhost:8080/update-enrollee
{
    "id": 2,
    "name": "Tam Larry Phan",
    "activationStatus": true,
    "birthDate": "10/10/2020",
    "phoneNumber": "999-999-9999"
}
      
      
POST method: http://localhost:8080/update-enrollees
  [
      {
        "id": 2,
        "name": "Tam Larry Phan",
        "activationStatus": true,
        "birthDate": "10/10/2020",
        "phoneNumber": "999-999-9999"
      },
      {
        "id": 3,
        "name": "Tam Larry Phan",
        "activationStatus": true,
        "birthDate": "10/10/2020",
        "phoneNumber": "999-999-9999"
      }
]


*** DEPENDENTS ***

POST method: http://localhost:8080/add-dependent
{
   "name": "Larry Thoai Phan",
    "birthDate": "04/02/2010",
    "enrolleId": 4
}

POST method: http://localhost:8080/add-dependents
[
  {
   "name": "Larry Thoai Phan",
    "birthDate": "04/02/2010",
    "enrolleId": 4
  },
  {
   "name": "Tam Thoai Phan",
    "birthDate": "04/02/2010",
    "enrolleId": 4
  }
 ]
 
 GET method: http://localhost:8080/get-all-dependents
 
 GET method: http://localhost:8080/get-all-dependents/enrollee-id/{enrolleeId}
 
 POST method: http://localhost:8080/update-dependent-by-enrolleeId
  {
    "name": "Tam Larry Thoai Phan",
    "birthDate": "04/02/2011",
    "enrolleId": 4
  }
  
 POST method: http://localhost:8080/update-dependent-by-id
  {
    "id":1,
    "name": "Tam Larry Thoai Phan",
    "birthDate": "04/02/2011",
    "enrolleId":6
  }
  
 POST method: http://localhost:8080/delete-dependent/id/2
 
 POST method: http://localhost:8080/delete-dependent/enrollee-id/4
  
 POST method: http://localhost:8080/delete-dependents
 [
    {
        "id":7
    },
    {
        "id":9
    }
]
    