# ibm
 
This project has API microservice using spring boot and Jersey to simulate user registration:
-  REST API to accept a payload of username, password, and IP address.
- All parameters are not blank (!= empty and null). Returned error messages if not valid
- Password need to be greater than 8 characters, containing at least 1 number, 1 Captialized letter, 1
  special character in this set “_ # $ % .” Return error messages if not valid
- Call this end point to get geolocation for the provided IP:IP-API.com - Geolocation API - Documentation
- JSON. If the IP is not in Canada, return error message that user is not elligible to register
- When all validation is passed, return a random uuid and a welcome message with his username and his
  City Name (resolved using ip-geolocation api)
- The API need to have OpenAPI specification, no matter what your approach is code first or design first.
- Project must use maven or gradle to build. Generate a spring boot project here: Spring Initializr
- JUnit Tests