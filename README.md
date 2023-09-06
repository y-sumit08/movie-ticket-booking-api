# MovieTicketBooking
This is the movie ticket booking REST API project. We have used Spring boot for Rest API implementation and Spring data jpa for Database connectivity.
We have also implemented jwt authentication to secure all the endpoints in EndUserController and TheatrePartnersController.
The api documentation is available at swagger ui : http://localhost:8080/swagger-ui/index.html

## Below are the list of API endpoints available for the user to make api integrations for there platform 


### All the Admin or merchant api endpoint starts with the /api/v1/admin *

### To add the City to the DB
URL: /api/v1/admin/city
Method: POST 
Request:
{
	"name":"Bengaluru", 
	"pincode":"590056",
	"state":"Karnataka"
}
Response:
{
    "name": "Bengaluru",
    "state": "Karnataka",
    "id": 2,
    "pincode": "590056"
}

### To add theatre to the DB 
here theatre reference to the key_id of the city as primary key so make sure to pass the valid city id 
URL: /api/v1/admin/{CityID}/theatre
Method: POST
Request: 
{
	"name":"Finox mall",
	"area":"Koramangala"	
}
Response:
{
    "t_name": "Finox",
    "t_area": "Kormangala",
    "t_id": 3
}

### To add the Movie to the DB 
URL: /api/v1/admin/movie
Method: POST
Request Body:
{
	"_name":"WAR 3",
	"_director":"WAR 3 directors",
	"_description":"description about the WAR 3 movie here"
}
Response Body:
{
    "_name": "WAR 3",
    "_director": "WAR 3 directors",
    "_description": "description about the WAR 3 movie here",
    "_id": 6
}

### To add a show 

URL: 

###  TO add a show pass the theatre Id and Movie id as a URL parma and the show time in the request body *
URL: /api/v1/api/v1/admin/{theatre_id}/{movie_id}/show
Method: POST
Request:
{
	"time":"4/4/2020 - 3:30 PM"
}
Response:
{
    "show_Id": 8,
    "show_time": "1/4/2020 - 3:30 PM "
}


### All the End user api will start with the /api/v1/user *

### When user comes to the app make a get call to below API to fetch the name of city available on the app 

URL: /api/v1/user/city
Method: GET 
Response Body:
[
    {
        "name": "Munavalli",
        "state": "Karnataka",
        "id": 1,
        "pincode": "591117"
    },
    {
        "name": "Bengaluru",
        "state": "Karnataka",
        "id": 2,
        "pincode": "590056"
    }
]

### To get the list of theatres Available on the city using the city id got in the previous call

URL: /api/v1/user/{cityID}/theatre
Method: GET
Response Body:
[
    {
        "t_name": "Finox",
        "t_area": "Kormangala",
        "t_id": 3
    },
    {
        "t_name": "Bhoda theatre",
        "t_area": "Kengeri",
        "t_id": 5
    }
]

### after user selects the theatre, pass the theatre id to the below api and in return you'll get a list of movies available in that theatre *

URL: /api/v1/user/theatre/{theatreID}/movie
Method: GET
Response Body:
[
    [
        6,
        "WAR 3",
        "description about the WAR 3 movie here"
    ]
]

