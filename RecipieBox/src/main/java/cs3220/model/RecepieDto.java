package cs3220.model;

import java.time.LocalDate;

public class RecepieDto {
	private Integer id;
	private String userName;
	private String recepie;
	private LocalDate date;
	private Integer userId;
	private String recipeName;

	public RecepieDto() {
	}

	public RecepieDto(RecepieEntry recepieEntry) {
		id = recepieEntry.getId();
		userName = recepieEntry.getUserName();
		recepie = recepieEntry.getRecepie();
		recipeName = recepieEntry.getRecipeName();
		date = recepieEntry.getDate();
		userId = recepieEntry.getUser().getId();
	}

	public RecepieEntry newRecepie() {
		RecepieEntry newRecepieEntry = new RecepieEntry();
		newRecepieEntry.setId(id);
		newRecepieEntry.setRecepie(recepie);
		newRecepieEntry.setRecipeName(recipeName);
		newRecepieEntry.setDate(LocalDate.now());
		newRecepieEntry.setUser(new UserEntry()); 
		newRecepieEntry.getUser().setId(userId);
		newRecepieEntry.setUserName(userName);
		return newRecepieEntry;
	}
	
	

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}



}
