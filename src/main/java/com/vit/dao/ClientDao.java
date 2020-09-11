package com.vit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vit.model.Client;
import com.vit.repository.ClientRepository;

@Service
public class ClientDao {

	@Autowired
	ClientRepository clientRepository;
	
	// save a client
	public Client save(Client clnt) {
		return clientRepository.save(clnt);
	}
	
	// search all clients
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	// get a client by  id
	public Client findOne(Integer id) {
		return clientRepository.findById(id).orElse(null);
	}
	
	public void delete(Client clnt) {
		clientRepository.delete(clnt);
	}
	
	public void deleteAll() {
		clientRepository.deleteAll();
	}
	
}
