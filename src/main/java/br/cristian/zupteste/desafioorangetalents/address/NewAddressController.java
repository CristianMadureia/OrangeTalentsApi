package br.cristian.zupteste.desafioorangetalents.address;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cristian.zupteste.desafioorangetalents.exceptions.AddressRegisterException;
import br.cristian.zupteste.desafioorangetalents.exceptions.UserRegisterException;


@RestController
public class NewAddressController {
	
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private ViaCepClient viaCepClient;

	@PostMapping("/addresses")
	@Transactional
	public ResponseEntity<AddressResponse> register (@RequestBody @Valid AddressRequest request, UriComponentsBuilder uriBuilder) throws AddressRegisterException {
		
		String cep = request.getCep();
		if(!cep.matches("^[ ]*[0-9]{5}-?[0-9]{3}[ ]*$"))
			throw new AddressRegisterException(HttpStatus.BAD_REQUEST, "O cep informado é inválido.");	
	
		
		
		AddressViaCep addressViaCep = viaCepClient.searchAddressByCep(cep.replaceAll("[- ]", ""));
		if(addressViaCep.getErro())
			throw new AddressRegisterException(HttpStatus.BAD_REQUEST, "O cep informado é inválido.");
		
		List<?> users = manager
				.createQuery("SELECT u FROM User u WHERE u.id = :id")
				.setParameter("id", request.getIdUser())
				.getResultList();
		
		if(users.size() == 0)
			throw new AddressRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, esse usuário não existe.");
		
		Address address = request.toModel(manager);
		manager.persist(address);
		URI uri =  uriBuilder.path("/addresses/{id}").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).body(new AddressResponse(address));
	}
}
