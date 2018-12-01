WADL URL : http://localhost:8080/itcrestful/application.wadl
General URL : http://localhost:8080/itcrestful/itc
Address URL : http://localhost:8080/itcrestful/itc/address
US Address : http://localhost:8080/itcrestful/itc/address/USA
EU Address : http://localhost:8080/itcrestful/itc/address/europe

By Country Code

http://localhost:8080/itcrestful/itc/regionaladdress/Europe?country=FI

Using Matrix Param

http://localhost:8080/itcrestful/itc/itcaddress;country=FI;areacode=europe

http://localhost:8080/itcrestful/itc/postaddress

Click on x-www-form-urlencoded in Postman
country - FI
areacode - europe

For Emp Id
http://localhost:8080/itcrestful/itc/emp/id123
in the header, pass the following

Accept - application/xml or application/json
Select GET request

Emp creation
http://localhost:8080/itcrestful/itc/createemp
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

Accept - application/xml or application/json
Content-Type ---------- application/xml or application/json
click on Raw to provide the data and down click on Pretty


For Uploading binary file
------------------------
http://localhost:8080/itcrestful/itc/upload