package br.com.contatoapi1.contato.controller;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.contatoapi1.contato.dao.ContatoBOImpl;
import br.com.contatoapi1.contato.dao.ContatoJpaDAO;
import br.com.contatoapi1.contato.model.Contato;

@CrossOrigin(origins = "*")
@RestController
public class ContatoApiController {

	private ContatoBOImpl contatoBOImpl = new ContatoBOImpl(new ContatoJpaDAO());

	@RequestMapping(value = "/contato", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Contato> findAll() {
		   return contatoBOImpl.findAll();
	}
	
	@RequestMapping(value = "/addContato", headers="Accept=application/json", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Contato addContato(@RequestBody Contato contato){
		return contatoBOImpl.saveOrUpdate(contato);
	}
	
	@RequestMapping(value = "/findById/{id}", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Contato findById(@PathVariable(value = "id") Long id){
		return contatoBOImpl.findById(id);
	}
	
	@RequestMapping(value = "/removeById/{id}", headers="Accept=application/json", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void removeById(@PathVariable(value = "id") Long id){
		contatoBOImpl.removeById(id);
	}
}