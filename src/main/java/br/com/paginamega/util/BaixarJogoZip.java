package br.com.paginamega.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BaixarJogoZip {
	
	public void baixarZip() {
		// -----------------------------------------------------//
		// Step 1: Start creating a few objects we'll need.
		// -----------------------------------------------------//
		URL u;
		InputStream is = null;
		DataInputStream dis;

		try {
			// ------------------------------------------------------------//
			// Step 2: Create the URL. //
			// ------------------------------------------------------------//
			// Note: Put your real URL here, or better yet, read it as a //
			// command-line arg, or read it from a file. //
			// ------------------------------------------------------------//
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			/*Url para download dos números por ordem de sorteio*/
//			u = new URL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip");
			/*Url para dawnload dos números em ordem crescente*/
			u = new URL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_mgsasc.zip"); 
			// ----------------------------------------------//
			// Step 3: Open an input stream from the url. //
			// ----------------------------------------------//
			is = u.openStream(); // throws an IOException
			// ZipFile zipFile = new ZipFile(is);
			// entries = zipFile.entries();
			// FileInputStream fin = new FileInputStream(is);
			ZipInputStream zin = new ZipInputStream(is);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				System.out.println("Unzipping " + ze.getName());
				FileOutputStream fout = new FileOutputStream(ze.getName());
				for (int c = zin.read(); c != -1; c = zin.read()) {
					fout.write(c);
				}
				zin.closeEntry();
				fout.close();
			}
			zin.close();
		/**	File file = new File("mega.htm");
//			File file = new File("D_MEGA.HTM");
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			
			// DataInputStream dis = null;
			try {
				fis = new FileInputStream(file);
				// Here BufferedInputStream is added for fast reading.
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
				// dis.available() returns 0 if the file does not have more
				// lines.
//				while (dis.available() != 0) {
//					// this statement reads the line from the file and print it
//					// to
//					// the console.
//					System.out.println(dis.readLine());
//				}
				// dispose all the resources after using them.
				fis.close();
				bis.close();
				dis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}**/
			// -------------------------------------------------------------//
			// Step 4: //
			// -------------------------------------------------------------//
			// Convert the InputStream to a buffered DataInputStream. //
			// Buffering the stream makes the reading faster; the //
			// readLine() method of the DataInputStream makes the reading //
			// easier. //
			// -------------------------------------------------------------//
			// dis = new DataInputStream(new BufferedInputStream(is));
			// ------------------------------------------------------------//
			// Step 5: //
			// ------------------------------------------------------------//
			// Now just read each record of the input stream, and print //
			// it out. Note that it's assumed that this problem is run //
			// from a command-line, not from an application or applet. //
			// ------------------------------------------------------------//
			// while ((s = dis.readLine()) != null) {
			// System.out.println(s);
			// }
		} catch (MalformedURLException mue) {
			System.out.println("Ouch - a MalformedURLException happened.");
			mue.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Oops- an IOException happened.");
			ioe.printStackTrace();
			System.exit(1);
		} finally {
			// ---------------------------------//
			// Step 6: Close the InputStream //
			// ---------------------------------//
			try {
				is.close();
			} catch (IOException ioe) {
				// just going to ignore this one
			}
		} // end of 'finally' clause
	} // end of main
}