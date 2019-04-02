package com.netcracker;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class BookModel extends AbstractTableModel {

    public  List<Book> books = new ArrayList<>();

    public void BookRun() throws IOException {
        String[] info = new String[7];
        for (String line : Files.readAllLines(Paths.get("TXT.txt"))) {
            int check = 0;
            for (String part : line.split("\\s+")) {
                info[check] = part;
                check++;

            }
            Gender gender =Gender.valueOf(info[6]);
            books.add(new Book(info[0], Integer.parseInt(info[2]),Integer.parseInt(info[1]), Double.parseDouble(info[3]),new Author(info[4],info[5],gender)));
        }
    }
 /*  public com.netcracker.BookModel() {
        books.add(new com.netcracker.Book("123",100,10.5,new com.netcracker.Author("456","789","m")));
    }*/

    public void addBook(Book b){
       books.add(b);
        fireTableDataChanged();
    }
    public void addBook(Book b,Book a,int y){
        books.remove(b);
        books.add(y,a);
        fireTableDataChanged();
    }
    public void deleteBook(Book b){
       books.remove(b);
        fireTableDataChanged();
    }
    public Book getBook(int rowId){
        Book f1 = books.get(rowId);
        return f1;
    }
    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case  2:
                return cur.getYear();
            case 4:
                return cur.getAuthor().getName();
            case 6:
                return cur.getAuthor().getGender();
            case 5:
                return cur.getAuthor().getEmail();
            case 3:
                return cur.getPrice();
            case 1:
                return cur.getQty();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "com.netcracker.Book name";

            case 2:
                return "Year";
            case 4:
                return "com.netcracker.Author";
            case 6:
                return "com.netcracker.Gender";
            case 5:
                return "Email";
            case 3:
                return "Price";
            case 1:
                return "Count";

        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 2:
                return Integer.class;
            case 4:
                return String.class;
            case 6:
                return Gender.class;
            case 5:
                return String.class;
            case 3:
                return Double.class;
            case 1:
                return Integer.class;

        }
        return Object.class;
    }
}
