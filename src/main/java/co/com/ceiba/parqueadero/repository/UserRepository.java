package co.com.ceiba.parqueadero.repository;

import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.entity.User;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Serializable>{
	
	public abstract User findByUsername(String username);

}
