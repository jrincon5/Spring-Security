package co.com.ceiba.parqueadero.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.entity.ContactEntity;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<ContactEntity, Serializable>{
	public abstract ContactEntity findById(int id);
}
