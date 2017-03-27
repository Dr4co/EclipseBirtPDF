import java.io.FileOutputStream;

/**
 * Created by Dr4co on 2017-03-27.
 */
public class Main {

    public static void main(String [] args) throws Exception {
        PDFGeneratorService IRS = new PDFGeneratorService();
        byte[] pdf = IRS.generatePdfFromTemplate();

        FileOutputStream fileOuputStream = null;
        try {
            fileOuputStream = new FileOutputStream("pdfTest.pdf");
            fileOuputStream.write(pdf);
        } finally {
            if (fileOuputStream != null){
                fileOuputStream.close();
            }
        }

        System.out.println(pdf);
    }
}
