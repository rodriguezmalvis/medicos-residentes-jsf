package daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import models.MedicoResidente;


public class MedicoResidenteDao {
	
	@Inject
	EntityManager manager;
	
	public MedicoResidente buscaPorId(long id){
		
		return manager.find(MedicoResidente.class, id);
		
	}
	
	public void altera(MedicoResidente medico){
		
		manager.merge(medico);
		
	}
	
	public void adiciona(MedicoResidente medico) {
		
		manager.persist(medico);
		
	}
	
	public void remove(MedicoResidente medico){
		manager.remove(manager.merge(medico));
	}
	
	public List<MedicoResidente> listaTodos() {
		
		Query query = manager.createQuery("select m from MedicoResidente m where m.ativo = true");

		List<MedicoResidente> lista = query.getResultList();
		
		return lista; 
	}
	
	public List<MedicoResidente> listaLikeNome(String nome) {
		
		Query query = manager.createQuery("select m from MedicoResidente m where lower (m.nomeCompleto) like :pNome and m.ativo = true")
						.setParameter("pNome", "%"+nome.toLowerCase()+"%");

		List<MedicoResidente> lista = query.getResultList();
		
		return lista;
	}

	public List<MedicoResidente> listaTodosInativos() {
		Query query = manager.createQuery("select m from MedicoResidente m where m.ativo = false");

		List<MedicoResidente> lista = query.getResultList();
		
		return lista; 
	}

	public List<MedicoResidente> listaInativosLikeNome(String nome) {
		Query query = manager.createQuery("select m from MedicoResidente m where lower (m.nomeCompleto) like :pNome and m.ativo = false")
				.setParameter("pNome", "%"+nome.toLowerCase()+"%");

List<MedicoResidente> lista = query.getResultList();

return lista;
	}

}
