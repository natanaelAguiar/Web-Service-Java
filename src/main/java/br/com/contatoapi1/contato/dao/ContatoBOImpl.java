package br.com.contatoapi1.contato.dao;

import java.util.List;

import br.com.contatoapi1.contato.model.Contato;

public class ContatoBOImpl implements ContatoBO {

	private ContatoJpaDAO contatoJpaDAO;
//	private Contato contato = new Contato();

	public ContatoBOImpl(ContatoJpaDAO contatoJpaDAO) {
		this.contatoJpaDAO = contatoJpaDAO;
	}

	public Contato saveOrUpdate(Contato contato) {
		if (contato.getNome() != null && contato.getEmail() != null) {
			contato = contatoJpaDAO.saveOrUpdate(contato);
			return contato;
		} else {
			return null;
		}
	}

	public Contato findById(Long id) {
		if (id != null) {
			return contatoJpaDAO.findById(id);
		} else {
			return null;
		}
	}

	public boolean removeById(Long id) {
		if (id != null) {
			return contatoJpaDAO.removeById(id);
		}
		else{
			return false;
		}

	}

	public List<Contato> findAll() {
		return contatoJpaDAO.findAll();
	}

}
