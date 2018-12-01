POST http://localhost:8080/jersey-spring-rest/1/loginservice/create

{"username":"Deba","password":"deba"}


PUT http://localhost:8080/jersey-spring-rest/1/loginservice/update

{"username":"Deba","password":"deba"}


DELETE http://localhost:8080/jersey-spring-rest/1/loginservice/delete

{"username":"Deba","password":"deba"}


GET http://localhost:8080/jersey-spring-rest/1/loginservice/userid?id=123


For ITC Related
-------------------
GET http://localhost:8080/jersey-spring-rest/itc

GET http://localhost:8080/jersey-spring-rest/itc/address

GET http://localhost:8080/jersey-spring-rest/itc/address/USA

GET http://localhost:8080/jersey-spring-rest/itc/regionaladdress/Europe?country=FI

GET http://localhost:8080/jersey-spring-rest/itc/itcaddress;country=FI;areacode=europe

POST http://localhost:8080/jersey-spring-rest/itc/postaddress

In case of Firefox Rest Client
Set in the header
“name” = “Content-Type” and “value” = “application/x-www-form-urlencoded”
Then set the header as 
country - FI
areacode - europe

In case of Chrome Postman
Click on x-www-form-urlencoded in Postman
country - FI
areacode - europe


GET http://localhost:8080/jersey-spring-rest/itc/emp/id123

Emp creation
POST http://localhost:8080/jersey-spring-rest/itc/createemp
For Emp creation
----------------
<Emp>
    <Name>Deba</Name>
    <Age>23</Age>
    <Email>deba@gmail.com</Email>
    <Adrs>
        <DoorNo>12-A</DoorNo>
        <Street>Street-11</Street>
        <City>Bangalore</City>
        <Country>Karnataka</Country>
    </Adrs>
</Emp>

or

{
    "Name": "Deba",
    "Age": 23,
    "Email": "deba@gmail.com",
    "Adrs": {
        "DoorNo": "12-A",
        "Street": "Street-11",
        "City": "Bangalore",
        "Country": "Karnataka"
    }
}

For uploading file
POST http://localhost:8080/jersey-spring-rest/itc/upload




http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/