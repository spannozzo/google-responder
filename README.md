# google-responder project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```
# running from docker
From main folder of the project you can build the project 
```
docker build -t quarkus-native/google-responder -f .\src\main\docker\Dockerfile.multistage .
```
than you can run in the port 80 this service with 
```
docker run --rm -i -p 80:8080 -t quarkus-native/google-responder
```
and you can use jsoup to retrieve the questions like from an external application, or using postman e.g. http://localhost:80/v2/ask/what is google
```
doc= Jsoup.connect("http://localhost:80/v2/ask/"+question).ignoreContentType(true).get();
String body=doc.body().text();
List<QueryResult> queries= Arrays.asList(gson.fromJson(body, QueryResult[].class ));
```
## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `google-responder-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/google-responder-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/google-responder-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
