package br.com.cod3r.exercicios_sb.model.repositories;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.cod3r.exercicios_sb.model.entities.Produto;

public interface ProdutoPaging extends ListPagingAndSortingRepository<Produto, Integer>{
	
	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
	
	//findByNomeContaining
	//findByNomeIsContaining
    //findByNomeContains
	
	//findByNomeStartsWith
	//findByNomeEndWith
	
    //findByNomeNotContaining
	
	@NativeQuery("select * from produto where nome LIKE %:nome%")
	public Iterable<Produto> searchByNameLike(@Param("nome") String nome);
}
