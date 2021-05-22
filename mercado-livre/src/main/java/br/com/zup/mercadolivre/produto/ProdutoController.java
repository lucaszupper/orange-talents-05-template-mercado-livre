package br.com.zup.mercadolivre.produto;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.validacao.CaracteristicaDuplicadaValidator;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
		
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@InitBinder
	public void init(WebDataBinder bind) {
		bind.addValidators(new CaracteristicaDuplicadaValidator());
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastra(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder builder) {
		
		@SuppressWarnings("unused")
		Produto produto = produtoRepository.save(produtoDto.toProduto(categoriaRepository));
		URI uri = builder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

}
