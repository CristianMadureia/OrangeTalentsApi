package br.cristian.zupteste.desafioorangetalents.user;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;

public class NewUserRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@CPF
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Past
	private Date dateBirth;
	
	public User toModel() {
		return new User(this.name, this.email, this.cpf, this.dateBirth);
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
	
	
}
