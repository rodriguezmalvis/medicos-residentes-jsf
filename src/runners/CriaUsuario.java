package runners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Usuario;
import enums.Roles;

public class CriaUsuario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setEmail("ruben@gmail.com");
		usuario.setPathFoto("Path Foto");
		usuario.setRol(Roles.Maestro);
		usuario.setSenha("123456");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("medicosResidentesLocal");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction();
		
		em.persist(usuario);
				
		em.getTransaction().commit();
		emf.close();
	}

}
