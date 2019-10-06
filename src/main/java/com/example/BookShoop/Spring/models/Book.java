package com.example.BookShoop.Spring.models;

public class Book extends AbstractModel {
    private long bookID;
    private String name;
    private String author;
    private int pagesSum;

    public Book(long bookID, String name, String author,int pagessum) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.pagesSum = pagessum;
    }


    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPagesSum() {
        return pagesSum;
    }

    public void setPagesSum(int pagesSum) {
        this.pagesSum = pagesSum;
    }
}
