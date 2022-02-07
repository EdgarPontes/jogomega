package br.com.paginamega.util;

import java.io.*;
import java.net.*;

public class BaixarPageJogo {
	
	public void BaixarPage() {
		
		String url;
		File file = new File("d_megasc.htm");

		try {
			
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

			url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/megasena/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbwMPI0sDBxNXAOMwrzCjA0sjIEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wNnUwNHfxcnSwBgIDUyhCvA5EawAjxsKckMjDDI9FQE-F4ca/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K8DBC0QPVN93KQ10G1/res/id=historicoHTML/c=cacheLevelPage/=/";

			BufferedWriter out = new BufferedWriter(new FileWriter(file));

			String inputLine;

			// add user agent
			URLConnection urlConnection = new URL(url).openConnection();
			urlConnection.addRequestProperty("User-Agent", "Mozilla");
			urlConnection.setReadTimeout(5000);
			urlConnection.setConnectTimeout(5000);

			try (InputStream is = new URL(url).openStream();
				 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

				while ((inputLine = br.readLine()) != null) {
					// Grava pagina no arquivo
					out.write(inputLine);
					out.newLine();
				}

			}
			System.out.println("Download completo!");

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