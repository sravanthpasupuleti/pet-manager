package com.petmanager.entity;

import com.petmanager.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "owner_table")
@Setter
@Getter
@ToString
public class Owner extends Base{

    @Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "mobile_number", nullable = false, unique = true, length = 10)
	private String mobileNumber;

	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;

	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	@JoinColumn(name = "pet_id", referencedColumnName = "id", unique = true, nullable = false)
	private Pet pet;
}
