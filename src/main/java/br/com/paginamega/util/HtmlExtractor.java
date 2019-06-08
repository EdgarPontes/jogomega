package br.com.paginamega.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paginamega.domain.Sorteio;
import br.com.paginamega.repository.SorteioRepository;

@Service
public class HtmlExtractor {

	private int contador = 0;
	private int par = 0;
	private int impar = 0;

	private final int indiceDataSorteio = 0;
	private final int indiceDezena1 = 1;
	private final int indiceDezena2 = 2;
	private final int indiceDezena3 = 3;
	private final int indiceDezena4 = 4;
	private final int indiceDezena5 = 5;
	private final int indiceDezena6 = 6;
	private final int indiceArrecadacaoTotal = 7;
	private final int indiceGanhadoresSena = 8;
	private final int indiceCidade = 9;
	private final int indiceUF = 10;
	private final int indiceRateioSena = 11;
	private final int indiceGanhadoresQuina = 12;
	private final int indiceRateioQuina = 13;
	private final int indiceGanhadoresQuadra = 14;
	private final int indiceRateioQuadra = 15;
	private final int indiceAcumulado = 16;
	private final int indiceValorAcumulado = 17;
	private final int indiceEstimativaPremio = 18;
	private final int indiceAcumuladoMegaDaVirada = 19;

	@PersistenceContext
	EntityManager em;

	@Autowired
	private SorteioRepository sorteioRepository;

	public void extractDataFromHtml(String path1, Charset charset) throws ParseException {

		BufferedReader reader;
		String line = null;
		Integer ultimoId ;
		try {
			
			Object getId = em.createQuery("SELECT MAX(id) FROM " + Sorteio.class.getName()).getSingleResult();

			ultimoId = getId == null ? 0 : Integer.parseInt(getId.toString());
			
			reader = Files.newBufferedReader(Paths.get(path1), charset);

			while ((line = reader.readLine()) != null) {
				String plainEntity = "";
				if (line.matches("<tr.*")) {
					line = readLine(reader);
					if ((line.matches("<td rowspan=\"\\d+\">.*"))) {
						plainEntity = readOneEntireEntity(reader);
						contador++;
						if (contador > ultimoId ) {
							System.out.println("linha numero: " + contador);
							Sorteio sorteio = assemblyEntity(plainEntity);
							persistData(sorteio);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro com o reader");
			e.printStackTrace();
		} finally {
			try {
				if (em != null) {
					em.close();
				}
			} catch (Exception e2) {
				System.out.println("erro fechando EntityManager");
			}
		}
	}

	@Transactional
	public void persistData(Sorteio sorteio) {

		sorteioRepository.save(sorteio);

	}

	public Sorteio assemblyEntity(String plainEnity) throws ParseException {
		String[] entity = plainEnity.split(";");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Sorteio sorteio = new Sorteio();
		sorteio.setConcurso(contador);
		sorteio.setDataDoSorteio(sdf.parse(entity[indiceDataSorteio]));
		sorteio.setDezena1(Integer.parseInt(entity[indiceDezena1]));
		sorteio.setDezena2(Integer.parseInt(entity[indiceDezena2]));
		sorteio.setDezena3(Integer.parseInt(entity[indiceDezena3]));
		sorteio.setDezena4(Integer.parseInt(entity[indiceDezena4]));
		sorteio.setDezena5(Integer.parseInt(entity[indiceDezena5]));
		sorteio.setDezena6(Integer.parseInt(entity[indiceDezena6]));
		sorteio.setArrecadacaoTotal(Double.parseDouble(entity[indiceArrecadacaoTotal]));
		sorteio.setGanhadoresSena(Integer.parseInt(entity[indiceGanhadoresSena]));
		if (!(entity[indiceCidade].equals("vazio"))) {
			sorteio.setCidade(entity[indiceCidade]);
		}
		if (!(entity[indiceUF].equals("vazio"))) {
			sorteio.setUF(entity[indiceUF]);
		}
		sorteio.setRateioSena(Double.parseDouble(entity[indiceRateioSena]));
		sorteio.setGanhadoresQuina(Integer.parseInt(entity[indiceGanhadoresQuina]));
		sorteio.setRateioQuina(Double.parseDouble(entity[indiceRateioQuina]));
		sorteio.setGanhadoresQuadra(Integer.parseInt(entity[indiceGanhadoresQuadra]));
		sorteio.setRateioQuadra(Double.parseDouble(entity[indiceRateioQuadra]));
		sorteio.setAcumulado(entity[indiceAcumulado]);
		sorteio.setValorAcumulado(Double.parseDouble(entity[indiceValorAcumulado]));
		sorteio.setEstimativaPremio(Double.parseDouble(entity[indiceEstimativaPremio]));
		sorteio.setAcumuladoMegaDaVirada(Double.parseDouble(entity[indiceAcumuladoMegaDaVirada]));
		parOuImpar(sorteio);
		sorteio.setNumerosPar(par);
		sorteio.setNumerosImpar(impar);
		par = 0;
		impar = 0;
		return sorteio;
	}

	public String readOneEntireEntity(BufferedReader reader) throws IOException {
		String plainEntityToReturn = "";
		String line = "";
		while ((!line.matches(".*</tr>.*"))) {
			plainEntityToReturn = plainEntityToReturn + "" + line;
			line = readLine(reader);
		}
		plainEntityToReturn = plainEntityToReturn.replaceAll("<td rowspan=\"\\d+\">", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("</td><td>", ";");
		plainEntityToReturn = plainEntityToReturn.replaceAll("<td></td>", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("<td>", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("</td>", ";");
		plainEntityToReturn = plainEntityToReturn.replaceAll("&nbsp", "vazio");
		plainEntityToReturn = plainEntityToReturn.replaceAll("\\s", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll(";$", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("\\.", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll(",", ".");
		plainEntityToReturn = plainEntityToReturn.replaceAll(";;", ";vazio;");

		return plainEntityToReturn;
	}

	public void parOuImpar(Sorteio sorteio) {
		if (sorteio.getDezena1() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
		if (sorteio.getDezena2() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
		if (sorteio.getDezena3() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
		if (sorteio.getDezena4() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
		if (sorteio.getDezena5() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
		if (sorteio.getDezena6() % 2 == 0) {
			par = par + 1;
		} else {
			impar = impar + 1;
		}
	}

	public String readLine(BufferedReader reader) throws IOException {
		String lineToReturn = null;

		do {
			lineToReturn = reader.readLine();
		} while (lineToReturn.matches(""));
		return lineToReturn;
	}
}
