package co.com.ceiba.parqueadero.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.converter.ContactConverter;
import co.com.ceiba.parqueadero.entity.ContactEntity;
import co.com.ceiba.parqueadero.model.ContactModel;
import co.com.ceiba.parqueadero.repository.ContactRepository;
import co.com.ceiba.parqueadero.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		ContactEntity contact = contactRepository.save(contactConverter.model2entity(contactModel));
		return contactConverter.entity2model(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<ContactEntity> contacts = contactRepository.findAll();
		List<ContactModel> contactModel = new ArrayList<ContactModel>();
		for(ContactEntity contact : contacts) {
			contactModel.add(contactConverter.entity2model(contact));	
		}
		return contactModel;
	}

	@Override
	public ContactEntity findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.entity2model(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		ContactEntity contactEntity = findContactById(id);
		if(null != contactEntity) {
			contactRepository.delete(contactEntity);
		}
	}
}
