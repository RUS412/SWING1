

public class Book {
    private String name;
    private Author author;
    private double price;
    private int qty=0;
    private int   year;

    public Book(double price, Author author, String name) {
        this.price = price;
        this.author = author;
        this.name = name;
    }

    public Book(String name, int qty,int year, double price, Author author) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.author = author;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name +" " + year+" " + qty  +" " + price +" " + author
               ;
    }
}
