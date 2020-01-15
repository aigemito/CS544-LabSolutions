package edu.miu.lab2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String ISBN;
	private String author;
	private double price;
	private java.util.Date public_date;
	
	public Book() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public java.util.Date getPublic_date() {
		return public_date;
	}

	public void setPublic_date(java.util.Date public_date) {
		this.public_date = public_date;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", ISBN=" + ISBN + ", author=" + author + ", price=" + price + ", public_date="
				+ public_date + "]";
	}
	
}
