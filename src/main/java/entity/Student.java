package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends AbstractModelIdName{

	private String lastName;
	private	String groupe;
	private Date date;
	private String textDate;
	
	public Student(){
		super();
	}
	
	public Student(Integer id, String name, String lastName, String groupe, Date date){
		super(id, name);
		this.lastName = lastName;
		this.groupe = groupe;
		this.date = date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.textDate = sdf.format(date);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public Date getDate() {
		return date;
	}

	public String getTextDate() {
		return textDate;
	}

	public void setDate(Date date) {
		this.date = date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.textDate = sdf.format(date);
	}
	
	

}
