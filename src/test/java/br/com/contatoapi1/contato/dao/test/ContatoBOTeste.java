package br.com.contatoapi1.contato.dao.test;

import org.junit.Before;
import org.junit.Test;

import br.com.contatoapi1.contato.dao.ContatoBO;
import br.com.contatoapi1.contato.dao.ContatoBOImpl;
import br.com.contatoapi1.contato.dao.ContatoJpaDAO;
import br.com.contatoapi1.contato.model.Contato;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ContatoBOTeste {
	ContatoJpaDAO contatoJpaDAO;
	ContatoBO contatoBOImpl;
	Contato contato;
	
	@Before
	public void setUp(){
		this.contatoJpaDAO = mock(ContatoJpaDAO.class);
		this.contatoBOImpl = new ContatoBOImpl(contatoJpaDAO);
		this.contato = new Contato();
		this.contato.setEmail("Hehe@gmail.com");
		this.contato.setNome("Hehe");
	}
	
	@Test
	public void deveSalvarOuAtualizarContato(){ 
		this.contatoBOImpl.saveOrUpdate(contato);
		verify(this.contatoJpaDAO).saveOrUpdate(this.contato);
	}
	
	@Test
	public void deveRetornarNullAoSalvarContato(){
		assertEquals(null, contatoBOImpl.saveOrUpdate(contato));
		verify(this.contatoJpaDAO).saveOrUpdate(contato);
	}
	
	@Test
	public void deveBuscarTodosOsContatos(){
		Contato contato = new Contato();
		contato.setEmail("natanael_aguiar@hotmail.com.br");
		contato.setNome("Natanael Aguiar");
		contato.setId(1L);
		
		List<Contato> listContato = new ArrayList<Contato>();
		listContato.add(this.contato);
		listContato.add(contato);
		
		when(this.contatoJpaDAO.findAll()).thenReturn(listContato);
		
		List<Contato> result = this.contatoBOImpl.findAll();
		
		
		verify(this.contatoJpaDAO).findAll();
		assertEquals(2, result.size());
		assertEquals("Natanael Aguiar",result.get(1).getNome());
	}
	
	@Test
	public void deveBuscarContatoPorId() {
		when(this.contatoJpaDAO.findById(1L)).thenReturn(contato);
		
		assertEquals(contato.getId(), contatoBOImpl.findById(1L).getId());
		
		verify(this.contatoJpaDAO).findById(1L);
	}
	
	@Test
	public void deveRemoverContatoPorId(){
		when(this.contatoJpaDAO.removeById(1L)).thenReturn(true);
		when(this.contatoJpaDAO.removeById(null)).thenReturn(false);
		
		assertEquals(true, this.contatoJpaDAO.removeById(1L));
		
		assertEquals(false, this.contatoJpaDAO.removeById(null));
		
		verify(this.contatoJpaDAO).removeById(1L);
		verify(this.contatoJpaDAO).removeById(null);
	}
}
