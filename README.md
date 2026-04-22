# Bank Management System

A comprehensive Java-based Bank Management System with a graphical user interface (GUI) built using Java Swing. This application simulates an ATM (Automated Teller Machine) system, allowing users to perform various banking operations.

## Features

- **User Registration**: Multi-step signup process to create new bank accounts
- **Secure Login**: Card number and PIN-based authentication with input validation
- **Deposit**: Add money to account balance with validation
- **Cash Withdrawal**: Withdraw money from account with balance checks
- **Fast Cash**: Quick withdrawal options with predefined amounts
- **Balance Enquiry**: Check current account balance
- **Mini Statement**: View recent transaction history (last 10 transactions)
- **PIN Change**: Update account PIN for security with validation
- **Transaction History**: Track all banking activities

## Technologies Used

- **Java**: Core programming language
- **Java Swing**: GUI framework for desktop application
- **MySQL**: Database for storing user and transaction data
- **JDBC**: Database connectivity with PreparedStatement for security
- **JDateChooser**: Date selection component

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)

## Database Setup

1. Install MySQL Server
2. Create a database named `bankSystem`
3. Create the required tables (see database schema below)
4. Configure database connection in `src/db.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/bankSystem?useSSL=false&serverTimezone=UTC
   db.username=your_username
   db.password=your_password
   ```

### Database Schema

#### signup table
```sql
CREATE TABLE signup (
    formno VARCHAR(20),
    name VARCHAR(100),
    fname VARCHAR(100),
    dob VARCHAR(20),
    gender VARCHAR(20),
    email VARCHAR(100),
    marital VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    pincode VARCHAR(10),
    state VARCHAR(50)
);
```

#### signup2 table
```sql
CREATE TABLE signup2 (
    formno VARCHAR(20),
    religion VARCHAR(50),
    category VARCHAR(50),
    income VARCHAR(50),
    education VARCHAR(50),
    occupation VARCHAR(50),
    pan VARCHAR(20),
    aadhar VARCHAR(20),
    seniorcitizen VARCHAR(10),
    existingaccount VARCHAR(10)
);
```

#### signup3 table
```sql
CREATE TABLE signup3 (
    formno VARCHAR(20),
    accountType VARCHAR(50),
    cardnumber VARCHAR(25),
    pin VARCHAR(10),
    facility VARCHAR(100)
);
```

#### login table
```sql
CREATE TABLE login (
    formno VARCHAR(20),
    cardnumber VARCHAR(25),
    pin VARCHAR(10)
);
```

#### bank table
```sql
CREATE TABLE bank (
    pin VARCHAR(10),
    date DATE,
    type VARCHAR(20),
    amount DOUBLE
);
```

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/adityabera2003/Bank_Management_Java.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Bank_Management_Java
   ```

3. Set up the database as described above

4. Configure database credentials in `src/db.properties`

5. Compile the Java files:
   ```bash
   javac -cp "path/to/mysql-connector.jar:src" src/bank/management/system/*.java
   ```

6. Run the application:
   ```bash
   java -cp "src:path/to/mysql-connector.jar" bank.management.system.Login
   ```

## Security Improvements

This version includes several security enhancements:

- **Prepared Statements**: All database queries use PreparedStatement to prevent SQL injection
- **Input Validation**: Comprehensive validation for all user inputs
- **Secure Configuration**: Database credentials stored in external properties file
- **Error Handling**: Proper exception handling with user-friendly messages
- **Resource Management**: Automatic connection closing with try-with-resources

## Project Structure

```
Bank_Management_Java/
├── src/
│   ├── db.properties                 # Database configuration
│   └── bank/
│       └── management/
│           └── system/
│               ├── BalanceEnquriy.java    # Balance enquiry functionality
│               ├── Connn.java             # Database connection class
│               ├── Deposit.java           # Deposit money feature
│               ├── FastCash.java          # Fast cash withdrawal
│               ├── Login.java             # User login interface
│               ├── main_Class.java        # Main ATM interface
│               ├── mini.java              # Mini statement feature
│               ├── Pin.java               # PIN change functionality
│               ├── Signup.java            # User registration (Page 1)
│               ├── Signup2.java           # User registration (Page 2)
│               ├── Signup3.java           # User registration (Page 3)
│               └── Withdrawl.java         # Cash withdrawal feature
├── icon/                      # Application icons and images
├── .gitignore                 # Git ignore file
└── README.md                  # Project documentation
```

## Usage

1. **First Time Users**: Use the signup process to create a new account
2. **Existing Users**: Login with your card number and PIN
3. **ATM Operations**: Choose from various banking operations using the GUI buttons

## Validation Rules

- **Card Number**: 10-19 digits
- **PIN**: 4-6 digits
- **Email**: Valid email format
- **Pincode**: 6 digits
- **Deposit Amount**: Positive number, max Rs. 1,000,000
- **Withdrawal Amount**: Positive number, max Rs. 10,000, multiples of Rs. 100

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is for educational purposes. Please ensure compliance with local banking regulations and data protection laws when adapting for real-world use.

## Disclaimer

This is a simulation application for educational purposes only. It does not handle real financial transactions and should not be used for actual banking operations.