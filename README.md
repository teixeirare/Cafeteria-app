# ☕ Cafeteria App

Order system for a coffee shop with a modern, user-friendly graphical interface built in **Java Swing**. The app lets you simulate coffee orders, show the receipt, and complete the purchase — all with a beautiful UI!

## 🎯 Features

- **User Login & Registration** (data saved locally, validation included) 
- **Start Screen** with logo, fullscreen background image, and “Start your order” button  
- **Product Selection**: input customer name, product list in two columns, + and - buttons, quantity display  
- **Visual Cart**: immediate feedback when adding/removing products  
- **Automatic calculation** of total price  
- **Payment**: choose method (credit, debit, cash with change calculation)  
- **Receipt Screen**: customer name, item summary, prices, date/time, payment method  
- **Final Screen**: centered success message, animated coffee cup/latte art, everything visually centered  
- **Modern UI**: rounded buttons, custom icons, responsive for maximized window, background images  

## 🖼️ Technologies Used

- **Java 17**
- **Swing/AWT**
- **VS Code** (with Java extensions)
- **Custom images** (icons, backgrounds, cup, latte art, etc.)

## 🗂️ Project Structure

coffee/
│
├── controller/
│ └── Order.java
│ └── RequestController.java
│ └── User.java
│ └── UserRepository.java
│
├── model/
│ └── JLabelShadow.java
│ └── Product.java
│ └── ProductRepository.java
│ └── RoundedBoot.java
│
├── receipt/
│ └── ReceiptGenerator.java
│ └── ReceiptPrinter.java
│ └── ReceiptSaver.java
│
├── view/
│ └── EndScreen.java
│ └── LoginScreen.java
│ └── OrderScreen.java
│ └── PaymentCash.java
│ └── ReceiptScreen.java
│ └── Registration.java
│ └── StartScreen.java
│
├──Main.java
│

imagens/
│ └── back.png
│ └── backBlack.png
│ └── cup.png
│ └── exit.png
│ └── gpt.png
│ └── home.png
│

## Receipts 

- Receipt created for the customer in the **Printed_receipts/** folder
- Receipt created to prepare the order in the **Printed_receipts/** folder
- Receipt saved for the record in the **Receipts_Cafeteria/** folder

## 📷 Screenshots

- Login and registration
- Start screen 
- Order screen
- Payment (card/cash/change)
- Detailed receipt (HTML style)
- Final “success” screen

> You can find interface images inside the **Screenshots/** folder

## 🚀 How to Run

1. Clone the repository:
    ```sh
    git clone https://github.com/teixeirare/Cafeteria-app.git
    ```
2. Open the project in VSCode with Java extensions enabled.
3. **Run** `Main.java` to start the application.

## 📝 License

This project is free for educational use.  
Images are for illustrative/demo purposes only.

