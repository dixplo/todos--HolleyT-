package s4.spring.td.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

import s4.spring.td.models.Todo;
import s4.spring.td.repositories.TodoRepository;




@Controller
@RequestMapping("/todo/")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@GetMapping("/index")
	public String todo(ModelMap model) {
		List<Todo> todos=todoRepo.findAll();
		model.put("todos",todos);
		return "todo"; 
	}
	
	
	@GetMapping("tache/addTodo")
	public String addTodo(ModelMap model) {
		Todo y=new Todo();
		y.setLabel("");
		y.setDescription("aezf");
		return "addTodo";
	}
	
	
	@PostMapping("task/add")
	public RedirectView addTodo(HttpServletRequest request) {
		
		String labeltodo = request.getParameter("labeltodo");
		String Descriptiontodo = request.getParameter("Descriptiontodo");
		String Avancementtodo = request.getParameter("Avancementtodo");
		String Poidstodo = request.getParameter("Poidstodo");
		
		s4.spring.td.models.Todo t=new s4.spring.td.models.Todo();
		
		t.setLabel(labeltodo);
		t.setDescription(Descriptiontodo);
		todoRepo.save(t);		
		return new RedirectView("/todo/index"); 
	}
	
		
	@GetMapping("delete/{id}")
	public @ResponseBody RedirectView todo(@PathVariable String id){
		todoRepo.deleteById(Integer.parseInt(id));
		return new RedirectView("/todo/index");
	}
	
	
	@RequestMapping("")
	public String indextds(ModelMap model) {
		List<Todo> todos= todoRepo.findAll();
		
		VueJS.addMethod("addTodo, let self=this" +Http.post("/rest/todos/create", "this.todo", "self.dialog=false;"
				+ "self.message='tache ajoutée';"
				+ "self.snackbar=true;"
				+"self.items.push(response.data);self.todo={};"));
		
		return "";
	}
	
	
	

	
}
