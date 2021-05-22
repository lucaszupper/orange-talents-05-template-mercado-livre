package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.categoria.Categoria;
import io.jsonwebtoken.lang.Assert;
@Entity
public class Produto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String nome;
	private @Positive BigDecimal valor;
	private @Positive BigDecimal qtd;
	private @Length(max = 1000, min = 1) String descricao;
	@NotNull @Valid
	@ManyToOne @JoinColumn(name = "categoria_id")
	private Categoria categoria;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicasProduto> caracteristicas = new HashSet<>();

	public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive BigDecimal qtd,
			@Length(max = 1000, min = 1) String descricao, @NotNull @Valid Categoria categoria, Collection<CaracteristicasDTO> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.qtd = qtd;
		this.descricao = descricao;
		this.categoria = categoria;
		Set<CaracteristicasProduto> caract = caracteristicas.stream()
				.map(c -> c.toCaracteristicasProduto(this))
				.collect(Collectors.toSet());
		this.caracteristicas.addAll(caract);
		
		Assert.isTrue(this.caracteristicas.size() >=3,"Produto precisa de 3 caracteristicas ou mais");
	}

	private Produto() {}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicasProduto> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	


}
