package br.com.zup.mercadolivre.categoria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    
    public ResponseEntity<CategoriaDto> cadastra(@RequestBody @Valid CategoriaDto categoriaDto, UriComponentsBuilder builder){
        System.out.println(categoriaDto.toString());
        Categoria categoria = categoriaRepository.save(categoriaDto.toCategoria(manager));
        URI uri = builder.path("/api/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }
}
