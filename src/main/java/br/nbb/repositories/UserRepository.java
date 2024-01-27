package br.nbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.nbb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User WHERE u.userName =: userName") // lidando com objeto e não com tabela ***
	User findByUserName(@Param("userName") String userName);
	
}

