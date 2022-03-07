# FOOD DELIVERY MANAGEMENT SYSTEM


Food Delivery Management System implementation in Java.

![image](https://user-images.githubusercontent.com/73665965/157125834-73e8eccf-db22-44db-af41-194584945a0d.png)

## Requirements
Design and implement a food delivery management system for a catering company. The client can
order products from the company’s menu. The system should have three types of users that log in
using a username and a password: administrator, regular employee, and client.

The administrator can:
* Import the initial set of products which will populate the menu from a .csv file.
* Manage the products from the menu: add/delete/modify products and create new products
composed of several products (an example of composed product could be named “daily
menu 1” composed of a soup, a steak, a garnish, and a dessert).
* Generate reports about the performed orders considering the following criteria:
o time interval of the orders – a report should be generated with the orders performed
between a given start hour and a given end hour regardless the date.
o the products ordered more than a specified number of times so far.
o the clients that have ordered more than a specified number of times and the value
of the order was higher than a specified amount.
o the products ordered within a specified day with the number of times they have
been ordered.
The client can:
* Register and use the registered username and password to log in within the system.
* View the list of products from the menu.
* Search for products based on one or multiple criteria such as keyword (e.g. “soup”), rating,
number of calories/proteins/fats/sodium/price.
* Create an order consisting of several products – for each order the date and time will be
persisted and a bill will be generated that will list the ordered products and the total price
of the order.
The employee is notified each time a new order is performed by a client so that it can prepare the
delivery of the ordered products.
Consider the system of classes in Figure 1 as a starting point for the system design. Other classes
and packages can be added to design and implement the full functionality of the application.
Figure 1: Class diagram to be considered as starting design of the system.
To implement the system, consider the following:
1) Define the interface IDeliveryServiceProcessing containing the main operations that can
   be executed by the administrator and client, as follows:
   * Administrator: import products, manage the products from the menu, generate
   reports
   * Client: create new order which implies computing the price for an order and
   generating a bill in .txt format, searching for products based on several criteria.
2) Define and implement the classes from the class diagram shown above:
   * Use the Composite Design Pattern for defining the classes MenuItem,
   BaseProduct and CompositeProduct
   * Use the Observer Design Pattern to notify the employee each time a new order
   is created.
3) Implement the class DeliveryService using a predefined JCF collection which uses a
   hashtable data structure. The hashtable key will be generated based on the class Order,
   which can have associated several MenuItems.
   * Define a structure of type Map<Order, Collection<MenuItem>> for storing the
   order related information in the DeliveryService class. The key of the Map will
   be formed of objects of type Order, for which the hashCode() method will be
   overwritten to compute the hash value within the Map from the attributes of the
   Order (OrderID, date, etc.).
   * Define a structure of type Collection<MenuItem> which will save the menu
   (i.e. all the products) provided by the catering company. Choose the appropriate
   collection type for your implementation.
   * Define a method of type “well formed” for the class DeliveryService.
   * Implement the class using Design by Contract method (involving pre, post
   conditions, invariants, and assertions).
4) The base products used initially for populating the DeliveryService object can be loaded
   from the products.csv file (adapted from Link) using lambda expressions and stream
   processing. Note: the administrator can manually add other base products as well.
5) The menu items, performed orders and user information will be persisted using
   serialization so as to be available at future system executions by means of deserialization.



## Description
This projects includes:
- Working with large volume of data (.csv file)
- Serialization/Deserialization
- Design Patterns 
- Design By Contract
- Lambda Expressions
- Layered Architecture
- Multi-User App


###### Note: Detailed documentation attached
