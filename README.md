☕ Cafeteria App
Order system for a coffee shop, with a user-friendly graphical interface built in Java Swing. The app allows simulating coffee orders, managing a cart, displaying the receipt, and completing the purchase.

🎯 Features
 Start screen with "Start Order" button

 Selection screen with customer name, product list, and interactive buttons

 Add and remove products with visual feedback

 Automatic total value calculation

 Receipt with customer name, date/time, item summary, and payment methods

 Styled buttons and modern interface with images

🖼️ Technologies Used
Java 17

Swing (AWT)

VS Code with Java extensions

Custom images (icons, background, cup, etc.)

🗂️ Project Structure

cafeteria-app/
│
├── controller/
│   └── PedidoController.java
│   └── Pedido.java
│
├── model/
│   └── Produto.java
│   └── ProdutoRepository.java
│   └── BotaoArredondado.java
│   └── JLabelComSombra.java
│
├── view/
│   └── TelaInicio.java
│   └── TelaPedido.java
│   └── TelaPagamento.java
│   └── PagamentoDinheiro.java
│   └── Recibo.java
│   └── TelaFinal.java
│
├── imagens/
│   └── fundo.jpg
│   └── xicara.png
│   └── casa.png
│
└── Main.java
📷 Interface
Interface images can be found in the imagens/ folder or below (if added on GitHub).

🚀 How to Run
Clone the repository:

git clone https://github.com/teixeirare/Cafeteria-app.git

Open the project in VSCode with Java extensions installed.

Run Main.java to start the application.

📝 License
This project is free for learning purposes. Images used are for illustrative purposes only.
