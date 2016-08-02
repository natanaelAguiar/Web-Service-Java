package br.com.contatoapi1.contato.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contatoapi1.contato.model.Contato;

public class ContatoJpaDAO {

	private EntityManagerFactory factory;

	public EntityManager getEm() {
		this.factory = Persistence.createEntityManagerFactory("contato");
		return factory.createEntityManager();
	}

	public Contato findById(Long id) {
		EntityManager entityManager = this.getEm();
		Contato contato;
		try {
			contato = entityManager.find(Contato.class, id);
		} finally {
			entityManager.close();
		}
		return contato;
	}

	@SuppressWarnings("unchecked")
	public List<Contato> findAll() {
		EntityManager entityManager = this.getEm();
		List<Contato> listContato;
		try {
			listContato = entityManager.createQuery("FROM " + Contato.class.getName()).getResultList();
		} finally {
			entityManager.close();
		}
		return listContato;
	}

	public Contato saveOrUpdate(Contato contato){
		EntityManager entityManager = this.getEm();
		try {
			entityManager.getTransaction().begin();
			contato = entityManager.merge(contato);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}finally {
			entityManager.close();
		}
		return contato;
	}

	public boolean removeById(Long id) {
		EntityManager entityManager = this.getEm();
		try {
			entityManager.getTransaction().begin();
			Contato contato = entityManager.find(Contato.class, id);
			entityManager.remove(contato);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}finally {
			entityManager.close();
		}
	}
}
