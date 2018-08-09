# Barry Tarlton's Grocery Store POS kata submission

This was coded using [SpringBoot 2.0.3](https://spring.io/projects/spring-boot) and on [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

I'm using [Maven 3.X](https://maven.apache.org/download.cgi) for my build/packaging tool.

To run the tests simply run the "**mvn test**" command.

To actually run the application run "**mvn spring-boot:run**"

The app will listen on port 8999. This can be changed in the [application.properties](https://github.com/javaplus/grocery-pos/blob/master/src/main/resources/application.properties)


The previous time I submitted a kata, it was said that at some point I may have missed commiting a file because I had a commit that wouldn't build or pass the tests.
This time to ensure that every commit to master was good, I set up a CodeBuild job on AWS to build each pull request to Master and to make sure it passed before I merged.
So, I did feature branches for all my development and even before commiting locally I ran all the tests with maven and then did a "git add ." and then "git commit" to make sure I didn't miss anything.

I also setup a CodePipeline on AWS to automatically test,build, and deploy the code to an Elastic Beanstalk instance.  I've added a Postman project as well as a link below to the Swagger/OAS if you want to hit the running app on AWS.
(Warning it can be slow on my super small free tier). 
Base URL for app deployed on AWS:  http://barrygrocerypos-env.mw9mxhkuak.us-east-1.elasticbeanstalk.com:8999

Simple GET Inventory Items URL to retrieve list of items:
[http://barrygrocerypos-env.mw9mxhkuak.us-east-1.elasticbeanstalk.com:8999/inventory/items](http://barrygrocerypos-env.mw9mxhkuak.us-east-1.elasticbeanstalk.com:8999/inventory/items)

Postman collection is at root of project. [Here](https://github.com/javaplus/grocery-pos/blob/master/PillarGroceryPOSKata.postman_collection.json)

I've also created a Swagger/Open API Spec on SwaggerHub for the API.
The [OAS/Swagger](https://app.swaggerhub.com/apis/btarlton/grocery-store_kata_api/2.0#/) can be found [here](https://app.swaggerhub.com/apis/btarlton/grocery-store_kata_api/2.0#/)
