WADL URL : http://localhost:8080/jersey-upload-download/rest/application.wadl

To download a file
http://localhost:8080/jersey-upload-download/rest/files/download?fileName=A.docx

you can simply hit the URL in any browser, you don't need any rest client

To Upload a file
http://localhost:8080/jersey-upload-download/rest/files/upload1

or

http://localhost:8080/jersey-upload-download/rest/files/upload
Use only Postman not the firefox RESTClient


To show Image
http://localhost:8080/jersey-upload-download/rest/files/show/image?fileName=r1.jpg




POST 
http://localhost:8080/jersey-upload-download/rest/files/upload3
emp as key 
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