package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import models.Usuario;
import java.io.Serializable;

@Named @SessionScoped
public class UsuarioLogadoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Usuario usuarioLogado;
	
	public String deslogar(){
		usuarioLogado = null;
		return "login?faces-redirect=true";
	}
	
	public String meuPerfil(){
		return "meuPerfil?faces-redirect=true";
	}
	
	public String getPathFoto(){
		if(usuarioLogado.getPathFotoCompleto() == null || usuarioLogado.getPathFotoCompleto().equalsIgnoreCase("")){
			usuarioLogado.setPathFoto("fotoDefault");
		}
		System.out.println("PATH FOTO: "+usuarioLogado.getPathFotoCompleto());
		return usuarioLogado.getPathFotoCompleto();
	}
	
	public boolean isLogado(){
		return usuarioLogado != null;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
