package br.com.paginamega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.paginamega.domain.Jogo;
import br.com.paginamega.repository.JogoRepository;

@Controller
@RequestMapping("/jogos")
public class JogoController {

	@Autowired
	private JogoRepository repo;
	
	@RequestMapping
	public ModelAndView jogo() {
		ModelAndView mv = new ModelAndView("JogosFeitos");
		return mv.addObject("jogos", repo.findAll(Sort.by(Sort.Direction.DESC, "concurso")));
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("Jogo");
			mv.addObject(new Jogo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Jogo jogo, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return "Jogo";
		}

		try {
			repo.save(jogo);
			attributes.addFlashAttribute("mensagem", "Jogo salvo com sucesso! ");
			
			return "redirect:/jogos/novo";
			
		}catch (DataIntegrityViolationException e) {
			errors.rejectValue("dataDoSorteio", null, "Formato de data inválido");
			return "Jogo";
		}
	}
}
