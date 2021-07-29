package br.com.paginamega.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;

public class BaixarPageJogo {
	
	public void BaixarPage() {
		
		URL url;
		File file = new File("d_megasc.htm");

		try {
			
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

			url = new URL("http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/megasena/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbwMPI0sDBxNXAOMwrzCjA0sjIEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wNnUwNHfxcnSwBgIDUyhCvA5EawAjxsKckMjDDI9FQE-F4ca/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K8DBC0QPVN93KQ10G1/res/id=historicoHTML/c=cacheLevelPage/=/");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			BufferedWriter out = new BufferedWriter(new FileWriter(file));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {

				// Imprime página no console
				//System.out.println(inputLine);

				// Grava pagina no arquivo
				out.write(inputLine);
				out.newLine();
			}
			System.out.println("Download completo!");
			
			in.close();
			out.flush();
			out.close();
		} catch (MalformedURLException mue) {
			System.out.println("Ouch - a MalformedURLException happened.");
			mue.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Oops- an IOException happened.");
			ioe.printStackTrace();
			System.exit(1);
		} 
	} // end of main
}