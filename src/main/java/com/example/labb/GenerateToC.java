package com.example.labb;

public class GenerateToC implements Visitor {
    private final TableOfContents toc = new TableOfContents();
    private int currentPage = 0;

    public TableOfContents getToC() {
        return toc;
    }

    private int depth(Element element) {
        int level = 0;
        for (Element p = element.getParent(); p != null; p = p.getParent()) {
            level++;
        }
        return level;
    }

    @Override
    public void visit(Book book) {
        currentPage = 1; // pagina 1 e coperta/introducerea
    }

    @Override
    public void visit(Section section) {
        currentPage++; // fiecare secțiune începe pe o pagină nouă
        toc.addEntry(section.getTitle(), Math.max(0, depth(section) - 1), currentPage);
    }

    @Override public void visit(TableOfContents t) {}
    @Override public void visit(Paragraph paragraph) {}
    @Override public void visit(ImageProxy imageProxy) {}
    @Override public void visit(Image image) {}
    @Override public void visit(Table table) {}
}