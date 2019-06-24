package br.com.paginamega.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.paginamega.repository.JogoRepository;
import br.com.paginamega.repository.SorteioRepository;
import br.com.paginamega.util.BaixarJogoZip;
import br.com.paginamega.util.HtmlExtractor;

@Controller
@RequestMapping("/")
public class SorteioController {

	@Autowired
	private SorteioRepository sorteioRepository;
	@Autowired
	private JogoRepository jogoRepository;
	
	@Autowired
	private HtmlExtractor htmlExtractor;
	
	@RequestMapping
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("DashBoard");
		
		mv.addObject("sorteios", sorteioRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
		mv.addObject("jogos", jogoRepository.findAll(Sort.by(Sort.Direction.DESC, "concurso")));
//		mv.addObject("sorteios", sorteioRepository.findByConcursoLessThan(2100));
//		mv.addObject("sorteios", sorteioRepository.findByConcursoGreaterThan(2137));
				
		return mv;
	}
	
	@RequestMapping("/atualizar")
	public ModelAndView atualizarResultados() {
		
		Charset charset = StandardCharsets.ISO_8859_1;
		/*Path do jogo em ordem de sorteio*/
//		String path = "d_mega.htm";
		/*Path do jogo em ordem crescente dos numeros*/
		String path = "d_megasc.htm";
//		String path = "/media/edgar/Backup/WorkspaceSpring/MegaSena/d_mega.htm";
		try {
			htmlExtractor.extractDataFromHtml(path, charset);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/download")
	public ModelAndView downloadResultados() {
		
		BaixarJogoZip baixarJogozip = new BaixarJogoZip();
		baixarJogozip.baixarZip();
		
		return new ModelAndView("redirect:/");
	}
}
