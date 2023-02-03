## Required Info:<br/>

This project is developed using MySql database and java 8 so before run the application 
in your machine you need to ensure that you have installed the **mysql** database and java 8.




## For run this project follow the below steps
1) create a database schema like ,
```
assignment
```
2) set your database username and password in <b>application.properties file</b>
```
spring.datasource.username=//your name here.
spring.datasource.password=//your password here.
```
3) Set your java version from your IDE project structure <b> Java 8</b>
4) If you don't have enable lombok please install lombok from your IDE settings=>plugins.
5) Everything ok now you can run the project.

**Test option:** 
<br/>_Option 1:_

In this project we have swagger so  after run the project you test the application 
using swagger and the swagger URL format should be http://yourIp:yourPort/swagger-ui/index.html

<br/>_Option 2:_
I have written the test case in the application you can also the available api
using the test code.
you can find the test code from the test directory.






