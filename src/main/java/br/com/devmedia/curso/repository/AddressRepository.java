package br.com.devmedia.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.curso.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
