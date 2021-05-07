package br.cristian.zupteste.desafioorangetalents.address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viaCepClient")
public interface ViaCepClient {
	
	@GetMapping("{cep}/json")
	AddressViaCep searchAddressByCep(@PathVariable("cep") String cep);
}