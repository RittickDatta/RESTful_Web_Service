Hello, please read this text file to get an overview of my application and steps to successfully run the application.

----------------LANGUAGE-------------------
Java language is used to implement the RESTful web service.

-----------DEVELOPMENT ENVIRONMENT---------
1. Java 1.8
2. Apache Tomcat v7.0
3. Jersey Framework binaries
4. Eclipse Java EE (Luna)
5. Bootstrap4 to style the welcome page(index.html)

----------SOURCE FILE DESCRIPTIONS---------
1. Vehicle.java - This is a POJO class with 4 properties (Id, Year, Make, and Model)
2. VehicleDao.java - This class has methods implemented to handle list of POJO objects retrieved from "Vehicles.dat".
					 "Vehicles.dat" file is used to persist Vehicle data in XML format.
3. VehicleService.java - This is the resource class and has methods to handle the following routes:
						 GET vehicles
						 GET vehicles/{id}
						 POST vehicles
						 PUT vehicles
						 DELETE vehicles/{id}
4. AutomatedTesting.java - This class can be run as a java application to test all 5 routes. It sends each of the five 
						 requests to the target and retrieves a result of pass/fail.
      NOTE: I also used "Postman" application to test GET, GET with an id, DELETE, POST and PUT requests.
5. CORSFilter.java - This class is used to add CORS support on server side in Java with Jersey framework.

--------STEPS TO RUN APPLICATION-----------
NOTE: Before running the application, please make sure you have Java 1.8 installed, downloaded Apache Tomcat v7.0 and set
CATALINA_HOME environment variable. For development purposes, I had set CLASSPATH to Jersey framework binaries(jaxrs-ri).
I would recommend Eclipse Java EE(Luna) for following steps.

1. Open Eclipse Java EE(Luna).
2. Click File -> Click Import -> Select File Type as "WAR file".
3. Browse for the "WAR file" and make sure that the "Target Runtime" is set to "Apache Tomcat V7.0".
4. Click "Finish".
5. Right-click on "Mitchell_CodingChallenge_RittickDatta" and select run as "Run on Server".
6. Expand Java Resources and locate "AutomatedTesting.java" in "mitchell.codingchallenge.rittick" package.
7. Right-click on "AutomatedTesting.java" and run as "Java Application".
8. Observe Console for test results (pass/fail).
NOTE: In the welcome page(index.html), there are two links, one to execute GET and another to execute GET by Id.