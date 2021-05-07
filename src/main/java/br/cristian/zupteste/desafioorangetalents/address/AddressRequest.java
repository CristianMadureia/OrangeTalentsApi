package br.cristian.zupteste.desafioorangetalents.address;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.cristian.zupteste.desafioorangetalents.user.User;

public class AddressRequest {

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
	private Long idUser;
	
	public Address toModel (EntityManager manager) {
		User user = manager.find(User.class, this.idUser);
		return new Address(this.street, this.number, this.complement, this.district, this.city, this.state, this.cep, user);
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
	
	public Long getIdUser() {
		return idUser;
	}
}
