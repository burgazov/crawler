# crawler 
simple web crawler web application

# System run/start
## Windows:
### 1. Pre-requisites
- Maven
- Java JDK

### 2. Compile and run locally
From the root of your project location (e.g. C:\workspace\crawler>) on cmd do

C:\workspace\crawler> mvn clean test install

C:\workspace\crawler> mvn spring-boot:run

or

C:\workspace\crawler> java -jar target/simple-crawler-challenge-0.0.1-SNAPSHOT.jar

or on linux

:/home/ubuntu/crawler$ sudo nohup java -jar simple-crawler-challenge-0.0.1-SNAPSHOT.jar &

# Access, use and test
## Access
Running on port 10002
http://localhost:10002/crawl

## Login
No user name and password required

## Use via plain web browser
Below (GETs) can be used and accessed via web browser (Chrome, IE, etc.)

http://localhost:10002/crawl?url=url_address - return list of all user ids

e.g.
http://localhost:10002/crawl?url=http://35.177.112.78:8080
http://localhost:10002/crawl?url=https://www.wiprodigital.com
http://localhost:10002/crawl?url=https://www.google.com


## Output
See /src/test/samples

### XML 
<?xml version="1.0" encoding="UTF-8"?>
<sitemap>
<url><loc>https://www.wiprodigital.com</loc></url>
<url><loc>https://wiprodigital.com/join-our-team#wdcareers_team</loc></url>
<url><loc>https://wiprodigital.com/wp-json/oembed/1.0/embed?url=https%3A%2F%2Fwiprodigital.com%2F</loc></url>
<url><loc>https://wiprodigital.com/what-we-do#wdwork_partners</loc></url>
...............
<image><loc>https://s17776.pcdn.co/wp-content/uploads/2017/10/Author-Phil-Fersht.jpg</loc></image>
<image><loc>https://s17776.pcdn.co/wp-content/uploads/2017/10/Insights-Time-to-Reinvent-The-Marketing-Agency-1.jpg</loc></image>
<image><loc>https://s17776.pcdn.co/wp-content/uploads/2018/03/Author-Caglar-Araz.jpg</loc></image>
<image><loc>https://s17776.pcdn.co/wp-content/uploads/2018/03/Insights-We-Have-Lost-Track-of-What-UX-Actually-Means.jpg</loc></image>
<image><loc>https://s17776.pcdn.co/wp-content/uploads/2015/11/Insights-We’re-in-a-Global-Usability-Crisis-and-Here’s-How-We-Can-Fix-it.jpg</loc></image>
</sitemap>


# Cloud running instance
It will follow shortly on GCloud

http://35.246.61.13:10002/crawl

