package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;



/*
 * Why Singly linked list you ask?
 * simple: unused ram is wasted ram
 ⠀⠀⠘⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀
⠀⠀⠀⠑⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡔⠁⠀⠀⠀
⠀⠀⠀⠀⠈⠢⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠊⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⢀⣀⣀⣀⣀⣀⡀⠤⠄⠒⠈⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠘⣀⠄⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀
⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠛⠛⠛⠋⠉⠈⠉⠉⠉⠉⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢿⣿⣿⣿⣿
⣿⣿⣿⣿⡏⣀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿
⣿⣿⣿⢏⣴⣿⣷⠀⠀⠀⠀⠀⢾⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿
⣿⣿⣟⣾⣿⡟⠁⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣷⢢⠀⠀⠀⠀⠀⠀⠀⢸⣿
⣿⣿⣿⣿⣟⠀⡴⠄⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⣿
⣿⣿⣿⠟⠻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠶⢴⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⣿
⣿⣁⡀⠀⠀⢰⢠⣦⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⡄⠀⣴⣶⣿⡄⣿
⣿⡋⠀⠀⠀⠎⢸⣿⡆⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⠗⢘⣿⣟⠛⠿⣼
⣿⣿⠋⢀⡌⢰⣿⡿⢿⡀⠀⠀⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣧⢀⣼
⣿⣿⣷⢻⠄⠘⠛⠋⠛⠃⠀⠀⠀⠀⠀⢿⣧⠈⠉⠙⠛⠋⠀⠀⠀⣿⣿⣿⣿⣿
⣿⣿⣧⠀⠈⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠟⠀⠀⠀⠀⢀⢃⠀⠀⢸⣿⣿⣿⣿
⣿⣿⡿⠀⠴⢗⣠⣤⣴⡶⠶⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡸⠀⣿⣿⣿⣿
⣿⣿⣿⡀⢠⣾⣿⠏⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠉⠀⣿⣿⣿⣿
⣿⣿⣿⣧⠈⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿
⣿⣿⣿⣿⡄⠈⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣦⣄⣀⣀⣀⣀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠙⣿⣿⡟⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠁⠀⠀⠹⣿⠃⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⢐⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⠿⠛⠉⠉⠁⠀⢻⣿⡇⠀⠀⠀⠀⠀⠀⢀⠈⣿⣿⡿⠉⠛⠛⠛⠉⠉
⣿⡿⠋⠁⠀⠀⢀⣀⣠⡴⣸⣿⣇⡄⠀⠀⠀⠀⢀⡿⠄⠙⠛⠀⣀⣠⣤⣤⠄⠀
 * 
 */



public class LibraryCatalog {
	
	SinglyLinkedList<Book> catalog;
	SinglyLinkedList<User> users;
		
	public LibraryCatalog() throws IOException {
		this.catalog=(SinglyLinkedList<Book>) getBooksFromFiles();
		this.users=(SinglyLinkedList<User>) getUsersFromFiles();
	}
	private List<Book> getBooksFromFiles() throws IOException {
		SinglyLinkedList<Book> res=new SinglyLinkedList<>();
		BufferedReader reader=new BufferedReader(new FileReader("data/catalog.csv"));
		String line=null;
		while(reader.ready()) {
			line=reader.readLine();
			String[] data=line.split(",");
			if(data[0].equalsIgnoreCase("ID"))continue;
			
			String[] dateData=data[4].split("-");
			LocalDate date=LocalDate.of(Integer.parseInt(dateData[0]), Integer.parseInt(dateData[1]), Integer.parseInt(dateData[2]));
					
			res.add(new Book(Integer.parseInt(data[0]),data[1],data[2],data[3],date,Boolean.parseBoolean(data[5])));
		}
		reader.close();
		return res;
	}
	
	private List<User> getUsersFromFiles() throws IOException {
		BufferedReader reader=new BufferedReader(new FileReader("data/user.csv"));
		SinglyLinkedList<User> res=new SinglyLinkedList<>();
		
		String line=null;
		while(reader.ready()) {
			line=reader.readLine();
			String[] data=line.split(",");
			if(data[0].equalsIgnoreCase("ID"))continue;
			

			SinglyLinkedList<Book> books=new SinglyLinkedList<>();
			if(data.length>=3) {
				String[] bookData = data[2].replace("{", "").replace("}", "").split(" ");

				for (String id : bookData) {
					for (Book book : catalog) {
						if (book.getId() == Integer.parseInt(id)) {
							books.add(book);
						}
					}
				}
			}
					
			res.add(new User(Integer.parseInt(data[0]),data[1],books));
		}
		reader.close();
		return res;
	}
	public List<Book> getBookCatalog() {
		return catalog;
	}
	public List<User> getUsers() {
		return users;
	}
	public void addBook(String title, String author, String genre) {
		Book book=new Book(catalog.size()+1,title,author,genre,LocalDate.of(2023, 9, 15),false);
		catalog.add(book);
		return;
	}
	public void removeBook(int id) {
		for(int i=0;i<catalog.size();i++) {
			Book book=catalog.get(i);
			if(book.getId()==id) {
				catalog.remove(i);
			}
		}
		return;
	}	
	
	public boolean checkOutBook(int id) {
		for(Book book:catalog) {
			if(book.getId()==id && !book.isCheckedOut()) {
				book.setCheckedOut(true);
				book.setLastCheckOut(LocalDate.of(2023, 9, 15));
				return true;
			}
		}
		return false;
	}
	public boolean returnBook(int id) {
		for(Book book:catalog) {
			if(book.getId()==id && book.isCheckedOut()) {
				book.setCheckedOut(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean getBookAvailability(int id) {
		for(Book book:catalog) {
			if(book.getId()==id) {
				return !book.isCheckedOut();
			}
		}
		return false;
	}
	public int bookCount(String title) {
		return this.searchForBook(book->book.getTitle().equals(title)).size();
	}
	public void generateReport() throws IOException {
		
		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";
		/*
		 * In this section you will print the amount of books per category.
		 * 
		 * Place in each parenthesis the specified count. 
		 * 
		 * Note this is NOT a fixed number, you have to calculate it because depending on the 
		 * input data we use the numbers will differ.
		 * 
		 * How you do the count is up to you. You can make a method, use the searchForBooks()
		 * function or just do the count right here.
		 */
		output += "Adventure\t\t\t\t\t" + (this.searchForBook(book->book.genre.equals("Adventure")).size()) + "\n";
		output += "Fiction\t\t\t\t\t\t" + (this.searchForBook(book->book.genre.equals("Fiction")).size()) + "\n";
		output += "Classics\t\t\t\t\t" + (this.searchForBook(book->book.genre.equals("Classics")).size()) + "\n";
		output += "Mystery\t\t\t\t\t\t" + (this.searchForBook(book->book.genre.equalsIgnoreCase("mystery")).size()) + "\n";
		output += "Science Fiction\t\t\t\t\t" + (this.searchForBook(book->book.genre.equalsIgnoreCase("science fiction")).size()) + "\n";
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (this.getBookCatalog().size()) + "\n\n";
		
		/*
		 * This part prints the books that are currently checked out
		 */
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		/*
		 * Here you will print each individual book that is checked out.
		 * 
		 * Remember that the book has a toString() method. 
		 * Notice if it was implemented correctly it should print the books in the 
		 * expected format.
		 * 
		 * PLACE CODE HERE
		 */
		for(Book book:this.searchForBook(book->book.isCheckedOut())) {
			output += book.toString()+"\n";
		}
		
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (this.searchForBook(book->book.isCheckedOut()).size()) + "\n\n";
		
		
		/*
		 * Here we will print the users the owe money.
		 */
		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";
		/*
		 * Here you will print all the users that owe money.
		 * The amount will be calculating taking into account 
		 * all the books that have late fees.
		 * 
		 * For example if user Jane Doe has 3 books and 2 of them have late fees.
		 * Say book 1 has $10 in fees and book 2 has $78 in fees.
		 * 
		 * You would print: Jane Doe\t\t\t\t\t$88.00
		 * 
		 * Notice that we place 5 tabs between the name and fee and 
		 * the fee should have 2 decimal places.
		 * 
		 * PLACE CODE HERE!
		 */
		float totalFees=0f;
		for(User user:this.getUsers()) {
			float fees=0f;
			for(Book book:user.getCheckedOutList()) {
				fees+=book.calculateFees();
			}
			if(fees>0) {
				output += user.getName()+"\t\t\t\t\t$"+String.format(java.util.Locale.US,"%.2f", fees)+'\n';

				totalFees+=fees;
			}
		}

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + (String.format(java.util.Locale.US,"%.2f", totalFees)) + "\n\n\n";
		output += "\n\n";
		System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		/*
		 * Here we will write to the file.
		 * 
		 * The variable output has all the content we need to write to the report file.
		 * 
		 * PLACE CODE HERE!!
		 */
		BufferedWriter w=new BufferedWriter(new FileWriter("report/report.txt"));
		w.write(output);
		w.close();
	}
	
	/*
	 * BONUS Methods
	 * 
	 * You are not required to implement these, but they can be useful for
	 * other parts of the project.
	 */
	public List<Book> searchForBook(FilterFunction<Book> func) {
		SinglyLinkedList<Book> books=new SinglyLinkedList<>();
		for(Book book: this.getBookCatalog()) {
			if(func.filter(book)) {
				books.add(book);
			}
		}
		return books;
	}
	
	public List<User> searchForUsers(FilterFunction<User> func) {
		SinglyLinkedList<User> users=new SinglyLinkedList<>();
		for(User user: this.getUsers()) {
			if(func.filter(user)) {
				users.add(user);
			}
		}
		return users;
	}
	
}
