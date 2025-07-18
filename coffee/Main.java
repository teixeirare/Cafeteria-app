package coffee;



import coffee.view.LoginScreen;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the application...");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Java vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java home: " + System.getProperty("java.home"));
        

        new  LoginScreen();
    }
}

// ============================== print screen size ========================

/*Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
System.out.println("Largura: " + d.width + ", Altura: " + d.height);*/