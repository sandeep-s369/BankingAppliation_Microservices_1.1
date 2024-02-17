package com.nagarro.banking.customers.entity;

import jakarta.persistence.CascadeType;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
		private String name;
	    private long phone;
	    private String mail;
	    private String pan;
//	    @OneToOne(cascade = CascadeType.ALL)
//		@JoinColumn(name="fk_accountNumber")
//	    private Account account;
	    
	    
	    
//		public Customer() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
		public Customer(long id, String name, long phone, String mail, String pan, Account account) {
			super();
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.mail = mail;
			this.pan = pan;
		//	this.account = account;
		}
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
		
//		public Account getAccount() {
//			return account;
//		}
//		public void setAccount(Account account) {
//			this.account = account;
//		}

	    
	    
}
