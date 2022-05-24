package com.example.testetecnico.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "animais_fazenda")
public class AnimaisFazenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@NotNull
	@Size(max = 15)
	@Column(name = "tag_identificacao", nullable = false)
	private String tagIdentificacao;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagIdentificacao() {
		return tagIdentificacao;
	}

	public void setTagIdentificacao(String tagIdentificacao) {
		this.tagIdentificacao = tagIdentificacao;
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
		if (!(obj instanceof AnimaisFazenda)) {
			return false;
		}	
		return Objects.equals(id, ((AnimaisFazenda) obj).id);
	}
	

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AnimaisFazenda{");
		sb.append("id=' ").append(id).append('\'');
		sb.append(", tagIdentificacao=' ").append(tagIdentificacao).append('\'');
		sb.append("}");
		return sb.toString();
	}

}
