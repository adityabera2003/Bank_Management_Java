# Bank Management System

A comprehensive Java-based Bank Management System with a graphical user interface (GUI) built using Java Swing. This application simulates an ATM (Automated Teller Machine) system, allowing users to perform various banking operations.

## Features

- **User Registration**: Multi-step signup process to create new bank accounts
- **Secure Login**: Card number and PIN-based authentication
- **Deposit**: Add money to account balance
- **Cash Withdrawal**: Withdraw money from account
- **Fast Cash**: Quick withdrawal options with predefined amounts
- **Balance Enquiry**: Check current account balance
- **Mini Statement**: View recent transaction history
- **PIN Change**: Update account PIN for security
- **Transaction History**: Track all banking activities

## Technologies Used

- **Java**: Core programming language
- **Java Swing**: GUI framework for desktop application
- **MySQL**: Database for storing user and transaction data
- **JDBC**: Database connectivity
- **JDateChooser**: Date selection component

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)

## Database Setup

1. Install MySQL Server
2. Create a database named `bankSystem`
3. Update the database credentials in `Connn.java`:
   ```java
   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","your_username","your_password");
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

3. Compile the Java files:
   ```bash
   javac -cp "path/to/mysql-connector.jar" src/bank/management/system/*.java
   ```

4. Run the application:
   ```bash
   java -cp "src:path/to/mysql-connector.jar" bank.management.system.Login
   ```

## Project Structure

```
Bank_Management_Java/
├── src/
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