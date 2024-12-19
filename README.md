# Amazon Locker Service Design

## Introduction

Amazon Locker is an innovative self-service delivery and return system offered by Amazon. It provides a secure and convenient alternative to home delivery, catering to customers with busy schedules or safety concerns about package theft. By integrating lockers in public locations like grocery stores, shopping malls, and convenience stores, Amazon ensures seamless and flexible delivery and return options.

This document details the design, functionality, benefits, and technical specifications of the Amazon Locker system.

## Overview of Amazon Locker Service

### What is Amazon Locker?

Amazon Locker is a network of automated lockers that facilitates package delivery and returns. Customers can select a nearby locker during checkout, receive a unique code for access, and retrieve or return their package at their convenience.

### Key Features

- **Delivery Convenience:** Customers can pick up packages on their way home or while running errands.
- **Secure Access:** Lockers are accessed with a one-time pickup code, ensuring only authorized access.
- **Flexibility:** Lockers are placed in easily accessible public areas and operate according to the hosting location's working hours.
- **Returns Made Easy:** Customers can return eligible items by dropping them off at a locker.

### Benefits of Amazon Locker

- **No Additional Costs:** The service is free of charge to Amazon Prime members and regular customers.
- **Reduced Theft Risk:** Secure lockers eliminate porch piracy.
- **Time Flexibility:** Customers can retrieve packages anytime during the locker location’s operating hours.

## Amazon Locker Usage Workflow

### For Delivery

1. **Locker Selection During Checkout:**
   - Customers select "Amazon Locker" as the delivery method during checkout.
   - Lockers are chosen based on proximity using geolocation data (latitude and longitude).

2. **Locker Assignment:**
   - Lockers are assigned based on proximity, availability, and package size.

3. **Package Pickup Process:**
   - Customers visit the designated locker location.
   - They enter the unique pickup code provided in the notification.
   - The locker door opens automatically, and customers retrieve their package.

4. **Uncollected Packages:**
   - If the package is not picked up within 3 days, it is returned to Amazon, and a refund is initiated.

### For Returns

1. **Initiating a Return:**
   - Customers initiate a return on the Amazon app or website.
   - A locker return option is provided for eligible items.

2. **Locker Drop-Off:**
   - The system generates a one-time return code, similar to delivery.
   - Customers visit the designated locker, enter the return code, and place the item in the locker.

## System Design

### Objects and Relationships

#### Locker

- **StandardLocker:** A standard locker designed to store regular packages without specific temperature requirements.
- **RefrigeratedLocker:** A specialized locker for temperature-sensitive items such as food or medicine.

Key Methods:

- `checkAndGetAvailableLockers()`: Validates locker availability based on size and location.
  - Throws:
    - `LockerNotFoundException`: Raised if no lockers are found matching the criteria.
    - `PackageSizeMappingException`: Triggered when no lockers can accommodate the package size.
- `getLockerStatus()`: Retrieves the current status of a locker (e.g., available, booked).

#### Location

- **Description:** Physical sites containing multiple lockers. Each location is identified by a unique ID and geo-coordinates.

Key Methods:

- `isNearBy()`: Determines if a location is within a defined radius of the delivery destination.
  - Throws:
    - `LockerNotFoundException`: Raised when no nearby lockers are available.
- `getLockerLocation()`: Retrieves details about a specific location, including geo-coordinates and the list of lockers.

#### Package

- **Description:** Represents the items stored in lockers.

Key Methods:

- `getPackageSize()`: Returns the size of the package based on dimensions.
  - Throws:
    - `PackageSizeMappingException`: Triggered when no size mapping exists for the package dimensions.
- `getLockerSizeForPack()`: Identifies the appropriate locker size for storing the package.

#### Order

- **Description:** Represents a customer’s purchase request linked to one or more packages.

Key Methods:

- `getDeliveryGeoLocation()`: Retrieves the delivery destination for the order.
  - Throws:
    - `PickupCodeExpiredException`: Triggered if the delivery code has expired due to delays in processing.

#### Delivery

- **Description:** Manages the process of transferring packages to customers.

Key Methods:

- `deliver()`: Handles the package delivery process by assigning a locker and generating a secure access code.
  - Throws:
    - `LockerCodeMisMatchException`: Raised when the provided pickup code does not match the stored code.
    - `PackPickTimeExceededException`: Triggered if the package is not picked up within the allowed time.
- `setCode()`: Generates and assigns secure access codes for package retrieval.

## Algorithm Design

### Closest Locker Problem

#### Description:
The algorithm calculates the distance between the package destination and all locker locations, then iterates over the sorted list to find the closest available location.

#### Algorithm:

```python
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
```

### Pickup Code Generation

#### Description:
A randomized 6-digit alphanumeric code is generated for each order or return request. Codes are cryptographically secure and expire upon first use or after the 3-day holding period.

#### Algorithm:

```python
FUNCTION generatePickupCode(length):
    RETURN RandomAlphanumericString(length)
```

## Security Measures

### Authentication and Verification Process

- **Encrypted Validation of Codes:** Pickup and return codes are encrypted and verified before allowing access to lockers.
- **Time-Sensitive Validation:** Pickup codes have a limited validity period and expire automatically after 3 days or upon first use.

### Exception Handling for Security

- `LockerNotFoundException`: Triggered when the system cannot locate the locker ID in the repository.
- `LockeCodeMisMatchException`: Raised when the provided code does not match the expected code for the locker.
- `PickupCodeExpiredException`: Generated when the code's validity has expired or is used outside the holding window.
- `PackPickTimeExceededException`: Thrown when the package is not picked within the specified duration.

## Story Line

### A Day in Mia’s Life with Amazon Locker

**Mia’s Busy Life:** Mia, a software developer, struggles to be home for deliveries. Amazon Locker becomes her go-to solution for secure, convenient package handling.

1. **Placing an Order:**
   - Mia orders a laptop stand and selects the nearby "Greenwood Locker Hub" for delivery.
   - The system verifies locker availability and assigns a medium locker.

2. **Delivery and Code Generation:**
   - The system maps her package size and generates a secure pickup code, "X3H7LZ."
   - The delivery driver stores the package, and Mia gets a notification.

3. **Picking Up the Package:**
   - Mia visits the hub and enters her code. The system validates it, and she retrieves her package.

4. **Special Scenarios:**
   - If Mia arrived outside operating hours, access would be denied.
   - If she delayed pickup for over 3 days, her package would be reallocated.

5. **Easy Returns:**
   - The next day, Mia uses a new pickup code to drop off a return at the same hub.

Amazon Locker simplifies Mia’s deliveries with proximity, secure codes, and efficient exceptions.

## Conclusion

The Amazon Locker system is a secure and efficient way to handle package delivery and returns, designed with the customer in mind. It works by matching packages to the right locker size and securing access with unique pickup codes. Features like time-based access and automatic handling of missed pickups or locker unavailability make the system reliable and hassle-free. By solving common problems like missed deliveries and safety concerns, Amazon Locker offers a convenient and dependable option for busy people, ensuring smooth and consistent service.
