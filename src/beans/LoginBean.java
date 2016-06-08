package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import custom.Transactional;
import daos.UsuarioDao;
import models.Usuario;

@Named @RequestScoped 
public class LoginBean {
	
	private String email;
	private String senha;
	
	@Inject
	UsuarioLogadoBean usuarioLogadoBean;
	
	@Inject
	UsuarioDao usuariodao;
	
	public String efetuaLogin(){
		Usuario usuario = usuariodao.buscaUsuarioPorEMailESenha(email, senha);
		if(usuario != null){
			usuarioLogadoBean.setUsuarioLogado(usuario);
			return "listagem?faces-redirect=true";
		}else{
			return "login?faces-redirect=true";
		}
	}
	
	@Transactional
	public void criaAdmin(){
		usuariodao.criaAdmin();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
