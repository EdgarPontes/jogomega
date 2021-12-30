package br.com.paginamega.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import br.com.paginamega.domain.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.paginamega.repository.SorteioRepository;
import br.com.paginamega.util.BaixarJogoZip;
import br.com.paginamega.util.BaixarPageJogo;
import br.com.paginamega.util.HtmlExtractor;

@Controller
@RequestMapping("/")
public class SorteioController {

	@Autowired
	private SorteioRepository sorteioRepository;

	@Autowired
	private HtmlExtractor htmlExtractor;

	@RequestMapping
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("DashBoard");

		mv.addObject("sorteios", sorteioRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
		// mv.addObject("sorteios", sorteioRepository.findByConcursoLessThan(2100));
		// mv.addObject("sorteios", sorteioRepository.findByConcursoGreaterThan(2137));

		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("Index");
		mv.addObject(new Jogo());
		return mv;
	}

	@RequestMapping("/atualizar")
	public ModelAndView atualizarResultados() {
		System.out.println("Atualizando!!!");

		Charset charset = StandardCharsets.UTF_8;
		
		String path = "d_megasc.htm";
		
		try {
			htmlExtractor.extractDataFromHtml(path, charset);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/download")
	public ModelAndView downloadResultados() {

		BaixarPageJogo baixarPageJogo = new BaixarPageJogo();
		baixarPageJogo.BaixarPage();

		return new ModelAndView("redirect:/");
	}
}
