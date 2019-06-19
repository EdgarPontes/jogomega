package br.com.paginamega.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.paginamega.repository.JogoRepository;

@Controller
public class JogoController {

	private JogoRepository repo;
	
	@GetMapping("jogo")
	public ModelAndView jogo(){
		ModelAndView mv = new ModelAndView("Jogo");
		
		mv.addObject("jogos", repo.findAll() );
		
		return mv;
	}
}
