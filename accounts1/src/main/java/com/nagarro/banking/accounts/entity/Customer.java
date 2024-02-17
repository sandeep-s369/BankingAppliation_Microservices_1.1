package com.nagarro.banking.accounts.entity;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	
    private long id;
	private String name;
    private long phone;
    private String mail;
    private String pan;
    
//	public Customer() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	
	
//	public Customer(long id, String name, long phone, String mail, String pan) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.phone = phone;
//		this.mail = mail;
//		this.pan = pan;
//	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public long getPhone() {
		return phone;
	}



	public void setPhone(long phone) {
		this.phone = phone;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public String getPan() {
		return pan;
	}



	public void setPan(String pan) {
		this.pan = pan;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", mail=" + mail + ", pan=" + pan
				+ "]";
	}



	

	

}