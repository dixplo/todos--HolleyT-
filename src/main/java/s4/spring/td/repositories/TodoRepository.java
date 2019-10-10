package s4.spring.td.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td.models.Todo;


public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findAllOrderByPoids();
}
