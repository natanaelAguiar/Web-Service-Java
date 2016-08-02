package br.com.contatoapi1.contato.dao;

import java.util.List;

import br.com.contatoapi1.contato.model.Contato;

public interface ContatoBO {
	
	public Contato saveOrUpdate(Contato contato);
	
	public Contato findById(Long id);
	
	public boolean removeById(Long id);
	
	public List<Contato> findAll();

}
