package com.myprojects.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myprojects.springboot.web.model.Todo;
import com.myprojects.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Date = dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String todoPage(ModelMap model) {

		String name = (String) model.get("name");
		model.put("todos", todoService.retrieveTodos(name));
		return "list-todos";

	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		model.put("head", "Add Todo");
		model.addAttribute("todo", new Todo(0, (String) model.get("name"), "", new Date(), false));
		return "todo";

	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todoService.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";

	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {

		todoService.deleteTodo(id);
		return "redirect:/list-todos";

	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodoPage(ModelMap model, @RequestParam int id) {

		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		model.put("head", "Update Todo");
		return "todo";

	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser((String) model.get("name"));
		todoService.updatingTodo(todo);
		return "redirect:/list-todos";
	}

}
