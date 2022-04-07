#Inventario de vacunación de empleados

###Previous steps

* You must have Java 11 or greater installed in your system to run this project.
* Create a postgresql database into your local system and save its properties
  as environment variables:

    SPRING_DATABASE_NAME=<database_name>
    
    SPRING_DATABASE_USERNAME=<database_username>
    
    SPRING_DATABASE_PASSWORD=<database_password>

###Building and testing

```
$ ./mvnw clean install
```

###Running the project

1. If you want to load data into database when running the project, please
include the content of ```/src/main/resources/data.txt``` into 
```/src/main/resources/data.sql```

    You could edit this script in order to have your own data loaded into the database.
    
    Don't forget to drop tables each time you run again the project (if you
have loaded data previously):
    
    ```postgres-sql
     DROP TABLE users, user_vaccines, roles, user_roles;
    ```
2. Run the project:
    ```
    $ ./mvnw spring-boot:run
    ```

3. As Spring Boot Security is configured, you should test the endpoints through a
web browser (or another preferred tool), using the address ```http://localhost:8080``` and using the credentials
previously set up (if you used the script in step 1, or you could load this data manually 
into the database). ADMIN users could access to all endpoints and EMPLOYEE users could only
retrieve data from ```/api/employees/{id}```.

   1. If you want to test the endpoints from API platforms (for example Postman) you must
    deactivate Spring Security Configuration and all endpoints will be available to test. You
    could go into ```/src/main/resources``` where you have a Postman Collection with several
    requests ready to be used.
   
###OpenApi
Navigate to ```http://localhost:8080/swagger-ui/index.html``` to check the openapi definition

###Technical debts
* Some unit/integration tests
* Testing using mocks instead of real scenarios
* Testing using in-memory database (H2) instead real database
* Use of OAuth2 instead of Spring Security
* Simple front-end for a proper manual testing of endpoints
* Proper preloading data (```schema.sql``` and ```data.sql```) into database when run the project
