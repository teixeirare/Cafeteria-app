# ☕ Cafeteria App

Sistema de pedidos para uma cafeteria, com interface gráfica amigável construída em **Java Swing**. O app permite simular pedidos de café, controlar o carrinho, exibir o recibo e finalizar a compra.

---

## 🎯 Funcionalidades

- [x] Tela inicial com botão "Começar Pedido"
- [x] Tela de seleção com nome do cliente, lista de produtos e botões interativos
- [x] Adição e remoção de produtos com feedback visual
- [x] Cálculo automático do valor total
- [x] Recibo com nome do cliente, data/hora, resumo dos itens e formas de pagamento
- [x] Botões estilizados e interface moderna com imagens

---

## 🖼️ Tecnologias utilizadas

- **Java 17**
- **Swing (AWT)**
- **VS Code com extensões Java**
- Imagens personalizadas (ícones, fundo, xícara etc.)

---

## 🗂️ Estrutura do Projeto

cafeteria-app/
│
├── controller/
│ └── PedidoController.java
│ └── Pedido.java
│
├── model/
│ └── Produto.java
│ └── ProdutoRepository.java
│ └── BotaoArredondado.java
│ └── JLabelComSombra.java
│
├── view/
│ └── TelaInicio.java
│ └── TelaPedido.java
│ └── TelaPagamento.java
│ └── PagamentoDinheiro.java
│ └── Recibo.java
│ └── TelaFinal.java
│
├── imagens/
│ └── fundo.jpg
│ └── xicara.png
│ └── casa.png
│
└── Main.java

yaml
Copiar
Editar

---

## 📷 Interface

Imagens da interface podem ser vistas diretamente na pasta `imagens/` ou abaixo (caso você adicione via GitHub).

---

## 🚀 Como rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/teixeirare/Cafeteria-app.git
Abra o projeto no VSCode com extensões Java instaladas.

Execute Main.java para iniciar.

📝 Licença
Este projeto é livre para fins de aprendizado. Imagens utilizadas são apenas ilustrativas.
