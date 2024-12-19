Amazon Locker Service Design

1. Introduction
Amazon Locker is an innovative self-service delivery and return system offered by Amazon. It provides a secure and convenient alternative to home delivery, catering to customers with busy schedules or safety concerns about package theft. By integrating lockers in public locations like grocery stores, shopping malls, and convenience stores, Amazon ensures seamless and flexible delivery and return options.
This document details the design, functionality, benefits, and technical specifications of the Amazon Locker system.
________________________________________
2. Overview of Amazon Locker Service
2.1 What is Amazon Locker?
Amazon Locker is a network of automated lockers that facilitates package delivery and returns. Customers can select a nearby locker during checkout, receive a unique code for access, and retrieve or return their package at their convenience.
2.2 Key Features
●	Delivery Convenience: Customers can pick up packages on their way home or while running errands.
●	Secure Access: Lockers are accessed with a one-time pickup code, ensuring only authorized access.
●	Flexibility: Lockers are placed in easily accessible public areas and operate according to the hosting location's working hours.
●	Returns Made Easy: Customers can return eligible items by dropping them off at a locker.
2.3 Benefits of Amazon Locker
●	No Additional Costs: The service is free of charge to Amazon Prime members and regular customers.
●	Reduced Theft Risk: Secure lockers eliminate porch piracy.
●	Time Flexibility: Customers can retrieve packages anytime during the locker location’s operating hours.



3. Amazon Locker Usage Workflow
 
3.1 For Delivery
1.	Locker Selection During Checkout:
o	While placing an order, the customer selects “Amazon Locker” as the delivery method.
o	Allows customers to search for nearby lockers using latitude and longitude of the geolocation.

2.	Locker Assignment:
o	Lockers are assigned based on proximity, availability, and package size.
3.	Package Pickup Process:
o	The customer visits the designated locker location.
o	They enter the unique pickup code provided in the notification.
o	The locker door opens automatically, and the customer retrieves the package.
o	Code Expiry: The pickup code expires once the locker door is closed.
4.	Uncollected Packages:
o	If the package is not picked up within 3 days, it is returned to Amazon, and a refund is initiated.
3.2 For Returns
1.	Initiating a Return:
o	Customers initiate a return on the Amazon app or website.
o	A locker return option is provided for eligible items.
2.	Locker Drop-Off:
o	The system generates a one-time return code, similar to delivery.
o	Customers visit the designated locker, enter the return code, and place the item in the locker.

________________________________________


4. System Design
4.1 Objects and Relationships
1.Locker
Description:
The Locker abstract class represents the foundational structure for storage units. It defines common attributes and behaviors shared by all locker types, such as unique ID, size classification, availability status, physical location, dimensions, and total volume capacity.

StandardLocker
A standard locker designed to store regular packages without specific temperature requirements.

RefrigeratedLocker
A specialized locker designed to store temperature-sensitive items such as food or medicine.
Key Methods:
•	checkAndGetAvailableLockers(): Validates locker availability based on size and location.
Throws:
o	LockerNotFoundException: Raised if no lockers are found matching the criteria.
o	PackageSizeMappingException: Triggered when no lockers can accommodate the package size.
•	getLockerStatus(): Retrieves the current status of a locker (e.g., available, booked).
2.Location
Description:
A Location refers to physical sites containing multiple lockers. Each location is identified by a unique ID and geo-coordinates. It manages a list of associated lockers and tracks total available storage volume.
Key Methods:
•	isNearBy(): Determines if a location is within a defined radius of the delivery destination.
Throws:
o	LockerNotFoundException: Raised when no nearby lockers are available.
•	getLockerLocation(): Retrieves details about a specific location, including geo-coordinates and the list of lockers.
3.Package
Description:
A Package represents the items stored in lockers. Each package has a unique ID, dimensions (length, width, height), and a calculated volume.
Key Methods:
•	getPackageSize(): Returns the size of the package based on dimensions.
Throws:
o	PackageSizeMappingException: Triggered when no size mapping exists for the package dimensions.
•	getLockerSizeForPack(): Identifies the appropriate locker size for storing the package.
4.Order
Description:
An Order represents a customer’s purchase request, linked to one or more packages. Attributes include a unique order ID, delivery geo-location, and a list of items.
Key Methods:
•	getDeliveryGeoLocation(): Retrieves the delivery destination for the order.
Throws:
o	PickupCodeExpiredException: Triggered if the delivery code has expired due to delays in processing.
5.Delivery
Description:
Delivery manages the process of transferring packages to customers. It includes mechanisms for secure retrieval using access codes.
Key Methods:
•	deliver(): Handles the package delivery process by assigning a locker and generating a secure access code.
Throws:
o	LockerCodeMisMatchException: Raised when the provided pickup code does not match the stored code.
o	PackPickTimeExceededException: Triggered if the package is not picked up within the allowed time.
•	setCode(): Generates and assigns secure access codes for package retrieval.


________________________________________
5.	Algorithm Design
Problem 1: Closest Locker Problem
Description:
The algorithm calculates the distance between the package destination and all locker locations, then iterates over the sorted list to find the closest available location.
Algorithm:
1.	Inputs: Customer coordinates, list of locker locations.
2.	Calculate the distance between the customer location and all locker locations.
3.	Sort lockers by proximity to the customer.
4.	Select the nearest locker that is available.
Pseudocode:
FUNCTION isNearby(loc1, loc2, radius):
    distance = SQRT((loc1.latitude - loc2.latitude)^2 + (loc1.longitude - loc2.longitude)^2)
    RETURN distance <= radius

FUNCTION getLocker(lockerSize, location):
    DEFINE searchRadius = 10.0
    lockerList = FILTER lockers WHERE:
        locker.status == "AVAILABLE" AND
        locker.size == lockerSize AND
        isNearby(locker.geoLocation, location, searchRadius)
    IF lockerList IS NOT EMPTY:
        RETURN lockerList[0]
    RETURN null
Pickup Code Generation
Description:-
A randomized 6-digit alphanumeric code is generated for each order or return request. Codes are cryptographically secure and expire upon first use or after the 3-day holding period.
Algorithm:
1.	Inputs: Length of the code (default = 6).
2.	Generate a random alphanumeric string of the given length.
3.	Store the code securely and associate it with the order.
4.	Expire the code upon use or after 3 days.
Pseudocode:
FUNCTION generatePickupCode(length):
    RETURN RandomAlphanumericString(length)
________________________________________
6.Security Measures: 

Authentication and Authorization
To ensure the security of locker operations, authentication and verification mechanisms validate pickup and return codes. These measures are crucial to prevent unauthorized access to lockers or misuse of codes. Below are the security implementations used in the system.
Authentication and Verification Process
1.	Encrypted Validation of Codes:
o	Pickup and return codes are encrypted and verified before allowing access to the lockers.
o	Only valid, non-expired codes can authorize operations.
2.	Locker and Code Validation:
o	Lockers are linked to unique package IDs and verification codes.
o	The system checks for a match between the provided locker ID, code, and the stored information.
o	Incorrect codes trigger exceptions and deny access.
3.	Time-Sensitive Validation:
o	Pickup codes have a limited validity period.
o	Codes expire automatically after the 3-day holding window or once used.
o	Time-based access ensures lockers operate only during designated operating hours.

6.1 Exception Handling for Security
1.	LockerNotFoundException:
Triggered when the system cannot locate the locker ID in the repository.
2.	LockeCodeMisMatchException:
Raised when the provided code does not match the expected code for the locker.
3.	PickupCodeExpiredException:
Generated when the code's validity has expired or is used outside the holding window.
4.	PackPickTimeExceededException:
Thrown when the package is not picked within the specified duration, resulting in cancellation or reallocation. 
________________________________________
7. Story Line

A Day in Mia’s Life with Amazon Locker
Mia’s Busy Life
Mia, a software developer, struggles to be home for deliveries. Amazon Locker becomes her go-to solution for secure, convenient package handling.
1. Placing an Order
Mia orders a laptop stand and selects the nearby "Greenwood Locker Hub" for delivery. The system verifies locker availability using checkAndGetAvailableLockers() and assigns a medium locker.
2. Delivery and Code Generation
The system maps her package size with getLockerSizeForPack() and generates a secure pickup code, "X3H7LZ." The delivery driver stores the package, and Mia gets a notification.
3. Picking Up the Package
Mia visits the hub and enters her code. The authenticateAndAuthorize() function validates it. If the code were expired or incorrect, appropriate exceptions like PickupCodeExpiredException or LockeCodeMisMatchException would trigger. Thankfully, her code works, and she retrieves her package.
4. Special Scenarios
•	If Mia arrived outside operating hours, isWithinOperatingHours() would deny access.
•	If she delayed pickup for over 3 days, PackPickTimeExceededException would reallocate the package.
5. Easy Returns
The next day, Mia uses a new pickup code to drop off a return at the same hub. The system ensures secure and smooth processing with deliver().
Amazon Locker simplifies Mia’s deliveries with proximity, secure codes, and efficient exceptions. It’s the perfect solution for her busy lifestyle.

________________________________________
8. Conclusion 

The Amazon Locker system is a secure and efficient way to handle package delivery and returns, designed with the customer in mind. It works by matching packages to the right locker size and securing access with unique pickup codes. Features like time-based access and automatic handling of missed pickups or locker unavailability make the system reliable and hassle-free. It’s built to handle large volumes of orders across many locations, ensuring smooth and consistent service. By solving common problems like missed deliveries and safety concerns, Amazon Locker offers a convenient and dependable option for busy people. This makes it a valuable solution for simplifying deliveries and keeping customers happy.

