package br.com.devmedia.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.curso.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
