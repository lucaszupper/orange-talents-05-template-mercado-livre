package br.com.zup.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.validacao.ExistsId;
import br.com.zup.mercadolivre.validacao.UniqueValue;

public class CategoriaDto {
	    

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;
    @NotNull @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private CategoriaDto(){}

    public CategoriaDto(Categoria categoria){

        this.nome = categoria.getNome();
        this.idCategoriaMae = categoria.getCategoriaMae().getId();
    }

    public CategoriaDto(Long idCategoriaMae, String nome) {
        this.idCategoriaMae = idCategoriaMae;
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }


    public Categoria toCategoria(EntityManager manager) {
        Categoria mae = manager.find(Categoria.class, this.idCategoriaMae);
        return new Categoria(this.nome, mae);
    }

    @Override
    public String toString() {
        return "CategoriaDto{" +
                "idCategoriaMae=" + idCategoriaMae +
                ", nome='" + nome + '\'' +
                '}';
    }
}
