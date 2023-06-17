# REAMDE

### Reference Documentation

reference address:

* [Web Address](http://192.168.10.123:8888)

## Introduce
For the e-banking portal, a Spring-based project was designed and implemented that implements a related reusable REST API to return a paginated list of currency account transactions created for a given customer logged into the portal in any calendar month. For each transaction "page", the total credit and debit values are returned at the current exchange rate. The transaction list should be used from Kafka topics. A Docker image is built from the application and a configuration is prepared to deploy it to Kubernetes.

This project uses the spring framework for development, mysql for data persistence, kafka cluster for data storage, Spring-Kafka for data consumption and stored in the database, and spring security for system permission management. swagger2 was used to create interface documentation for debugging, mybatis-plus was used to update and delete database tables, and Docker was used to deploy the project.

## Deployment Notes
1. Operating System: centOS/Ubuntu

2. Database: MySQL8

3. Permission management: Spring Security

4. Container: Docker

5. Message queue: kafka

6. Distributed configuration center: zookeeper
7. Language: Java

## DataBase Design
Tb_role stores the name and description of the permission, tb_user stores user information, and tb_user_role represents the many-to-many relationship of the first two tables.

Since a user may have multiple IBAN accounts, a second table tb_card is created, Tb_role stores the permission name and description, tb_user stores user information, and tb_user_role represents the many-to-many relationship of the first two tables. Used to represent a one-to-many relationship between a user and an IBAN account.

The transaction records of users stored in Kafka message queue need to be consumed by the system back-end and stored in the relational database, so the tb_transaction table needs to be established to store the data.

## Security assurance
Using spring security, create a User class and implement the getAuthorities() method in UserDetails to retrieve information about all the roles of the current user. The roles are stored in the roles property, so this method iterates directly through the roles property. Then the SimpleGrantedAuthority set is constructed and returned, and the UserDetailService interface is implemented in the UserService. According to the user name entered when the user logs in, the user is searched in the database by the user name.

If the search to the user, to continue to find this user is the role of information, and will get the user object returns, then DaoAuthenticationProvider class provided by the system to match the password is correct. Then configure SpringSercurity.

The permissions are configured so that non-logged-in users need to log in first to continue to access the API, otherwise they are not allowed to obtain any resources.

After a successful login, the user information is returned. The front-end stores the token and carries it with it when requesting the API to identify the user.

## kafka Configuration
After a successful login, the user information is returned. The front-end stores the token and carries it with it when requesting the API to identify the user. Use spring-kafka to consume kafka data, create KafkaComsumer class, subscribe data in transaction topic with kafkaListener annotation,
parse the received data, and store it in tb_transaction data table.

## interface debugging
Write the controller using a RESTful interface, first inject the UserService with @Autowired annotation, according to the request parameters passed from the frontends: token, month, pageNum query the data, token is the user unique identifier, month represents the data month to query, pageNum represents the page, used to paginate the data.

Firstly, the corresponding user is found by querying the tb_user table according to the token, and then all the IBAN accounts of the user are queried according to the user information, and then the data that meets the requirements are queried from the tb_transaction table according to the month and the IBAN account. Finally, the data is paginated.

The wrapper R class is used to convert the data to JSON format.

## Deploy

After configuring the project to start, use docker to package the jar and deploy to docker. Write a Dockerfile to configure the image and jar.

