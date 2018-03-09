package co.com.ceiba.parqueadero.converter;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.entity.ContactEntity;
import co.com.ceiba.parqueadero.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public ContactEntity model2entity(ContactModel contactModel) {
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setFirstName(contactModel.getFirstName());
		contactEntity.setLastName(contactModel.getLastName());
		contactEntity.setId(contactModel.getId());
		contactEntity.setTelephone(contactModel.getTelephone());
		contactEntity.setCity(contactModel.getCity());
		return contactEntity;
	}
	
	public ContactModel entity2model(ContactEntity contactEntity) {
		ContactModel contactModel = new ContactModel();
		contactModel.setFirstName(contactEntity.getFirstName());
		contactModel.setLastName(contactEntity.getLastName());
		contactModel.setId(contactEntity.getId());
		contactModel.setTelephone(contactEntity.getTelephone());
		contactModel.setCity(contactEntity.getCity());
		return contactModel;
	}
}
