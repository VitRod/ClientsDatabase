package com.vit.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vit.model.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Integer> {

}
