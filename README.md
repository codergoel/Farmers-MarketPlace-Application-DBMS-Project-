# Farmers Marketplace Application

## Overview
The Farmers Marketplace Application is designed to connect farmers directly with consumers, facilitating the sale and purchase of agricultural products through a digital platform. This project aims to enhance direct market access for farmers, improve the efficiency of agricultural sales, and provide consumers with access to fresh produce.

## Features
- **Online Marketplace**: Farmers can list their products, and buyers can purchase them directly.
- **User Onboarding Panels**: Separate onboarding processes for farmers and buyers to register and manage their profiles.
- **Product Panels**: Display available products in a card format with options to buy and specify quantities.
- **Dynamic Order Management**: Supports creating, updating, and tracking orders in real-time.
- **Payment and Review Systems**: Secure payment gateway and a system for buyers to review products.
- **Quality Assurance and Tracking**: Ensures product quality and allows users to track order statuses.

## Technology Stack
- **Frontend**: Java Swing for the graphical user interface.
- **Backend**: Java with JDBC for database interaction.
- **Database**: MySQL for storing all application data.

## Setup and Installation

### Prerequisites
- Java JDK 11 or newer.
- MySQL Server 8.0 or newer.
- An IDE like IntelliJ IDEA or Eclipse for Java development (optional but recommended).

### Database Configuration
1. **Create Database**:
   ```sql
   CREATE DATABASE farmers_market;
   ```
2. **Import Schema**:
   - Navigate to your MySQL command line or use a GUI like MySQL Workbench to import the initial database schema provided in the SQL file.

### Running the Application
1. **Compile the Java Code**:
   - If using the command line:
     ```bash
     javac -d bin src/*.java
     ```
   - If using an IDE, build the project using the IDE's build tools.

2. **Run the Application**:
   ```bash
   java -cp bin Main
   ```

## User Guide

### Welcome Panel
- Start by selecting either "Farmer" or "Buyer" to navigate to the respective onboarding panel.

### User Onboarding Panel
- New users can register by filling out their details.
- Existing users can update or delete their profiles.
- Login to move to the Product Panel for Buyers.

### Product Panel for Buyers
- Browse available products.
- Select quantity and click "Buy" to proceed to the Payment Panel.

### Payment Panel
- Review your order details.
- Confirm payment which then redirects to the Order Tracking Panel.

### Order Tracking Panel
- Track the status of your order once payment is confirmed.

### Review Panel
- After receiving your order, you can leave a review for the product.

### Farmer Onboarding and Product Registration Panel
- Farmers can register, manage their product listings, and update or delete existing entries.

## Development Notes
- Ensure that the JDBC connection settings in `DatabaseConnection.java` are configured according to your MySQL setup.
- The system assumes a simple security model; consider enhancing authentication and authorization for production use.

## Contributing
Contributions to the Farmers Marketplace Application are welcome! Please fork the repository, make your changes, and submit a pull request.

