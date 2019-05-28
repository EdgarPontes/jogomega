package br.com.paginamega.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.paginamega.repository.SorteioRepository;

@Controller
public class SorteioController {

	@Autowired
	private SorteioRepository sorteioRepository;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("DashBoard");
		
//		mv.addObject("sorteios", sorteioRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
//		mv.addObject("sorteios", sorteioRepository.findByConcursoLessThan(2100));
		mv.addObject("sorteios", sorteioRepository.findByConcursoGreaterThan(2140));
				
		return mv;
	}
}
