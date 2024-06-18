**Technical Design**:

This application has been developed in Java using Spring Boot. Spring Boot is used for the rest api development for simplified setup and development.

**Tech Stack**:

Spring Boot: To build and run the application.
Spring Web: To create Restful web services.
Junit: Test cases are implemented using Junit & Mockito frameworks.

The in-memory caching is achieved through the Vector data structure. This has been choosen considering the concurrent calls to the publish service. i.e Multiple threads threads will be accessing and modifying the list items. Vector is synchronised and thread safe and can be used in a multi-threaded environment.

This application follows the Repository Design Pattern. It provides a clean separation betweee business logic and data access logic which makes it easier to manage and test.

**Sequence Diagram**:

![SequenceDiagram](https://github.com/vrvasantharaj/matsuri-price/assets/101232744/82fa66a0-b87e-40de-88ba-4babcbf7aac3)

**Class Diagram**:

![ClassDiagram](https://github.com/vrvasantharaj/matsuri-price/assets/101232744/c10d63ef-c96a-425b-a37b-efa84ccd32e2)

**Components**:

**Repository Interface (PriceRepository)**:

The PriceRepository interface provides the methods for interacting with the data layer.

**Repository Implementation (PriceRepositoryImpl)**:

This class provides the actual implementation of the PriceRepository interface.

List<Price> stores the Price objects.

**Methods**:

save(Price price): This method saves a Price object into the list.

getPriceByIsin(String isinNo): This method retrieves a list of Price objects based on the isinNo.

getPriceByVendor(String vendorId): This method retrieves a list of Price objects based on vendorId.
removeOldPrices(): This method removes Price objects that are older than 30 days.

**REST API**:

The application exposes three end points,

[title] http://localhost:8080/publishprice

[title] http://localhost:8080/isin/:isinNo

[title] http://localhost:8080/vendor/:vendorIds


**Improvements**:

The price information can be fed into the cube asychronously through a messaging system rather than API. 

For better performance, in-memory data store like Redis can be used. 

PriceRepositoryImpl class can be modified to interact with the database with minimal effort.
