package com.msg.msg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -6440695620165525838L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@JoinColumn(name = "fk_role_id", referencedColumnName = "id")
	@ManyToOne
	private Role role;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@Column(name = "photo_link")
	private String photoLink;

	@Column(name = "is_active")
	private int activeStatus;
	
	@ManyToMany
	@JoinTable(
	name="trainer_area",
	joinColumns=@JoinColumn(name="fk_trainer_id"),
	inverseJoinColumns=@JoinColumn(name="fk_area_id"))
	List <Area> areas;
	
	@ManyToMany
	@JoinTable(
	name="trainer_specialization",
	joinColumns=@JoinColumn(name="fk_trainer_id"),
	inverseJoinColumns=@JoinColumn(name="fk_training_type"))
	List<TrainingType> trainingTypes;

	@OneToMany
	@JoinColumn(name = "fk_sender_id", referencedColumnName = "iduser")
	@JsonIgnore
	private List<Message> fromMsgs;

	@OneToMany
	@JoinColumn(name = "fk_receiver_id", referencedColumnName = "iduser")
	@JsonIgnore
	private List<Message> toMsgs;

	@OneToMany
	@JoinColumn(name = "fk_client_id", referencedColumnName = "iduser")
	@JsonIgnore
	private List<TrainingSession> clientSessions;

	@OneToMany
	@JoinColumn(name = "fk_trainer_id", referencedColumnName = "iduser")
	@JsonIgnore
	private List<TrainingSession> trainerSessions;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Token> tokens;

	public User() {
	}

//	public User(int id, String username, String password, Role role, String firstName, String lastName, String email,
//			double price, String description, String photoLink) {
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.role = role;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.price = price;
//		this.description = description;
//		this.photoLink = photoLink;
//	}

	public User(String username, String password, Role role, String firstName, String lastName, String email,
			double price, String description) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.price = price;
		this.description = description;
		this.trainingTypes=new ArrayList<TrainingType>();
		this.areas=new ArrayList<Area>();
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String retrievePassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getPassword() {
		return password;
	}

	public List<Message> getFromMsgs() {
		return fromMsgs;
	}

	public void setFromMsgs(List<Message> fromMsgs) {
		this.fromMsgs = fromMsgs;
	}

	public List<Message> getToMsgs() {
		return toMsgs;
	}

	public void setToMsgs(List<Message> toMsgs) {
		this.toMsgs = toMsgs;
	}

	public List<TrainingSession> getClientSessions() {
		return clientSessions;
	}

	public void setClientSessions(List<TrainingSession> clientSessions) {
		this.clientSessions = clientSessions;
	}

	public List<TrainingSession> getTrainerSessions() {
		return trainerSessions;
	}

	public void setTrainerSessions(List<TrainingSession> trainerSessions) {
		this.trainerSessions = trainerSessions;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	

	public List<Area> getAreas() {
		return areas;
	}
	
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	

	public List<TrainingType> getTrainingTypes() {
		return trainingTypes;
	}

	public void setTrainingTypes(List<TrainingType> trainingTypes) {
		this.trainingTypes = trainingTypes;
	}
	
	public void addTrainingType(TrainingType trainingType) {
		this.trainingTypes.add(trainingType);
	}
	
	public void removeTrainingType(TrainingType trainingType) {
		this.trainingTypes.remove(this.trainingTypes.indexOf(trainingType));
	}

	public void addArea(Area area) {
		this.areas.add(area);
	}
	
	public void removeArea(Area area) {
		this.areas.remove(this.areas.indexOf(area));
	}
	
	

	public static void validateUser(User user) {
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", price=" + price + ", description=" + description
				+ "]";
	}

}
