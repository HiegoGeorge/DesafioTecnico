package com.example.testetecnico.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "fazenda")
public class Fazenda implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "nome_fazenda", nullable = false)
	private String nomeFazenda;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeFazenda() {
		return nomeFazenda;
	}

	public void setNomeFazenda(String nomeFazenda) {
		this.nomeFazenda = nomeFazenda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Fazenda)) {
			return false;
		}	
		return Objects.equals(id, ((Fazenda) obj).id);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Fazenda{");
		sb.append("id=' ").append(id).append('\'');
		sb.append(", nomeFazenda=' ").append(nomeFazenda).append('\'');
		sb.append("}");
		return sb.toString();
	}
	

}
