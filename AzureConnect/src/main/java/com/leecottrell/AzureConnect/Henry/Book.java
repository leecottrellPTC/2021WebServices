package com.leecottrell.AzureConnect.Henry;

import java.util.Objects;

public class Book {
    private String BookCode;
    private String Title;
    private String PublisherCode;
    private String Type;
    private String Paperback;
    private String Stuff;

    public Book() {
    }

    public Book(String BookCode, String Title, String PublisherCode, String Type, String Paperback, String Stuff) {
        this.BookCode = BookCode;
        this.Title = Title;
        this.PublisherCode = PublisherCode;
        this.Type = Type;
        this.Paperback = Paperback;
        this.Stuff = Stuff;
    }

    public String getBookCode() {
        return this.BookCode;
    }

    public void setBookCode(String BookCode) {
        this.BookCode = BookCode;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPublisherCode() {
        return this.PublisherCode;
    }

    public void setPublisherCode(String PublisherCode) {
        this.PublisherCode = PublisherCode;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getPaperback() {
        return this.Paperback;
    }

    public void setPaperback(String Paperback) {
        this.Paperback = Paperback;
    }

    public String getStuff() {
        return this.Stuff;
    }

    public void setStuff(String Stuff) {
        this.Stuff = Stuff;
    }

    public Book BookCode(String BookCode) {
        setBookCode(BookCode);
        return this;
    }

    public Book Title(String Title) {
        setTitle(Title);
        return this;
    }

    public Book PublisherCode(String PublisherCode) {
        setPublisherCode(PublisherCode);
        return this;
    }

    public Book Type(String Type) {
        setType(Type);
        return this;
    }

    public Book Paperback(String Paperback) {
        setPaperback(Paperback);
        return this;
    }

    public Book Stuff(String Stuff) {
        setStuff(Stuff);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(BookCode, book.BookCode) && Objects.equals(Title, book.Title) && Objects.equals(PublisherCode, book.PublisherCode) && Objects.equals(Type, book.Type) && Objects.equals(Paperback, book.Paperback) && Objects.equals(Stuff, book.Stuff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BookCode, Title, PublisherCode, Type, Paperback, Stuff);
    }

    @Override
    public String toString() {
        return "{" +
            " BookCode='" + getBookCode() + "'" +
            ", Title='" + getTitle() + "'" +
            ", PublisherCode='" + getPublisherCode() + "'" +
            ", Type='" + getType() + "'" +
            ", Paperback='" + getPaperback() + "'" +
            ", Stuff='" + getStuff() + "'" +
            "}";
    }
    
}
