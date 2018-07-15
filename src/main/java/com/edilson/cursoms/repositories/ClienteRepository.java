package com.edilson.cursoms.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edilson.cursoms.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);// isto faz com que o spring crie este metodo automaticamente, basta findBy"e o nome do campo(ou-get(),não sei)"
}
