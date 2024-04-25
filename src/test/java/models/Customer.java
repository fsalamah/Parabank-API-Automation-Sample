package models;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
	
    private int id;	
    private String firstName;
	private String lastName;
    private Address address;
    private String phoneNumber;
    private String ssn;
  
}
