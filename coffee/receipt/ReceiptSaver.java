package coffee.receipt;

import java.io.*;

public class ReceiptSaver {

    public static void saveToFile(String content, String fileName) {
        File folder = new File("ReceiptsCafeteria");
        if (!folder.exists()) folder.mkdirs();
        File file = new File(folder, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
            System.out.println("Saved receipt to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

