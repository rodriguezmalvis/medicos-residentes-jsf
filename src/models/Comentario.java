package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String autor;
	@Lob
	@Column(length = 65535)
	private String conteudo;
	private Calendar dataComentario;
	
	@ManyToOne
	private MedicoResidente medico;
	
	@ManyToOne
	private Usuario usuario;
	
	public String getDataFormatada(){
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dataComentario.getTime());
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public MedicoResidente getMedico() {
		return medico;
	}
	public void setMedico(MedicoResidente medico) {
		this.medico = medico;
	}
	public Calendar getDataComentario() {
		return dataComentario;
	}
	public void setDataComentario(Calendar dataComentario) {
		this.dataComentario = dataComentario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
