package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import constants.Constantes;
import enums.Roles;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;
	
	@NotNull @NotEmpty
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Roles rol;
	
	private boolean ativo;
	
	private String pathFoto;
	
	@NotNull @NotEmpty
	private String nome;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="usuario")
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	public String getStatusUsuario(){
		if(ativo){
			return "Ativo";
		}
		return "Inativo";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String nome) {
		this.email = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Roles getTipo() {
		return rol;
	}
	public void setTipo(Roles tipo) {
		this.rol = tipo;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Roles getRol() {
		return rol;
	}
	public void setRol(Roles rol) {
		this.rol = rol;
	}
	public String getPathFoto() {
		if(pathFoto == null || pathFoto.equalsIgnoreCase("")){
			this.pathFoto = "fotoDefault";
		}
		System.out.println("PATH FOTO: "+this.pathFoto);
		return this.pathFoto;
	}
	public String getPathFotoCompleto() {
		if(this.pathFoto != null){
			return Constantes.wilflyUrl+"img/"+this.pathFoto+".jpeg";
		}
		return null;
	}
	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isMaster(){
		if(this.rol.equals(Roles.Maestro)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isAdmin(){
		if(this.rol.equals(Roles.Admin)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isUsuario(){
		if(this.rol.equals(Roles.Usuario)){
			return true;
		}else{
			return false;
		}
	}

}
