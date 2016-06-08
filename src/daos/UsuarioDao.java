package daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import enums.Roles;
import models.MedicoResidente;
import models.Usuario;

public class UsuarioDao {
	
	@Inject
	EntityManager manager;
	
	public void altera(Usuario usuario){
		
		manager.merge(usuario);
		
	}
	
	public void adiciona(Usuario usuario) {
		
		manager.persist(usuario);
		
	}
	
	public List<Usuario> listaTodos() {
		
		Query query = manager.createQuery("select u from Usuario u where u.ativo = true");

		List<Usuario> lista = query.getResultList();
		
		return lista;
	}
	
	public List<Usuario> listaTodosInativos() {
		
		Query query = manager.createQuery("select u from Usuario u where u.ativo = false");

		List<Usuario> lista = query.getResultList();
		
		return lista;
	}
		
	public Usuario buscaUsuarioPorEMailESenha(String email, String senha){
		Query query = manager.createQuery("select u from Usuario u where u.email like :pEmail and u.senha like :pSenha")
				.setParameter("pEmail", email).setParameter("pSenha", senha);
		if(query.getResultList().isEmpty()){
			return null;
		}
		
		return (Usuario) query.getSingleResult();
	}
	
	public void criaAdmin(){
		Usuario usuario = new Usuario();
		usuario.setNome("Ruben Rodriguez");
		usuario.setAtivo(true);
		usuario.setEmail("ruben@gmail.com");
		usuario.setPathFoto("");
		usuario.setRol(Roles.Maestro);
		usuario.setSenha("123456");
		
				
		manager.persist(usuario);
		
	}

	public Usuario buscaPorId(Long idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}

	public void remove(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public List<Usuario> listaLikeNome(String filtro) {
		Query query = manager.createQuery("select u from Usuario u where lower (u.nome) like :pNome and u.ativo = true")
				.setParameter("pNome", "%"+filtro.toLowerCase()+"%");

		List<Usuario> lista = query.getResultList();
		
		return lista;
	}

	public List<Usuario> listaInativosLikeNome(String filtro) {
		Query query = manager.createQuery("select u from Usuario u where lower (u.nome) like :pNome and u.ativo = false")
				.setParameter("pNome", "%"+filtro.toLowerCase()+"%");

		List<Usuario> lista = query.getResultList();
		
		return lista;
	}

}
