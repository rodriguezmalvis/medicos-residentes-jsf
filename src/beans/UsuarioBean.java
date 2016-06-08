package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import custom.Transactional;
import daos.UsuarioDao;
import enums.Roles;
import models.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	
	@Inject
	UsuarioDao usuariodao;
	
	Usuario usuario = new Usuario();
	private Long idUsuario;
	
	private List<Usuario> usuarios;

	private String filtro;
	
	public UsuarioDao getUsuariodao() {
		return usuariodao;
	}

	public void setUsuariodao(UsuarioDao usuariodao) {
		this.usuariodao = usuariodao;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	@Transactional
	public String gravar(){
		if(this.usuario.getId() == null){
			usuario.setAtivo(false);
			usuariodao.adiciona(usuario);
		}else{
			usuariodao.altera(usuario);
		}
		return "perfil?faces-redirect=true&id="+usuario.getId();
		
	}
	
	/*public String remover(){
		usuariodao.remove(usuario);
		
		return "listaUsuarios?faces-redirect=true&id="+usuario.getId();
	}*/
	
	@Transactional
	public String remover(){
		usuario = usuariodao.buscaPorId(idUsuario);
		
		usuario.setAtivo(false);
		
		usuariodao.altera(usuario);
		
		return "listaUsuarios?faces-redirect=true";
	}
	
	@Transactional
	public String ativar(){
		usuario = usuariodao.buscaPorId(idUsuario);
		
		usuario.setAtivo(true);
		
		usuariodao.altera(usuario);
		
		return "listaUsuarios?faces-redirect=true";
	}
	
	public Roles[] getRoles(){		
		return Roles.values();
	}
	
	public String getPathFoto(){
		if(usuario.getPathFotoCompleto() == null || usuario.getPathFotoCompleto().equalsIgnoreCase("")){
			usuario.setPathFoto("fotoDefault");
		}
		System.out.println("PATH FOTO: "+usuario.getPathFotoCompleto());
		return usuario.getPathFotoCompleto();
	}
	
	@Transactional
	public void buscaUsuario(){
		if(idUsuario != null && idUsuario != 0){
			this.usuario = usuariodao.buscaPorId(idUsuario);
		}
	}

	@Transactional
	public List<Usuario> getUsuarios() {
		
		if(usuarios == null){
			usuarios = usuariodao.listaTodos();
		}
		
		return usuarios;
	}
	
	@Transactional
	public List<Usuario> getUsuariosInativos() {
		
		if(usuarios == null){
			usuarios = usuariodao.listaTodosInativos();
		}
		
		return usuarios;
	}
	
	public List<Usuario> getUsuariosLikeNome(AjaxBehaviorEvent event) {
		
		System.out.println(filtro);
		this.usuarios = usuariodao.listaLikeNome(filtro);
		
		return this.usuarios;
	}
	
	public List<Usuario> getUsuariosInativosLikeNome(AjaxBehaviorEvent event) {
		
		System.out.println(filtro);
		this.usuarios = usuariodao.listaInativosLikeNome(filtro);
		
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
