# Diff Application

Diff is an application based on Java 8 and SpringBoot 2 that calculates the diff of two text files base64 encoded.

# Table of Contents

- [`Author`](#author)
- [`Stack`](#stack)
- [`Recommended IDE`](#recommended-ide)
- [`Development Setup`](#development-setup)
- [`Google Java Style Guide`](#google-java-style-guide)
- [`Tools`](#tools)
- [`How to deploy`](#how-to-deploy)
- [`Swagger documentation`](#swagger-documentation)
- [`Endpoints`](#endpoints)
- [`Implementation`](#implementation)

## Author
* [Leandro Inacio de Oliveira Bororo]

## Stack
``` bash
├── Java 8
├── Spring Boot 2.0.5.RELEASE
├── Spring MVC 2.0.5.RELEASE
├── Spring Data MongoDB 2.0.5.RELEASE
├── Spring Boot Test 2.0.5.RELEASE
├── Swagger 2.9.2
├── Lombok 1.18.4
├── Immutables 2.7.3
├── Embedded MongoDB 2.0.3
├── Docker
├── Gradle
```

## Recommended IDE
- IntelliJ IDEA 2018.3

## Development Setup 

### IntelliJ
```bash
$ ./gradlew idea open
or
$ ./gradlew idea
open *.ipr  #on OSX
```

#### In order get your code compiled in Intellij (Lombok and Immutables annotations processing depend on this):
1. Install "Lombok" plugin
2. Enable: Settings -> Compiler -> Annotation Processor -> Enable annotation processing
3. Select: Settings -> Compiler -> Annotation Processor -> Obtain processors from project classpath
4. Select: Settings -> Compiler -> Annotation Processor -> Store generated sources relative to: -> Module content root
5. Compile the project
6. Look for the package provided in Settings -> Compiler -> Annotation Processor -> Production sources directory
7. Using the right mouse click select Mark Directory As -> Sources root. This will make the classes generated by Immutables library available while coding

## Google Java Style Guide

Used [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) to keep the code formatting style.

To format the code using IntelliJ you need to install [google-java-format](https://github.com/google/google-java-format), please follow the instructions below.

It's also possible format the code running: ./gradlew goJF

## Tools

You should download and install these properly on your system. Visit the websites (linked) to find instructions on how to set them up.

 * [Gradle](https://gradle.org/) - Build tool
 * [Java](https://www.java.com/en/download/) - Java Runtime environment
 * [Docker](https://docs.docker.com/) - Docker containers

## How to deploy
#### Build backend, run tests and verify code formatting
```bash
$ ./gradlew clean build
```
#### Build and run the Docker containers
```
$ docker-compose up --build
```

## Swagger documentation

After successfully building and deploying the application. The api documentation can be accessed at http://localhost:8080/swagger-ui.html

## Endpoints

GET http://localhost:8080/v1/diff

PUT http://localhost:8080/v1/diff/ID/left

PUT http://localhost:8080/v1/diff/ID/right

## Implementation

### Assumptions

The application was built based on some assumptions:

1. The URIs **host/v1/diff/ID/left**, **host/v1/diff/ID/right** and **host/v1/diff/ID** contain the diff resource URI. For this reason it was assumed that there is a previous step to create the diff resource (something like an HTTP POST method call that retrieves the newly added diff resource). In order to avoid extra steps the calls to host/v1/diff/ID/left and host/v1/diff/ID/right are done through an HTTP PUT call (even though PUT should be used only for updating) but behind the scenes if the diff resource does not exist the application creates a new one. 
2. Because the diff endpoints start with **host/v1/diff** it was assumed that context-path should not be added to the URLs. For this reason the URLs start with http://localhost:8080/v1/diff.
3. It was assumed that **host/v1/diff/ID/left** and **host/v1/diff/ID/right** endpoints calls do not have to return a response body.
4. It was assumed that offset values should start at position 0.
5. It was assumed that HTTP GET calls to **host/v1/diff/ID** do not need to return the diff resource. For this reason they return the processing status and the solution collection only.

### Decisions

A few decisions were taken:

1. [Spring Initializr](https://start.spring.io/) was used to bootstrap a Java application using Gradle, Spring Boot 2, Spring Data MongoDB, Spring MVC and Spring Boot Test.
2. Added [idea plugin](https://docs.gradle.org/current/userguide/idea_plugin.html) (IntelliJ IDE was used to code the application) to build.gradle file.
3. Added [google-java-format](https://plugins.gradle.org/plugin/com.github.sherter.google-java-format) plugin to build.gradle file.
4. Added [Lombok](https://projectlombok.org) dependency to generate code in the domain classes.
5. Added [Immutables](https://immutables.github.io/) dependency to generate immutable data transfer objects.
6. Added [MongoDB embedded](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo) dependency for testing purposes.
7. Created a Dockerfile to describe how to build a docker image that hosts the application.
8. Decided to create a docker-compose.yml file in order to have a docker image running MongoDB (exposed port 27017), a docker image running one instance of the application for asynchronous processing (exposed only port 8094 for debugging purposes) and a docker image running another instance of the application for synchronous processing (exposed port 8080 for API calls and port 8094 for debugging purposes). 
9. Decided to use [Swagger](https://springfox.github.io/springfox/docs/snapshot/) to document the REST API.
10. Decided to implement a job using @Scheduled annotation to perform asynchronous diff calculation.

### Details

Since the client requests provide diff resource URIs, PUT method was picked to implement both left and right texts  (through **host/v1/diff/ID/left** and **host/v1/diff/ID/right**). If the resource does not exist a new resource is created and then persisted on MongoDB (even though resource creation should be done using POST method), otherwise the resource is updated. Before saving the diff resource two actions take place:
* If it is not possible to decode the text then a DataNotDecodeableException is thrown. The ExceptionControllerHandler (annotated with @ControllerAdvice) intercepts the exception and creates a response with HTTP status equal to 422 - Unprocessable Entity and a body like this:
``` bash
{
    "status": "UNPROCESSABLE_ENTITY",
    "error": "Invalid data  = text"
}
```
* If both left and right texts are present in the diff resource then set the DiffStatus to READY_TO_PROCESS, otherwise set the status to NOT_PROCESSED.

* In case of success the application creates a response with HTTP Status equal to 200 - Ok. 

Client requests to **host/v1/diff/ID** using GET method are handled as follows:

* If there is no diff resource with the given ID a DiffNotFoundException is thrown. The ExceptionControllerHandler (annotated with @ControllerAdvice) intercepts the exception and creates a response with HTTP status equal to 404 - Not Found and a body like this:
``` bash
{
    "status": "NOT_FOUND",
    "error": "Resource with id XX not found"
}
```
* If the diff exists then the application creates a response with HTTP Status equal to 200 - Ok and a body like these:
``` bash
{
    "status": "NOT_EQUALS",
    "diffs": [
        {
            "offset": 13,
            "length": 1
        },
        {
            "offset": 23,
            "length": 1
        },
        {
            "offset": 34,
            "length": 1
        }
    ]
}
```
When the texts have the same content:
``` bash
{
    "status": "EQUALS",
    "diffs": []
}
```
When the texts have different sizes:
``` bash
{
    "status": "DIFFERENT_SIZES",
    "diffs": []
}
```
When one text is missing:
``` bash
{
    "status": "NOT_PROCESSED",
    "diffs": []
}
```
When the diff is ready to be picked up and solved:
``` bash
{
    "status": "READY_TO_PROCESS",
    "diffs": []
}
```
The asynchronous instance of the application has the "worker" profile activated. When "worker" profile is active the SolveDiffJob bean is assembled and managed by the spring container. This bean has a method annotated with @Scheduled that triggers at every 5 seconds and solves the pending diffs as follows:
1. Searches for diff resources whose status is equal to READY_TO_PROCESS.
2. Delegates to DiffSolver the calculation of the diff according to this assignment specification.

#### Tests

* DiffControllerIntegrationTest : end-to-end integration test
* SolveDiffJobTest : unit test to test the job
* DiffServiceImplTest : unit test to test the service layer
* DiffSolverTest : unit test to test the logic that solves the diff

### Improvements

A few improvements that could be implemented:
1. Have two applications instead of only one, this ways we could separate concerns.
2. Use a message-broker instead of a job this way every time a diff is ready to be processed a message could be sent and a producer could take care of the processing.
3. Improve the REST API to provide more endpoints. Maybe come up with a better approach to create the resources using POST method and more endpoints.
