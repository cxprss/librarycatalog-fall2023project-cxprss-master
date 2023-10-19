package main;

import interfaces.List;



public class User {
	int id;
	String name;
	List<Book> checkedBooks;
	
	public User() {
		this(-25,"",null);
	}
	public User(int id, String name, List<Book> checkedBooks) {
		this.id=id;
		this.name=name;
		this.checkedBooks=checkedBooks;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public List<Book> getCheckedOutList() {
		return checkedBooks;
	}

	public void setCheckedOutList(List<Book> checkedOutList) {
		this.checkedBooks=checkedOutList;
	}
	
}
