package br.cristian.zupteste.desafioorangetalents.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.cristian.zupteste.desafioorangetalents.address.AddressResponse;

public class UserResponse {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateBirth;
	private List<AddressResponse> addresses = new ArrayList<AddressResponse>();
	
	public UserResponse(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.dateBirth = user.getDateBirth();
		this.addresses.addAll(user.getAdresses().stream().map(address -> address.toResponse()).collect(Collectors.toList()));
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

	public List<AddressResponse> getAddresses() {
		return addresses;
	}
	
}
