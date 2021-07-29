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

	private int par = 0;
	private int impar = 0;

	private final int indiceCidadeUF = 0;
	private final int indiceDataSorteio = 1;
	private final int indiceDezena1 = 2;
	private final int indiceDezena2 = 3;
	private final int indiceDezena3 = 4;
	private final int indiceDezena4 = 5;
	private final int indiceDezena5 = 6;
	private final int indiceDezena6 = 7;
	private final int indiceGanhadoresSena = 8;
	private final int indiceGanhadoresQuina = 9;
	private final int indiceGanhadoresQuadra = 10;
	private final int indiceRateioSena = 11;
	private final int indiceRateioQuina = 12;
	private final int indiceRateioQuadra = 13;
	private final int indiceArrecadacaoTotal = 14;
	private final int indiceValorAcumulado = 15;
	private final int indiceEstimativaPremio = 16;
	private final int indiceeAcumulado = 17;
	private final int indicesorteioEspecial = 18;
	private final int indiceObservacao = 19;

	@PersistenceContext
	EntityManager em;

	@Autowired
	private SorteioRepository sorteioRepository;

	public void extractDataFromHtml(String path1, Charset charset) throws ParseException {

		BufferedReader reader;
		String line = null;
		Integer ultimoId ;
		int contador = 0;
		try {
			
			Object getId = em.createQuery("SELECT MAX(id) FROM " + Sorteio.class.getName()).getSingleResult();

			ultimoId = getId == null ? 0 : Integer.parseInt(getId.toString());
			
			reader = Files.newBufferedReader(Paths.get(path1), charset);

			while ((line = reader.readLine()) != null) {
				String plainEntity = "";
				if (line.matches(".*<tr>")) {
					line = readLine(reader);
					if ((line.matches(".*<td align=.*"))) {
						plainEntity = readOneEntireEntity(reader);
						contador++;
						if (contador > ultimoId ) {
							System.out.println("linha numero: " + contador);
							Sorteio sorteio = assemblyEntity(plainEntity, contador);
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

	public Sorteio assemblyEntity(String plainEnity, int contador) throws ParseException {
		String[] entity = plainEnity.split(";");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Sorteio sorteio = new Sorteio();
		sorteio.setConcurso(contador);
		sorteio.setCidadeUF(entity[indiceCidadeUF].trim());
		sorteio.setDataDoSorteio(sdf.parse(entity[indiceDataSorteio].trim()));
		sorteio.setDezena1(Integer.parseInt(entity[indiceDezena1].trim()));
		sorteio.setDezena2(Integer.parseInt(entity[indiceDezena2].trim()));
		sorteio.setDezena3(Integer.parseInt(entity[indiceDezena3].trim()));
		sorteio.setDezena4(Integer.parseInt(entity[indiceDezena4].trim()));
		sorteio.setDezena5(Integer.parseInt(entity[indiceDezena5].trim()));
		sorteio.setDezena6(Integer.parseInt(entity[indiceDezena6].trim()));
		sorteio.setGanhadoresSena(Integer.parseInt(entity[indiceGanhadoresSena].trim()));
		sorteio.setGanhadoresQuina(Integer.parseInt(entity[indiceGanhadoresQuina].trim()));
		sorteio.setGanhadoresQuadra(Integer.parseInt(entity[indiceGanhadoresQuadra].trim()));
		sorteio.setRateioSena(Double.parseDouble(entity[indiceRateioSena].trim()));
		sorteio.setRateioQuina(Double.parseDouble(entity[indiceRateioQuina].trim()));
		sorteio.setRateioQuadra(Double.parseDouble(entity[indiceRateioQuadra].trim()));
		sorteio.setValorAcumulado(Double.parseDouble(entity[indiceArrecadacaoTotal].trim()));
		sorteio.setEstimativaPremio(Double.parseDouble(entity[indiceEstimativaPremio].trim()));
		sorteio.setacumuladoProxConcurso(Double.parseDouble(entity[indiceValorAcumulado].trim()));
		sorteio.seteAcumulado(entity[indiceeAcumulado].trim());
		sorteio.setSorteioEspecial(entity[indicesorteioEspecial].trim());
		sorteio.setObservacao(entity[indiceObservacao].trim());
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
			if(line.matches(".*<table>")){
				while(!line.matches(".*</table>")){
					plainEntityToReturn = plainEntityToReturn.replaceAll("<tr>", "");
					plainEntityToReturn = plainEntityToReturn.replaceAll("<!-- LINHA DA CIDADE-->", "");
					plainEntityToReturn = plainEntityToReturn.replaceAll("<td></td>", "");
					plainEntityToReturn = plainEntityToReturn.replaceAll("<td>.*</td>", "");
					plainEntityToReturn = plainEntityToReturn.replaceAll("</tr>", "");
					plainEntityToReturn = plainEntityToReturn.replaceAll("<!-- FIM LINHA CIDADE-->", "");
					line = readLine(reader);
				}
			}
			plainEntityToReturn = plainEntityToReturn + "" + line;
			line = readLine(reader);
		}

		plainEntityToReturn = plainEntityToReturn.replaceAll("<td align=\"center\">", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("</td>", ";");
		plainEntityToReturn = plainEntityToReturn.replaceAll("<!-- DEZENAS -->", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("<!-- GANHADORES -->", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("<!-- RATEIO-->", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll("\\.", "");
		plainEntityToReturn = plainEntityToReturn.replaceAll(",", ".");
		plainEntityToReturn = plainEntityToReturn.replaceAll(";;", ";vazio;");
		plainEntityToReturn = plainEntityToReturn.replaceAll("</table>", "");

		return plainEntityToReturn.trim();
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
