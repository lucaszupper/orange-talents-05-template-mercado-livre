package br.com.zup.mercadolivre.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastra(@RequestBody @Valid CategoriaDto categoriaDto, UriComponentsBuilder builder){

        Categoria categoria = categoriaRepository.save(categoriaDto.toCategoria());
        URI uri = builder.path("/api/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }
}
