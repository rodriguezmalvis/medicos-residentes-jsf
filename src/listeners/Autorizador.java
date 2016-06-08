package listeners;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import beans.UsuarioLogadoBean;
import enums.Roles;

public class Autorizador implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioLogadoBean usuarioLogado;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		
		if(("/login.xhtml".equalsIgnoreCase(context.getViewRoot().getViewId()) 
		|| ("/negado.xhtml".equalsIgnoreCase(context.getViewRoot().getViewId()))) ){
			return;
		}
		
		if(!usuarioLogado.isLogado()){
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			
			context.renderResponse();
		}else if(!temPermissao(usuarioLogado,context) || !usuarioLogado.getUsuarioLogado().isAtivo()){
			handler.handleNavigation(context, null, "negado?faces-redirect=true");
			
			context.renderResponse();
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private boolean temPermissao(UsuarioLogadoBean usuarioLogado,FacesContext context) {
		boolean permissao = false;
		
		String rol = usuarioLogado.getUsuarioLogado().getRol().toString();
		
		if(rol.equals(Roles.Maestro.toString())){
			permissao = true;
		}else if (rol.equals(Roles.Admin.toString())){
			if(temAcessoAdmin(context.getViewRoot().getViewId())){
				permissao = true;
			}
		}else if (rol.equals(Roles.Usuario.toString())){
			if(temAcessoUsuario(context.getViewRoot().getViewId())){
				permissao = true;
			}
		}
		
		return permissao;
	}

	private boolean temAcessoAdmin(String viewId) {
		boolean temAcesso = false;
		
		List<String> caminhos = new ArrayList<String>();
		
		caminhos.add("/cadastro.xhtml");
		caminhos.add("/listagem.xhtml");
		caminhos.add("/listagemInativos.xhtml");
		caminhos.add("/ficha.xhtml");
		caminhos.add("/comentario.xhtml");
		caminhos.add("/meuPerfil.xhtml");
		
		for (String caminho : caminhos) {
			if(caminho.equals(viewId)){
				temAcesso = true;
			}
		}
		
		return temAcesso;
	}
	
	private boolean temAcessoUsuario(String viewId) {
		boolean temAcesso = false;
		
		List<String> caminhos = new ArrayList<String>();
		
		caminhos.add("/listagem.xhtml");
		caminhos.add("/ficha.xhtml");
		caminhos.add("/comentario.xhtml");
		caminhos.add("/meuPerfil.xhtml");
		
		for (String caminho : caminhos) {
			if(caminho.equals(viewId)){
				temAcesso = true;
			}
		}
		
		return temAcesso;
	}

}
