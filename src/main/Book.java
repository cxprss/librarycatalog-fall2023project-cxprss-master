package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book {
	int id;
	String title,author,genre;
	LocalDate lastCheckout;
	
	boolean checkout;
	
	public Book() {
		this(-10,"","","",LocalDate.now(),false);
	}
	
	public Book(int id,String title,String author, String genre,LocalDate lastCheckout, boolean checkout) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.genre=genre;
		this.lastCheckout=lastCheckout;
		this.checkout=checkout;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre=genre;
	}
	public LocalDate getLastCheckOut() {
		return lastCheckout;
	}
	public void setLastCheckOut(LocalDate lastCheckOut) {
		this.lastCheckout=lastCheckOut;
	}
	public boolean isCheckedOut() {
		return checkout;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.checkout=checkedOut;
	}
	
	@Override
	public String toString() {
		/*
		 * This is supposed to follow the format
		 * 
		 * {TITLE} By {AUTHOR}
		 * 
		 * Both the title and author are in uppercase.
		 */
//		return "";
		return title.toUpperCase()+" BY "+author.toUpperCase();
	}
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		float fees=0;
		
		LocalDate currDate = LocalDate.of(2023, 9, 15);
		
		long daysBetween = ChronoUnit.DAYS.between(this.getLastCheckOut(), currDate);
		if(daysBetween >= 31) {
			long daysOver=Math.max(daysBetween-31, 0);
			
			fees=10f + 1.50f * daysOver;
		}
		
		return fees;
	}
}
