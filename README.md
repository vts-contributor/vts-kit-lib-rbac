Role-based Access Control Library for Spring Boot
-------
This library provides a dynamic role-base access control framework for a spring boot project

<b>Feature List</b>
- JWT-based authentication
- Dynamic role-based authorization
  * User: CRUD
  * Organization: CRUD, assign to user
  * Role: CRUD, assign to user & organization
  * Menu Permission: CRUD, assign to role to control permission to frontend menu
  * Custom Permission: CRUD, assign to role to control permission to custom frontend components
  * Endpoint Permission: CRUD, assign to role to control permission to backend endpoints
  

<b>Database type supported</b>
* MariaDB, Postgresql, MySql,...

Quick start
-------
* Just add the dependency to an existing Spring Boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-lib-rbac</artifactId>
    <version>1.0.0</version>
</dependency>
```

* Then, add the following properties to your `application-*.yml` file.
```properties
spring:
  datasource:
    url: <url>
    username: <username>
    password: <password>
vtrbac:
    jwt:
      base64-secret: <base64-secret>
      token-validity-in-seconds: <token-validity-in-seconds>
      token-validity-in-seconds-for-remember-me: <token-validity-in-seconds-for-remember-me>
```
The system will provide jwt-based authentication and automatically create neccessary tables and save some default account data there:
| Username | Password | Roles |
|----------|----------|----------|
| admin | admin | ROLE_ADMIN, ROLE_USER |
| user | user | ROLE_USER |



Usage
-------

### Authentication
#### Login
```js
   ### Expect { id_token: <JWT-token> }
   POST /authenticate
   {
    "username":<username>,
    "password":<passowrd>,
    "rememberMe":<rememberMe>
   }
   ```
#### Register
```js
   ### Expect code 201
   POST /register
   {
    "login":<username>,
    "password":<passowrd>,
   }
   ```

### Dynamic role-based authorization

#### Menu authorization
Follow the steps below:

<b>Step 1</b>: Create menu permission

<b>Step 2</b>: Assign menu permission to allowed roles

<b>Step 3</b>: Write front-end code to dynamically load menu based on result from /getPermission endpoint

#### Custom frontend authorization
Example frontend components:
  - Button
  - Datatable
  
<b>Step 1</b>: Create button permission

<b>Step 2</b>: Assign button permission to allowed roles

<b>Step 3b</b>: Write front-end code to dynamically update component based on result from /getPermission endpoint


#### Backend authorization
  
<b>Step 1</b>: Create backend endpoint

Authorization for backend endpoint can be done explicitly or through button permission mapping if the use case calls for synchronous authorization with front-end components
In case of explicit authorization without mapping
<b>Step 2a</b>: Assign backend endpoint to allowed roles

In case of implicit authorization thourgh permission mapping
<b>Step 2b</b>: Assign backend endpoint to the corresponding permission mapping


Contribute
-------
#### Setting up the development environment
* <b>IDE:</b> Eclipse, Intellij IDEA
* <b>JDK:</b> >= JDK 8
* <b>Maven:</b> >= 3.6.0
* <b>Build:</b>
```shell script
mvn clean install
# Skip Unittest
mvn clean install -DskipTests
```
#### Contribute Guidelines
If you have any ideas, just open an issue and tell us what you think.

If you'd like to contribute, please refer [Contributors Guide](CONTRIBUTING.md)

License
-------
This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.
