package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import constants.Constantes;

@Entity
public class MedicoResidente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty(message = "Nome Completo deve ser preenchido")
	private String nomeCompleto;
	
	@NotNull @NotEmpty(message = "Lugar de Graduaçao deve ser preenchido")
	private String lugarGraduacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar anoGraduacao = Calendar.getInstance();
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento = Calendar.getInstance();
	
	@Temporal(TemporalType.DATE)
	private Calendar anoInicioResidencia = Calendar.getInstance();
	
	@NotNull @NotEmpty(message = "Supervisores deve ser preenchido")
	private String professoresSupervisores;
	
	@NotNull @NotEmpty(message = "Clinica Familia deve ser preenchido")
	private String clinicaFamilia;
	
	private String pathFoto;
	private boolean ativo;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="medico")
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getLugarGraduacao() {
		return lugarGraduacao;
	}
	public void setLugarGraduacao(String lugarGraduacao) {
		this.lugarGraduacao = lugarGraduacao;
	}
	public Calendar getAnoGraduacao() {
		return anoGraduacao;
	}
	public void setAnoGraduacao(Calendar anoGraduacao) {
		this.anoGraduacao = anoGraduacao;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Calendar getAnoInicioResidencia() {
		return anoInicioResidencia;
	}
	public void setAnoInicioResidencia(Calendar anoInicioResidencia) {
		this.anoInicioResidencia = anoInicioResidencia;
	}
	public String getProfessoresSupervisores() {
		return professoresSupervisores;
	}
	public void setProfessoresSupervisores(String professoresSupervisores) {
		this.professoresSupervisores = professoresSupervisores;
	}
	public String getClinicaFamilia() {
		return clinicaFamilia;
	}
	public void setClinicaFamilia(String clinicaFamilia) {
		this.clinicaFamilia = clinicaFamilia;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public String getPathFotoCompleto() {
		if(this.pathFoto != null){
			return Constantes.wilflyUrl+"img/"+this.pathFoto+".jpeg";
		}
		return Constantes.wilflyUrl+"img/fotoDefault.jpeg";
	}
	public String getPathFoto() {
		if(this.pathFoto != null){
			return this.pathFoto;
		}
		return null;
	}
	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
