package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import custom.Transactional;
import models.Comentario;
import daos.ComentarioDao;

@Named @RequestScoped
public class ComentarioBean {
	
	@Inject
	private ComentarioDao comentariodao;
	
	private Comentario comentario = new Comentario();
	private Long idComentario;
	
	@Transactional
	public void buscaComentario(){
		if(idComentario != null && idComentario != 0){
			this.comentario = comentariodao.getComentarioPorId(idComentario);
		}
	}
	
	@Transactional
	public String editaComentario(){
		Comentario comentarioAEditar = comentariodao.getComentarioPorId(comentario.getId());
		comentarioAEditar.setConteudo(comentario.getConteudo());
		comentariodao.edita(comentarioAEditar);
		return "ficha?faces-redirect=true&id="+comentarioAEditar.getMedico().getId();
	}
	
	public ComentarioDao getComentariodao() {
		return comentariodao;
	}
	public void setComentariodao(ComentarioDao comentariodao) {
		this.comentariodao = comentariodao;
	}
	public Comentario getComentario() {
		return comentario;
	}
	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	public Long getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}
	

}
