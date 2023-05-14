#Account Ledger API.

I used springboot 3.0.6/ Java 17/ SpringMVC

Building and Deploying

mvn clean install

After success build create the docker image by running

docker build -t ledgerapi:1 .
After creating the image run

docker run -p 8080:8080 --name tango ledgerapi:1
#Preconfiguration for testing preloading data was created using CommandLineRunner
user/password/accountNumber/branchNumber -----> test@gmail.com/password/8888/999999

Once the application is running you will be exposed to endpoints

URL

```
http://localhost:8080

```

## H2 In-Memory Database

URL jdbc:h2:mem:testdb
username: sa
password: password


## Testing Account Ledger API
Please note due to time restrain the solution is not optimum and could not include unit testing.

## Login
Username : test@gmail.com
Password : password

It also have the ability to register users