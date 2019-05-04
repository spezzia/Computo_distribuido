package musica;

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

import javazoom.jl.decoder.InputStreamSource;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

import java.util.Base64;



public class reproducir {

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, JavaLayerException {
		// TODO Auto-generated method stub
		
		int camtidad = 38; // frames/ seg
		int duracion = 22; //segundos de la cancion;
		int total = 10716; // total fremes
		
		int inicio = 60; // segundo que inicia;
		int fiinal = 90; //
		
		int comienzo = 60 * 38;
		int descomienzo = 90 * 38;             
		
	 
	   
		java.io.File fichero = new java.io.File("/home/jorge/Música/1.mp3");
		FileInputStream ficheroStream = new FileInputStream(fichero);
		byte contenido[] = new byte[(int)fichero.length()];
		ficheroStream.read(contenido);
		
		String asB64 = Base64.getEncoder().encodeToString(contenido);
		
		
		
		
		
		byte[] asBytes = Base64.getDecoder().decode(asB64);
		
		File temporal = File.createTempFile("cancion.mp3",null);
		
		FileOutputStream fileOuputStream = new FileOutputStream(temporal);
		fileOuputStream.write(asBytes);
		fileOuputStream.close();
		

	        
	 
	        
		AdvancedPlayer apl = new AdvancedPlayer(new FileInputStream("/home/jorge/Música/1.mp3"));
		long startTime = System.nanoTime();
		AdvancedPlayer apl1 = new AdvancedPlayer(new FileInputStream(temporal));
		//apl.play(comienzo,descomienzo);
		apl1.play(comienzo,descomienzo);
	    long endTime = System.nanoTime();
	    System.out.println("Duración: " + (endTime-startTime)/1e6 + " ms"); 
	    
	    
	    temporal.deleteOnExit();
	}
	

}
