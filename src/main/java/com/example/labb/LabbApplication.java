package com.example.labb;

public class LabbApplication {

    public static void main(String[] args) throws Exception {
        Section cap1 = new Section("Capitolul 1");
        Paragraph p1 = new Paragraph("Paragraph 1");
        cap1.add(p1);
        Paragraph p2 = new Paragraph("Paragraph 2");
        cap1.add(p2);
        Paragraph p3 = new Paragraph("Paragraph 3");
        cap1.add(p3);
        Paragraph p4 = new Paragraph("Paragraph 4");
        cap1.add(p4);
        cap1.add(new ImageProxy("ImageOne"));
        cap1.add(new Image("ImageTwo"));
        cap1.add(new Paragraph("Some text"));
        cap1.add(new Table("Table 1"));
        BookStatistics stats = new BookStatistics();
        cap1.accept(stats);
        stats.printStatistics();

        Book book = new Book("Noapte buna, copii!");
        Section c1 = new Section("Capitolul 1");
        Section c11 = new Section("Capitolul 1.1");
        Section c2 = new Section("Capitolul 2");
        book.addContent(c1);
        c1.add(c11);
        c1.add(new Paragraph("Text"));
        book.addContent(c2);
        GenerateToC generator = new GenerateToC();
        book.accept(generator);
        generator.getToC().render();
        //cap1.accept(new RenderContentVisitor());
    }
}