# ☕ Cafeteria App

Order system for a coffee shop with a modern, user-friendly graphical interface built in **Java Swing**. The app lets you simulate coffee orders, manage a cart, show the receipt, and complete the purchase — all with a beautiful UI!

## 🎯 Features

- **Start Screen** with logo, fullscreen background image, and “Start your order” button  
- **User Login & Registration** (data saved locally, validation included)  
- **Product Selection**: input customer name, product list in two columns, + and - buttons, quantity display  
- **Visual Cart**: immediate feedback when adding/removing products  
- **Automatic calculation** of total price  
- **Payment Screen**: choose method (credit, debit, cash with change calculation)  
- **Receipt Screen**: customer name (auto-capitalized), item summary, prices, date/time, payment method  
- **Final Screen**: centered success message, animated coffee cup/latte art, everything visually centered  
- **Modern UI**: rounded buttons, custom icons, responsive for maximized window, background images  

## 🖼️ Technologies Used

- **Java 17**
- **Swing/AWT**
- **VS Code** (with Java extensions)
- **Custom images** (icons, backgrounds, cup, latte art, etc.)

## 🗂️ Project Structure

cafeteria-app/
│
├── controller/
│ └── Order.java
│ └── RequestController.java
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
├── imagens/
│ └── home.png
│ └── fundo.jpg / fundo.png
│ └── logo.jpg / logo.png
│ └── xicarates.jpg / xicara_latte.png
│
└── Main.java


## 📷 Screenshots

- Start screen with background image and logo
- Login and registration with centered fields
- Product selection (responsive for any screen size)
- Payment screen (card/cash/change)
- Detailed receipt (HTML style)
- Final “success” screen with coffee cup image

> You can find interface images inside the **imagens/** folder or add them to this README.

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

**Latest commit:**  
[55582176d1837e0de91809a7d8b480aacbbb9fb0](https://github.com/teixeirare/Cafeteria-app/commit/55582176d1837e0de91809a7d8b480aacbbb9fb0)  
_Last updated: 2024-07-18_

*Tip: Add screenshots of the UI below to make your README even more attractive!*


