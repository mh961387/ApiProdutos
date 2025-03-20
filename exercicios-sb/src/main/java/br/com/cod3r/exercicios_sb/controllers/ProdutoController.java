package br.com.cod3r.exercicios_sb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exercicios_sb.model.entities.Produto;
import br.com.cod3r.exercicios_sb.model.repositories.ProdutoPaging;
import br.com.cod3r.exercicios_sb.model.repositories.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoPaging produtopaging;

//	@PostMapping
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Produto salvarProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Produto> ObterProdutosPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina){
		if (qtdePagina >= 5) qtdePagina = 5; 
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtopaging.findAll(page);
	}
	
	@GetMapping(path = "/nome/{parteNome}")
	public Iterable<Produto> obterProdutoPorNome(@PathVariable String parteNome){
//		return produtopaging.findByNomeContainingIgnoreCase(parteNome);
		return produtopaging.searchByNameLike(parteNome);
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	
//	@PutMapping
//	public Produto alterarProduto(@Valid Produto produto) {
//		produtoRepository.save(produto);
//		return produto;
//	}
	
	@DeleteMapping("/{id}")
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
}
