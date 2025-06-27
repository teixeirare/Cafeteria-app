package coffee.receipt;

import java.awt.*;
import java.awt.print.*;

public class ReceiptPrinter {

    public static void print(String content, String jobName) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName(jobName);
        job.setPrintable((Graphics g, PageFormat pf, int page) -> {
            if (page > 0) return Printable.NO_SUCH_PAGE;
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g.setFont(new Font("Monospaced", Font.PLAIN, 10));
            int y = 15;
            for (String line : content.split("\n")) {
                g.drawString(line, 10, y);
                y += 15;
            }
            return Printable.PAGE_EXISTS;
        });
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try { job.print(); } catch (PrinterException e) { e.printStackTrace(); }
        }
    }
}



/* (sem diálogo) 

try {
    job.print(); // Imprime sem perguntar
} catch (PrinterException e) {
    e.printStackTrace();
}
 */

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /// 

 /* Com as duas opções (diálogo ou não) 
 
 public static void print(String content, String jobName, boolean showDialog) {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setJobName(jobName);
    job.setPrintable((Graphics g, PageFormat pf, int page) -> {
        if (page > 0) return Printable.NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g.setFont(new Font("Monospaced", Font.PLAIN, 10));
        int y = 15;
        for (String line : content.split("\n")) {
            g.drawString(line, 10, y);
            y += 15;
        }
        return Printable.PAGE_EXISTS;
    });

    if (showDialog) {
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try { job.print(); } catch (PrinterException e) { e.printStackTrace(); }
        }
    } else {
        try { job.print(); } catch (PrinterException e) { e.printStackTrace(); }
    }
}


E na chamada, só decide:

ReceiptPrinter.print(content, jobName, true); → Mostra diálogo

ReceiptPrinter.print(content, jobName, false); → Imprime direto


 */