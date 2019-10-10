package s4.spring.td.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td.models.Todo;
import s4.spring.td.repositories.TodoRepository;


@RestController
@RequestMapping("rest/todos")
public class RestTodoController {

	@Autowired
	private TodoRepository todoRepository;
	
	
	@GetMapping("")
	public @ResponseBody List<Todo> read(){
		return todoRepository.findAllOrderByPoids();
	}
	
	@PostMapping("create")
	public @ResponseBody Todo create(@RequestBody Todo todo) {
		todoRepository.save(todo);
		return todo;
	}
	
	@PostMapping("delete")
	public @ResponseBody Todo delete(@RequestBody Todo todo) {
		todoRepository.delete(todo);
		return todo;
	}
	
	@PutMapping("update")
	public @ResponseBody Todo update(@RequestBody Todo todo) {
		todoRepository.save(todo);
		return todo;
	}
}
