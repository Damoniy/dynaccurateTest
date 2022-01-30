# rest-api-dynaccurate-test

#FOLLOW THIS STEPS

INFO: THIS PROJECT WAS WRITTEN IN KOTLIN - JDK 11.

1. First of all, use docker to get the images from dockerhub.

REST API -> docker command: docker pull damoniy/dynaccurate-test:latest
RABBITMQ MESSAGE CONSUMER -> docker command: docker pull damoniy/rabbitmq-consumer:latest

2. After pull the projects, run the two images.

3. Use a tool like postman to send the POST, GET, UPDATE and DELETE requests.

4. That's it. 

#IMPORTANT

You can also check the REST application in heroku instead of docker container: 
https://mysterious-retreat-83549.herokuapp.com/

REST API Docs available in: https://mysterious-retreat-83549.herokuapp.com/docs
