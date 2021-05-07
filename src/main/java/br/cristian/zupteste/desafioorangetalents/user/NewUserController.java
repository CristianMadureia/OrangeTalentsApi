package br.cristian.zupteste.desafioorangetalents.user;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cristian.zupteste.desafioorangetalents.exceptions.UserRegisterException;

@RestController
public class NewUserController {
	
	@Autowired
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/users")
	@Transactional
	public ResponseEntity<UserResponse> register(@RequestBody @Valid NewUserRequest request, UriComponentsBuilder uriBuilder) throws UserRegisterException {	
		List<?> users = manager
				.createQuery("SELECT u FROM User u WHERE u.cpf LIKE :cpf OR u.email LIKE :email")
				.setParameter("cpf", request.getCpf())
				.setParameter("email", request.getEmail())
				.getResultList();
		
		if(users.size() > 1) {
			throw new UserRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, ocorreu algum erro inesperado, existe mais de um usuario com mesmo cpf ou email.");
		} else if(users.size() == 1) {
			User user = (User) users.get(0);
			
			boolean cpfEquals = user.getCpf().equals(request.getCpf());
			boolean emailEquals = user.getEmail().equals(request.getEmail());
			
			if(cpfEquals && emailEquals) {
				throw new UserRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, já existe um usuário com o mesmo cpf e o mesmo e-mail.");
			} else if(cpfEquals) {
				throw new UserRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, já existe um usuário com o mesmo cpf.");
			} else {
				throw new UserRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, já existe um usuário com o mesmo e-mail.");
			}
			
		} else {
			
			User user = request.toModel();
			manager.persist(user);
			
			URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new UserResponse(user));
		}	
	}

	

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponse> getUserWithAdresses(@PathVariable Long userId) throws UserRegisterException {
		List<?> users = manager
				.createQuery("SELECT u FROM User u WHERE u.id = :id")
				.setParameter("id", userId)
				.getResultList();
		
		if(users.size() == 0)
			throw new UserRegisterException(HttpStatus.BAD_REQUEST, "Desculpe, não encontramos um usuário com este id.");
		
		User user = manager.find(User.class, userId);
		return ResponseEntity.ok(new UserResponse(user));
	}
}
