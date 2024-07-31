package cs3220.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class RecepieEntry {
	@Id
	@GeneratedValue
	private Integer recepieId;
	private String userName;
	@NotBlank(message = "Message cannot be empty")
	private String recepie;
	private LocalDate date;
	@ManyToOne
	private UserEntry user;
	private String recipeName;

	public RecepieEntry() {
		this.setDate(LocalDate.now());
	}

	public RecepieEntry(String userName, String recepie, String recipeName, UserEntry user) {
		this.userName = userName;
		this.recepie = recepie;
		this.recipeName = recipeName;
		this.setDate(LocalDate.now());
		this.user = user;
	}
	

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public Integer getId() {
		return recepieId;
	}

	public void setId(Integer recepieId) {
		this.recepieId = recepieId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecepie() {
		return recepie;
	}

	public void setRecepie(String recepie) {
		this.recepie = recepie;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public UserEntry getUser() {
		return user;
	}

	public void setUser(UserEntry user) {
		this.user = user;
	}

}