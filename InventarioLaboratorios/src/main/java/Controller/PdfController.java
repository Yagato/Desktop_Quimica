package Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PdfController {
    URL font_path = Thread.currentThread().getContextClassLoader().getResource("fonts/Montserrat-Light.otf");

    private String[] id;
    private String[] nombre;
    private String[] cantidad;

    public PdfController(String[] id, String[] nombre, String[] cantidad){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public void generatePDF(String path) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));

        writer.setPageEvent(new PdfPageEventHelper() {
            public void onEndPage(PdfWriter writer, Document document){
                try {
                    createHeader().writeSelectedRows(0, -1,
                            document.left(),
                            document.top() + document.topMargin() - 10,
                            writer.getDirectContent());

                    createFooter().writeSelectedRows(0, -1,
                            document.left(),
                            document.bottom(),
                            writer.getDirectContent());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        document.setPageSize(PageSize.A4);
        document.setMargins(36, 36,
                20 + createHeader().getTotalHeight(),
                30 + createFooter().getTotalHeight());
        document.setMarginMirroring(false);
        document.open();

        document.add(createDataTable());

        System.out.println("PDF file generated successfully");

        document.close();
    }

    public PdfPTable createHeader() throws DocumentException, IOException {
        PdfPTable headerTable = new PdfPTable(2);
        String path_tec_mexico = "src/main/resources/imagenes/header.png";
        headerTable.setSplitLate(false);
        headerTable.setSplitRows(true);
        headerTable.setWidthPercentage(50);
        headerTable.setLockedWidth(true);
        headerTable.setTotalWidth(500);

        headerTable.addCell(createImageCell(path_tec_mexico)).setBorder(Rectangle.NO_BORDER);
        headerTable.addCell(createEmptyCell());
        headerTable.addCell(createEmptyCell());
        headerTable.addCell(createTitleCell());

        return headerTable;
    }

    public PdfPCell createEmptyCell() {
        PdfPCell emptyCell = new PdfPCell();
        emptyCell.setBorder(Rectangle.NO_BORDER);
        return emptyCell;
    }

    public PdfPCell createTitleCell() {
        Font font1 = generateFont();
        font1.setStyle(Font.BOLD);
        font1.setSize(10);
        font1.setColor(BaseColor.GRAY);

        Font font2 = generateFont();
        font2.setStyle(Font.BOLD);
        font2.setSize(8);
        font2.setColor(BaseColor.GRAY);

        PdfPCell titleCell = new PdfPCell();
        Chunk chunk1 = new Chunk("Instituto Tecnológico de Ciudad Madero\n", font1);
        Chunk chunk2 = new Chunk("Departamento de Química y Bioquímica\n", font2);
        Paragraph paragraph = new Paragraph(new Chunk(chunk1));
        paragraph.add(new Chunk(chunk2));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        titleCell.addElement(paragraph);
        titleCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        titleCell.setBorder(Rectangle.NO_BORDER);
        return titleCell;
    }

    public PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image image = Image.getInstance(path);
        return new PdfPCell(image, true);
    }

    public PdfPTable createFooter() throws DocumentException, IOException {
        PdfPTable footerTable = new PdfPTable(1);
        String path_footer = "src/main/resources/imagenes/footer.png";
        footerTable.setSplitLate(false);
        footerTable.setSplitRows(true);
        footerTable.setWidthPercentage(100);
        footerTable.setLockedWidth(true);
        footerTable.setTotalWidth(527);

        footerTable.addCell(createImageCell(path_footer)).setBorder(Rectangle.NO_BORDER);

        return footerTable;
    }

    public PdfPTable createDataTable() {
        PdfPTable table = new PdfPTable(3);
        table.setSplitLate(false);
        table.setSplitRows(true);

        Font fontHeader = generateFont();
        fontHeader.setSize(15);
        fontHeader.setStyle(Font.BOLD);

        PdfPCell column1 = new PdfPCell(new Phrase("Laboratorio", fontHeader));
        column1.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell column2 = new PdfPCell(new Phrase("Reactivo/Material", fontHeader));
        column2.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell column3 = new PdfPCell(new Phrase("Cantidad", fontHeader));
        column3.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(column1);
        table.addCell(column2);
        table.addCell(column3);

        table.setHeaderRows(1);

        for(int i = 0; i < id.length ; i++){
            table.addCell(new Phrase(id[i], FontFactory.getFont("Montserrat-Light", 12)));
            table.addCell(new Phrase(nombre[i], FontFactory.getFont("Montserrat-Light", 12)));
            table.addCell(new Phrase(cantidad[i], FontFactory.getFont("Montserrat-Light", 12)));
        }

        return table;
    }

    public Font generateFont(){
        FontFactory.register(font_path.toString(), "Montserrat-Light");
        return FontFactory.getFont("Montserrat-Light");
    }
}
