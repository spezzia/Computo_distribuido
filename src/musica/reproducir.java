package musica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javazoom.jl.decoder.InputStreamSource;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

import java.util.Base64;



public class reproducir {

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, JavaLayerException {
		// TODO Auto-generated method stub
		
		
		// Crear un string base 64 de un archivo de musica.
		// El proposito de este abstracto es saber como se enviará los archivos por corba
	    // En este caso el string a enviar es asB64
		java.io.File fichero = new java.io.File("/home/jorge/Música/1.mp3");
		FileInputStream ficheroStream = new FileInputStream(fichero);
		byte contenido[] = new byte[(int)fichero.length()];
		ficheroStream.read(contenido);
		
		String asB64 = Base64.getEncoder().encodeToString(contenido);
		
		
		
		//System.out.print(asB64.length());
		String ruta = "/home/jorge/Documentos/archivo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("base64");
        bw.write("\n Hola amigos");
        bw.close();
        
        String texto="";
        
        FileReader lector=new FileReader("/home/jorge/Documentos/archivo.txt");

      //El contenido de lector se guarda en un BufferedReader
      BufferedReader contenido1=new BufferedReader(lector);

      //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
      while((texto=contenido1.readLine())!=null)
      {
    	  System.out.println(texto);
      	}
     
		
		
		// De un string de base 64 se genera un archivo temporal.
		// En este caso el archivo es una cancion mp3
		
		byte[] asBytes = Base64.getDecoder().decode(asB64);
		
		File temporal = File.createTempFile("cancion.mp3",null);
		
		FileOutputStream fileOuputStream = new FileOutputStream(temporal);
		fileOuputStream.write(asBytes);
		fileOuputStream.close();
		

	        
	 
	    /// Con este fragmento de codigo se puede reproducir un pedazo de cancion determinado por 
		/// comienco y descomienzo
		
		
		
		/// Dentro de una cancion mp3 se encomtro la siguiente informacion
		
				int camtidad = 38; // frames/ seg
				int duracion = 220; //segundos de la cancion;
				int total = 10716; // total fremes
				
				int inicio = 60; // segundo que inicia;
				int fiinal = 90; //
				
				int comienzo = 60 * 38;
				int descomienzo = 90 * 38;             
				
		
		
		/*AdvancedPlayer apl = new AdvancedPlayer(new FileInputStream("/home/jorge/Música/1.mp3"));
		long startTime = System.nanoTime();
		AdvancedPlayer apl1 = new AdvancedPlayer(new FileInputStream(temporal));
		//apl.play(comienzo,descomienzo);
		apl1.play(comienzo,descomienzo);
	    long endTime = System.nanoTime();
	    System.out.println("Duración: " + (endTime-startTime)/1e6 + " ms"); */
	    
	    
	    
	    ///Se elimina el archivo temporal
	    temporal.deleteOnExit();
	}
	

}
