package br.cristian.zupteste.desafioorangetalents.user;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import br.cristian.zupteste.desafioorangetalents.address.Address;


@Entity
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	
	@CPF
	@NotBlank
	@Column(unique = true)
	private String cpf;
	
	@NotNull
	@Past
	private Date dateBirth;
	
	@OneToMany(mappedBy = "user")
	private List<Address> addresses = new ArrayList<Address>();
	
	/**
	 * 
	 * @param name
	 * @param email
	 * @param cpf
	 * @param dateBirth
	 */
	public User(String name, @Email String email, @CPF String cpf, Date dateBirth) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
	}
	
	@Deprecated
	public User() {
		super();
	}

	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public Date getDateBirth() {
		return dateBirth;
	}
	
	public List<Address> getAdresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
