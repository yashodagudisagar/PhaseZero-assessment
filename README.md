A Spring Boot–based microservice designed for managing product catalogs.
It provides REST APIs for creating, validating, and managing part numbers, including duplicate checks, structured validation, and global exception handling.


phasezero-catalog-service/
 ├── src/main/java/
 │   ├── Phasezero/
 │   │   ├── DTO/
 │   │   ├── Entity/
 │   │   ├── Controller/
 │   │   ├── exception/
 │   │   ├── Repository/
 │   │   ├── Service/
 │   │   └── PhasezeroApplication.java
 ├── src/main/resources/
 │   ├── application.properties
 └── pom.xml

and the main requests are
GET-> "/products" which returns all the records present in list format
POST-> "/prodcut" which is used to store product details into database
GET-> "/product/search" to search values based on names
GET-> "/product/filter" to filter out products based on category 
GET-> "/product/sort" it will return sorted result based on prise in asscending order
GET-> "/product/inventory/value" it will find total invetory value
