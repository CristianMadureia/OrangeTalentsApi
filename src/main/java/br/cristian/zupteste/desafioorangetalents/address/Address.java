package br.cristian.zupteste.desafioorangetalents.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.cristian.zupteste.desafioorangetalents.user.User;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String street;
	
	@NotNull
	private Integer number;
	
	@NotBlank
	private String complement;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String cep;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	/**
	 * 
	 * @param street Endereço
	 * @param number
	 * @param complement
	 * @param district
	 * @param city
	 * @param state
	 * @param cep
	 * @param user
	 */
	public Address(@NotBlank String street, @NotNull Integer number, @NotBlank String complement,
			@NotBlank String district, @NotBlank String city, @NotBlank String state, @NotBlank String cep, @NotNull User user) {
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.city = city;
		this.state = state;
		this.cep = cep;
		this.user = user;
	}
	
	
	@Deprecated
	public Address() {
		super();
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", number=" + number + ", complement=" + complement
				+ ", district=" + district + ", city=" + city + ", state=" + state + ", cep=" + cep + "]";
	}
	public Long getId() {
		return id;
	}
	public String getStreet() {
		return street;
	}
	public Integer getNumber() {
		return number;
	}
	public String getComplement() {
		return complement;
	}
	public String getDistrict() {
		return district;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCep() {
		return cep;
	}
	
	public User getUser() {
		return user;
	}
	
	public AddressResponse toResponse() {
		return new AddressResponse(this);
	}

}
