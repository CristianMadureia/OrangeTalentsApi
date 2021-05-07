package br.cristian.zupteste.desafioorangetalents.address;

public class AddressResponse {
	
	private String street;
	private Integer number;
	private String complement;
	private String district;
	private String city;
	private String state;
	private String cep;
	private String userName;
	
	public AddressResponse(Address address) {
		super();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.complement = address.getComplement();
		this.district = address.getDistrict();
		this.city = address.getCity();
		this.state = address.getState();
		this.cep = address.getCep();
		this.userName = address.getUser().getName();
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

	public String getUserName() {
		return userName;
	}
	
}
