package br.com.cod3r.exercicios_sb.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cod3r.exercicios_sb.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
