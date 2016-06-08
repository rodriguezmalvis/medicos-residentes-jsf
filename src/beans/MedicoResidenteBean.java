package beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import models.Comentario;
import models.MedicoResidente;
import custom.Transactional;
import daos.ComentarioDao;
import daos.MedicoResidenteDao;

@Named @RequestScoped
public class MedicoResidenteBean {
	
	@Inject
	private MedicoResidenteDao medicodao;
	@Inject
	private ComentarioDao comentariodao;
	@Inject
	UsuarioLogadoBean usuarioLogadoBean;
	private MedicoResidente medico = new MedicoResidente();
	private List<MedicoResidente> medicos;
	private Comentario comentario = new Comentario();

	private String filtro;
	private Long idMedico;
	
	@Transactional
	public String gravaComentario(){
		comentario.setMedico(medicodao.buscaPorId(idMedico));
		comentario.setUsuario(usuarioLogadoBean.getUsuarioLogado());
		comentario.setAutor(usuarioLogadoBean.getUsuarioLogado().getNome());
		comentario.setDataComentario(Calendar.getInstance());
		comentariodao.adiciona(comentario);
		this.comentario = new Comentario();
		return "ficha?faces-redirect=true&id="+idMedico;				
	}
	
	public int getIdade(){
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int anoNascimento = medico.getDataNascimento().get(Calendar.YEAR);
		return anoAtual-anoNascimento;
	}
	
	public String getPathFoto(){
		if(medico.getPathFotoCompleto() == null || medico.getPathFotoCompleto().equalsIgnoreCase("")){
			medico.setPathFoto("fotoDefault");
		}
		System.out.println("PATH FOTO: "+medico.getPathFotoCompleto());
		return medico.getPathFotoCompleto();
	}
	
	/*@Transactional
	public String remover(){
		medico = medicodao.buscaPorId(idMedico);
		for(Comentario comentario : medico.getComentarios()){
			comentariodao.remove(comentario);
		}
		medicodao.remove(medico);
		return "listagem?faces-redirect=true";
	}*/
	
	@Transactional
	public String remover(){
		medico = medicodao.buscaPorId(idMedico);
		
		this.medico.setAtivo(false);
		
		System.out.println("Inativando medico");
		medicodao.altera(medico);
		
		return "listagem?faces-redirect=true";
	}
	
	@Transactional
	public String ativar(){
		medico = medicodao.buscaPorId(idMedico);
		
		this.medico.setAtivo(true);
		
		System.out.println("Ativando medico");
		medicodao.altera(medico);
		
		return "listagem?faces-redirect=true";
	}
	
	@Transactional
	public void buscaMedico(){
		if(idMedico != null && idMedico != 0){
			this.medico = medicodao.buscaPorId(idMedico);
		}
	}
	
	@Transactional
	public String gravar(){
		if(this.medico.getId() == null){
			System.out.println("Novo Medico");
			this.medico.setAtivo(true);
			medicodao.adiciona(medico);			
		}else{
			System.out.println("Altera Medico");
			System.out.println(medico.getDataNascimento().getTime());
			medicodao.altera(medico);
		}
		return "ficha?faces-redirect=true&id="+medico.getId();
	}
	
	public List<MedicoResidente> getMedicos() {
		
		if(this.medicos == null){
			this.medicos = medicodao.listaTodos();
		}
		
		return this.medicos;
	}
	
	public List<MedicoResidente> getMedicosInativos() {
		
		if(this.medicos == null){
			this.medicos = medicodao.listaTodosInativos();
		}
		
		return this.medicos;
	}
	
	public List<MedicoResidente> getMedicosLikeNome(AjaxBehaviorEvent event) {
		
		System.out.println(filtro);
		this.medicos = medicodao.listaLikeNome(filtro);
		
		return this.medicos;
	}
	
	public List<MedicoResidente> getMedicosInativosLikeNome(AjaxBehaviorEvent event) {
		
		System.out.println(filtro);
		this.medicos = medicodao.listaInativosLikeNome(filtro);
		
		return this.medicos;
	}
	
	public String irEditar(){
		return "cadastro?faces-redirect=true";
	}
	
	public void setMedicos(List<MedicoResidente> medicos) {
		this.medicos = medicos;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public MedicoResidente getMedico() {
		return medico;
	}
	
	public void setMedico(MedicoResidente medico) {
		this.medico = medico;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
