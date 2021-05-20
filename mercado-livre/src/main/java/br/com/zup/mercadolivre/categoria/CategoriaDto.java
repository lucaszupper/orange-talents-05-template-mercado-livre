package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.validacao.ExistsId;
import br.com.zup.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaDto {
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;
    @NotNull @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private CategoriaDto(){}

    public CategoriaDto(Categoria categoria){
        this.nome = categoria.getNome();
        this.idCategoriaMae = categoria.getIdCategoriaMae();
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


    public Categoria toCategoria() {
        return new Categoria(this.nome, this.idCategoriaMae);
    }
}
