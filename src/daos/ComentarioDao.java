package daos;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import models.Comentario;

public class ComentarioDao {
	
	@Inject
	EntityManager manager;
	
	public void adiciona(Comentario comentario) {
		
		manager.persist(comentario);
		
	}
	
	public void edita(Comentario comentario) {
		manager.persist(comentario);
	}
	
	public void remove(Comentario comentario) {
		
		manager.remove(manager.merge(comentario));
		
	}
	
	public Comentario getComentarioPorId(Long idComentario){
		
		return manager.find(Comentario.class, idComentario);
		
	}

}
