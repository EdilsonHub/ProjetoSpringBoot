package com.edilson.cursoms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edilson.cursoms.domain.Pagamento;
//basta criar o repository da superclasse, não precisa criar das subeclasses
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
