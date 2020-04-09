Test Assignment
===============================
Tasks:
1. Correct all of the deficiencies in index.html
2. "Sectors" selectbox:
2.1. Add all the entries from the "Sectors" selectbox to database
2.2. Compose the "Sectors" selectbox using data from database
3. Perform the following activities after the "Save" button has been pressed: 
3.1. Validate all input data (all fields are mandatory)
3.2. Store all input data to database (Name, Sectors, Agree to terms)
3.3. Refill the form using stored data 
3.4. Allow the user to edit his/her own data during the session

The best approach to solve this was Spring MVC CRUD HTML form handling, using Spring validator and Bootstrap for CSS styling.
Created with IntelliJ IDEA.

### 1. Technologies used
* Maven 3.0
* Spring 4.3.5.RELEASE
* HSQLDB 2.3.2
* Bootstrap 3

### 2. To Run this project locally
```shell
$ mvn jetty:run
```
Access ```http://localhost:8080/helmes-form/people/add```

