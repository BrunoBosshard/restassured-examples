# RestAssured API tests
RestAssured is an open source Java library to test and validate REST API's.

This project contains a collection of example RestAssured tests and techniques.

## Installation
For this project, you need to install:

1. An Integrated Development Environment (IDE) for Java development, such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download) (the free "Community Edition" is sufficient), or [Eclipse](https://www.eclipse.org/downloads/). Some IDE's come with Java already pre-installed, while others require separate installation of Java. You should ensure that your Java IDE supports [TestNG](https://testng.org/) by checking if a TestNG plug-in is already installed (IntelliJ IDEA), or if a plug-in needs to be downloaded and installed from an IDE Marketplace (Eclipse).
2. [Apache Maven](https://maven.apache.org/) is required as the dependency management tool for this project. You need to install Apache Maven separately.

Once you have your IDE, Java, and Apache Maven installed, then you can execute the `pom.xml` file in your IDE by using the context menu. This will download and install all dependencies listed in the `pom.xml` file, including RestAssured and TestNG.

For some test examples, you need your own local API server. The local API server that is used in this project is [json-server](https://www.npmjs.com/package/json-server). "json-server" is a [Node](https://nodejs.org/en) based server, which means that you need to install Node first, before you can install "json-server". You can install "json-server" in any folder (even outside of this project).

1. Install Node
2. Install "json-server" with this command: `npm install -g json-server`

## RestAssured test examples in the "restassured_tests" package

### "Authentications" class
The test examples show different forms of Authentication (Basic, Digest, Pre-emptive, Bearer Token, AOuth version 1, AOuth version 2, API Key). The "Bearer Token" and the "Oauth version 2" test examples require your own GitHub Personal Account Token. You can get your Personal Access Token for free in your GitHub account. You can use the same GitHub Personal Access Token for both test examples ("Bearer Token" and "Oauth version 2").

### "Basic_HTTP_requests" class
The test examples show all four basic API request types ("GET", "POST", "PUT", "DELETE"). "GET" is used to retrieve information from a REST API server, "POST" is used to send new information, "PUT" is used to update existing information, and "DELETE" is used to delete existing information.

### "Cookies_demo" class
The test example shows how to handle Cookies from "GET" requests.

### "Faker_demo" class
[Java Faker](https://github.com/DiUS/java-faker) is a Java library that creates random (fake) data. The test example demonstrates how to use it. Faker is extremely useful to automatically create new data for API requests. In this project, Faker is used in the `testng_execution` package to generate new data in both the `Create_user` and `Update_user` classes.

### "Four_different_POST_requests" class
The test examples show how to create body data (payload data) for "POST" requests using HashMaps, JSON objects, Java POJO classes, and external files (by using an external JSON file called `body.json` that is provided in the project root folder).
The test examples require a locally running "json-server" that serves the `students.json` file that is provided in the project root folder. You can start the "json-server" with this command:
```
json-server students.json
```
Executing the test examples will add additional data to the `students.json` file. You might therefore want to periodically delete this additional data to keep the `students.json` file tidy.

### "Headers_demo" class
The test examples show how to validate Header responses.

### "JSON_schema_validation" class
The test example shows how to validate "GET" responses against the JSON Schema `storeJsonSchema.json` that is stored in the `resources` folder. This folder is also known as the "Classpath".
The test example requires a locally running "json-server" that serves the `store.json` file that is provided in the project root folder. You can start the "json-server" with this command:
```
json-server store.json
```
If you need to create a new JSON Schema, then you can create one from your existing JSON data by using [this web site](https://jsonformatter.org/json-to-jsonschema).

### "Logging_demo" class
The test example shows several options for logging output, including `body()`, `cookies()`, `headers()` and `status()`.

### "Parsing_JSON_response_data" class
The test examples show multiple ways to parse the JSON responses from "GET" requests using `jsonPath()` and JSON objects.
The test examples require a locally running "json-server" that serves the `store.json` file that is provided in the project root folder. You can start the "json-server" with this command:
```
json-server store.json
```
If you need to find a JSON path, then you can use [this web site](https://jsonpathfinder.com/) to find it.

### "Parsing_XML_response_data" class
The test examples show multiple ways to parse XML responses from "GET" requests using `xmlPath()` and XML objects.

### "Path_and_query_parameters" class
The test example shows the difference between `pathParam()` (part of the URL) and `queryParam()` (the parameter(s) after the "?" in the URL).

### "POJO_POST_request_class" class
This is the POJO (Plain Old Java Object) class that is used to create a POJO object in a test example of the "Four_different_POST_requests" class.

### "XML_schema_validation" class
The test example shows how to validate "GET" responses against the XML Schema `apigeeXmlSchema.xsd` that is stored in the `resources` folder, which is also known as the "Classpath".
If you need to create a new XSD Schema (`*.xsd` file), then you can create one from your existing XML data by using [this web site](https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/).

## TestNG test scheduling and data exchange example in the "testng_execution" package
For this example, you need your own API access token for the "Go REST" web site. [Click here to get your free "Go REST" API access token](https://gorest.co.in/consumer/login) with your existing GitHub, Google, or Microsoft account.

The `testng_execution` package contains four classes that contain one test each.

TestNG is used to execute these tests sequentially (one after the other), as defined in the `testng.xml` file.

TestNG allows exchanging TestNG attributes (variables) between classes. In this example, the first TestNG attribute that gets exchanged between classes is your "bearer token" for the "Go REST" web site. The second TestNG attribute that gets exchanged between classes is the "id" that is returned in the API response when a new user gets created with `Create_user`. This "id" is then subsequently used to get the new user information (`Get_user`), update the new user (`Update_user`), and finally to delete the new user (`Delete_user`).

TestNG attributes are enabled by adding `ITestContext context` to the test methods that use them. You can set TestNG attributes with `setAttribute()`, and you can get TestNG attributes with `getAttribute()`. If you use `getSuite()` as part of your TestNG attribute set or get command, then the scope of your TestNG attribute is for the entire TestNG "Suite" and your attribute will be accessible in all "Test" that are part of the "Suite"(as defined in the `testng.xml` file). If you leave out the `getSuite()` part, then the TestNG attribute only applies to the scope of its "Test" (as defined in the `testng.xml` file). This means that with `getSuite()`, you set and get a kind of "global variable" for the entire "Suite", while without it, you set and get a kind of "local variable" for the "Test" only.

You can execute the TestNG "Suite" in your IDE by using the context menu of the `testng.xml` file.
