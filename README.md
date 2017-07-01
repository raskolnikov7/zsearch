# Search

Search app built with Java and Spring data elasticsearch.

## Introduction

This is a Spring boot application that instantiates embedded elastic search node. Spring data elastic search repositories are used to index and search records. Reflection is used to dynamically call search methods with search terms.

### Prerequisites

Java 1.8.x
Maven 3.2.5 to package


### Installing

From the project root directory, to run as a spring boot maven project.

```
mvn spring-boot:run
```


From the project root directory, to create an executable jar under /target with all the dependencies bundled together.

```
mvn package  
```

To package without running tests

```
mvn package -Dmaven.test.skip=true
```


To execute jar from command line, Under /target/

```
java -jar demo-zsearch.jar
```


## Running the tests

From the project root directory


```
mvn test
```


## Deployment

The generated jar .jar will execute on any system (tested on mac only) running JRE 1.8.x
## Built With

* [Java](http://www.oracle.com/technetwork/java/javase/overview/index.html) - The language
* [Spring Boot](https://projects.spring.io/spring-boot/) - Application framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Elasticsearch](https://www.elastic.co/) - Index and search
* [Simple Json] - JSON parsing



## Authors

* **Kamlendra Chauhan**  - (https://github.com/raskolnikov7)

