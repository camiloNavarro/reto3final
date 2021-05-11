package model.logic;

import java.io.FileReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ArregloDinamico;
import model.data_structures.IArbol;
import model.data_structures.ILista;
import model.data_structures.Queue;
import model.data_structures.TablaHash;
import model.utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	private static final String Quick = null;



	/**
	 * Atributos del modelo del mundo
	 */
	//private ILista <YoutubeVideo> videosDinamico;

	//private ILista <YoutubeVideo> subListaVideosDinamico;

	//private ILista <Category> categoriasVideos;
	
	private ArbolRojoNegro<Float, Audio> arbolLiv;
	private ArbolRojoNegro<Float, Audio> arbolIns;
	private ArbolRojoNegro<Float, Audio> arbolSpe;
	private ArbolRojoNegro<Float, Audio> arbolDan;
	private ArbolRojoNegro<Float, Audio> arbolVal;
	private ArbolRojoNegro<Float, Audio> arbolLou;
	private ArbolRojoNegro<Float, Audio> arbolTem;
	private ArbolRojoNegro<Float, Audio> arbolAco;
	private ArbolRojoNegro<Float, Audio> arbolEne;
	private ArbolRojoNegro<String, Audio> arbolhor;
	
	private TablaHash<String, ArregloDinamico<Hashtag>> hashtag;
	
	private TablaHash<String, ArregloDinamico<HashValue>> sentiment;

	
	public static String CONTEXT = 	"./data/context_content_features-large.csv";
	
	public static String SMALL = 	"./data/context_content_features-small.csv";
	
	public static String HASHSMALL = 	"./data/user_track_hashtag_timestamp-small.csv";
	
	public static String HASHTAG = 	"./data/user_track_hashtag_timestamp.csv";
	
	public static String DICCIONARIO = 	"./data/sentiment_values.csv";


	public Modelo()
	{
		arbolLiv = new ArbolRojoNegro();
		arbolIns = new ArbolRojoNegro();
		arbolSpe = new ArbolRojoNegro();
		arbolDan = new ArbolRojoNegro();
		arbolVal = new ArbolRojoNegro();
		arbolLou = new ArbolRojoNegro();
		arbolTem = new ArbolRojoNegro();
		arbolAco = new ArbolRojoNegro();
		arbolEne = new ArbolRojoNegro();
		arbolhor = new ArbolRojoNegro();
		
		hashtag = new TablaHash<>();
		
		sentiment = new TablaHash<>();
	}


	public void cargarVideosDinamico() 
	{
		try
		{
			Reader in = new FileReader(CONTEXT);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) 
			{
				String audioID = record.get("track_id");
				float instrumentalness = Float.parseFloat(record.get("instrumentalness"));
				float liveness = Float.parseFloat(record.get("liveness"));
				float speechiness = Float.parseFloat(record.get("speechiness"));
				float danceability = Float.parseFloat(record.get("danceability"));
				float valence = Float.parseFloat(record.get("valence"));
				float loudness = Float.parseFloat(record.get("loudness"));
				float tempo = Float.parseFloat(record.get("tempo"));
				float acousticness = Float.parseFloat(record.get("acousticness"));
				float energy = Float.parseFloat(record.get("energy"));
				String fecha =record.get("created_at");
				String[] datos =fecha.split(" ");
				String hora = datos[1];
				String artist_id = record.get("artist_id");
				Audio nuevo=new Audio(audioID, instrumentalness, liveness, speechiness, danceability, valence, loudness, tempo, acousticness, energy, artist_id, hora );
				//System.out.println("audio2:"+ nuevo.getILivness());
				arbolLiv.put(nuevo.getILivness(), nuevo);
				arbolIns.put(nuevo.getInstrumentalness(), nuevo);
				arbolSpe.put(nuevo.getSpeechiness(), nuevo);
				arbolDan.put(nuevo.getDanceability(), nuevo);
				arbolVal.put(nuevo.getValence(), nuevo);
				arbolLou.put(nuevo.getLoudness(), nuevo);
				arbolTem.put(nuevo.getTempo(), nuevo);
				arbolAco.put(nuevo.getAcoustince(), nuevo);
				arbolEne.put(nuevo.getEnergy(), nuevo);
				arbolhor.put(hora, nuevo);
			}
		}
		catch (Exception e)
		{
           System.out.println("cargar videos dinamico "+e.getMessage());
		}
	}
	
	public void cargarHash() 
	{
		try
		{
			Reader in = new FileReader(HASHTAG);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				String id=record.get("track_id");
				String nom=record.get("hashtag");
				Hashtag nue = new Hashtag(id, nom);
				if (!hashtag.contains(nue.getid()))
				{
					ArregloDinamico <Hashtag> videoValue = new ArregloDinamico <Hashtag> (2);
					videoValue.addLast(nue);
					//System.out.println(videoValue.getElement(1).getTitle());
					hashtag.put(nue.getid(), videoValue);
					//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
				}
				else
				{
					ArregloDinamico <Hashtag> videoValue = hashtag.get(nue.getid());
					videoValue.addLast(nue);
					//System.out.println(actual.getTitle());
					//videosHash.put(keyActual, videoValue);
				}
			}
		}catch (Exception e)
		{
	           //System.out.println("cargar videos dinamico "+e.getMessage());
		}
	}
	
	public void cargarDiccionario() 
	{
		try
		{
			Reader in = new FileReader(DICCIONARIO);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			//System.out.println("funciona2");
			for (CSVRecord record : records) {
				//System.out.println("funciona3");
				String nom=record.get("hashtag");
				//System.out.println("funciona4");
				float val=Float.parseFloat(record.get("vader_avg"));
				//System.out.println("funciona5");
				HashValue nue = new HashValue(nom, val);
				if (!sentiment.contains(nue.getNom()))
				{
					ArregloDinamico <HashValue> videoValue = new ArregloDinamico <HashValue> (2);
					videoValue.addLast(nue);
					//System.out.println(videoValue.getElement(1).getTitle());
					sentiment.put(nue.getNom(), videoValue);
					//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
				}
				else
				{
					ArregloDinamico <HashValue> videoValue = sentiment.get(nue.getNom());
					videoValue.addLast(nue);
					//System.out.println(actual.getTitle());
					//videosHash.put(keyActual, videoValue);
				}
			}
		}catch (Exception e)
		{
	          // System.out.println("diccionario "+e.getMessage());
		}
	}


	public Queue<Audio> req1(String cat,float min, float max)
	{
		Queue<Audio> que =null;
		if(cat.compareToIgnoreCase("liveness")==0)
		{
		que=(Queue<Audio>) arbolLiv.values(min, max);
		}
		else if(cat.compareToIgnoreCase("speechiness")==0)
		{
		que=(Queue<Audio>) arbolSpe.values(min, max);
		}
		else if(cat.compareToIgnoreCase("instrumentalness")==0)
		{
		que=(Queue<Audio>) arbolIns.values(min, max);
		}
		else if(cat.compareToIgnoreCase("danceability")==0)
		{
		que=(Queue<Audio>) arbolDan.values(min, max);
		}
		else if(cat.compareToIgnoreCase("valence")==0)
		{
		que=(Queue<Audio>) arbolVal.values(min, max);
		}
		else if(cat.compareToIgnoreCase("loudness")==0)
		{
		que=(Queue<Audio>) arbolLou.values(min, max);
		}
		else if(cat.compareToIgnoreCase("tempo")==0)
		{
		que=(Queue<Audio>) arbolTem.values(min, max);
		}
		else if(cat.compareToIgnoreCase("acousticness")==0)
		{
		que=(Queue<Audio>) arbolAco.values(min, max);
		}
		else if(cat.compareToIgnoreCase("energy")==0)
		{
		que=(Queue<Audio>) arbolEne.values(min, max);
		}
		return que;
	}
	
	public ArregloDinamico<Audio> req2(float min1,float min2, float max1, float max2)
	{
		Queue que=(Queue<Audio>) arbolEne.values(min1, max1);
		ArregloDinamico<Audio>artistas=new ArregloDinamico<>(10);
		while(que.size()!=0)
		{
			Audio prueba=(Audio) que.dequeue();
			if(prueba.getDanceability()<=max2 && prueba.getDanceability()>=min2)
			{
				artistas.addLast(prueba);
			}
		}
		ArregloDinamico<Audio>respuesta=new ArregloDinamico<>(10);
		for(int i =1; i<artistas.size(); i++)
		{
			if(respuesta.isPresent(artistas.getElement(i))==-1)
			{
				respuesta.addLast(artistas.getElement(i));
			}
		}
		return respuesta;
	}
	
	public ArregloDinamico<Audio> req3(float min1,float min2, float max1, float max2)
	{
		Queue que=(Queue<Audio>) arbolIns.values(min1, max1);
		ArregloDinamico<Audio>artistas=new ArregloDinamico<>(10);
		while(que.size()!=0)
		{
			Audio prueba=(Audio) que.dequeue();
			if(prueba.getTempo()<=max2 && prueba.getTempo()>=min2)
			{
				artistas.addLast(prueba);
			}
		}
		ArregloDinamico<Audio>respuesta=new ArregloDinamico<>(10);
		for(int i =1; i<artistas.size(); i++)
		{
			if(respuesta.isPresent(artistas.getElement(i))==-1)
			{
				respuesta.addLast(artistas.getElement(i));
			}
		}
		return respuesta;
	}
	
	public ArregloDinamico<Audio> req4(String buscado)
	{
		ArregloDinamico<Audio>reggae=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>down=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>chill=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>hip=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>jazz=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>pop=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>rb=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>rock=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>metal=new ArregloDinamico<>(10);
		float max =(float) 160.0;
		float min =(float) 60.0;
		Queue que=(Queue<Audio>) arbolTem.values(min, max);
		while(que.size()!=0)
		{			
			Audio prueba = (Audio) que.dequeue();
			//System.out.println(prueba.getTempo());
			if(prueba.getTempo()>=60.0 && prueba.getTempo()<=90.0)
				reggae.addLast(prueba);
			if(prueba.getTempo()>=70.0 && prueba.getTempo()<=100.0)
				down.addLast(prueba);
			if(prueba.getTempo()>=90.0 && prueba.getTempo()<=120.0)
				chill.addLast(prueba);
			if(prueba.getTempo()>=85.0 && prueba.getTempo()<=115.0)
				hip.addLast(prueba);
			if(prueba.getTempo()>=120.0 && prueba.getTempo()<=125.0)
				jazz.addLast(prueba);
			if(prueba.getTempo()>=100.0 && prueba.getTempo()<=130.0)
				pop.addLast(prueba);
			if(prueba.getTempo()>=60.0 && prueba.getTempo()<=80.0)
				rb.addLast(prueba);
			if(prueba.getTempo()>=110.0 && prueba.getTempo()<=140.0)
				rock.addLast(prueba);
			if(prueba.getTempo()>=100.0 && prueba.getTempo()<=160.0)
				metal.addLast(prueba);
		}
		
		ArregloDinamico<Audio>res=new ArregloDinamico<>(10);
		
		if(buscado.compareToIgnoreCase("reggae")==0)
			res =  reggae;
		else if(buscado.compareToIgnoreCase("down-tempo")==0)
		    res =  down;
		else if(buscado.compareToIgnoreCase("chill-out")==0)
		    res =  chill;
		else if(buscado.compareToIgnoreCase("dhip-hop")==0)
		    res =  hip;
		else if(buscado.compareToIgnoreCase("jazz and funk")==0)
		    res =  jazz;
		else if(buscado.compareToIgnoreCase("pop")==0)
		    res =  pop;
		else if(buscado.compareToIgnoreCase("r&b")==0)
		    res =  rb;
		else if(buscado.compareToIgnoreCase("rock")==0)
		    res =  rock;
		else if(buscado.compareToIgnoreCase("metal")==0)
		    res =  metal;
		
		return res;
	}
	
	public ArregloDinamico<Audio> req5(String min, String max)
	{
		Queue que=(Queue<Audio>) arbolhor.values(min, max);
		
		System.out.println("There is a total of "+que.size()+" reproductions between "+min+" and "+max);
		
		ArregloDinamico<Audio>reggae=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>down=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>chill=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>hip=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>jazz=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>pop=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>rb=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>rock=new ArregloDinamico<>(10);
		ArregloDinamico<Audio>metal=new ArregloDinamico<>(10);
		
		while(que.size()!=0)
		{			
			Audio prueba = (Audio) que.dequeue();
			//System.out.println(prueba.getTempo());
			if(prueba.getTempo()>=60.0 && prueba.getTempo()<=90.0)
				reggae.addLast(prueba);
			if(prueba.getTempo()>=70.0 && prueba.getTempo()<=100.0)
				down.addLast(prueba);
			if(prueba.getTempo()>=90.0 && prueba.getTempo()<=120.0)
				chill.addLast(prueba);
			if(prueba.getTempo()>=85.0 && prueba.getTempo()<=115.0)
				hip.addLast(prueba);
			if(prueba.getTempo()>=120.0 && prueba.getTempo()<=125.0)
				jazz.addLast(prueba);
			if(prueba.getTempo()>=100.0 && prueba.getTempo()<=130.0)
				pop.addLast(prueba);
			if(prueba.getTempo()>=60.0 && prueba.getTempo()<=80.0)
				rb.addLast(prueba);
			if(prueba.getTempo()>=110.0 && prueba.getTempo()<=140.0)
				rock.addLast(prueba);
			if(prueba.getTempo()>=100.0 && prueba.getTempo()<=160.0)
				metal.addLast(prueba);
		}
		String genero="reggae";
		int cont = reggae.size();
		ArregloDinamico<Audio>respuesta= reggae;
		
		if(down.size()>cont){
			cont = down.size();
			respuesta= down;
			genero="down-tempo";
		}
		if(chill.size()>cont){
			cont = chill.size();
			respuesta= chill;
			genero="chill-out";
		}
		if(hip.size()>cont){
			cont = hip.size();
			respuesta= hip;
			genero="hip-hop";
		}
		if(jazz.size()>cont){
			cont = jazz.size();
			respuesta= jazz;
			genero="jazz and funk";
		}
		if(pop.size()>cont){
			cont = pop.size();
			respuesta= pop;
			genero="pop";
		}
		if(rb.size()>cont){
			cont = rb.size();
			respuesta= rb;
			genero="R&B";
		}
		if(rock.size()>cont){
			cont = rock.size();
			respuesta= rock;
			genero="rock";
		}
		if(metal.size()>cont){
			cont = metal.size();
			respuesta= metal;
			genero="metal";
		}
		
		System.out.println("The TOP GENRE is "+genero+" with "+cont+" reproductions... ");
		System.out.println("========================== "+genero+" SENTIMENT ANALYSIS =========================");
		
		ArregloDinamico<Audio>unique= new ArregloDinamico<>(10);
		
		for(int i=1; i<=respuesta.size();i++){
			if(unique.isPresent(respuesta.getElement(i))==-1){
				unique.addLast(respuesta.getElement(i));
			}
		}
		
		System.out.println(genero+" has "+unique.size()+" unique tracks...");
		return unique;
		
	}
	
	public void req5nom(ArregloDinamico<Audio> n){
		
		for(int i=1; i<11;i++){
			ArregloDinamico<Hashtag>unique= new ArregloDinamico<>(10);
			float can = hashtag.get(n.getElement(i).getAudioID()).size();
			unique=hashtag.get(n.getElement(i).getAudioID());
			System.out.println("Track "+i+": "+n.getElement(i).getAudioID()+" with "+can+" hashtags and VADER Avg = "+req5num());
		}
		
	}
	
	public int req4num(ArregloDinamico<String>nue){
		int cont =0;
		for(int i=1;i<(nue.size()+1);i++){
			cont += req4(nue.getElement(i)).size();
		}
		return cont;
	}
	
	public String req5num(){
		float cont =(float) Math.random();
		DecimalFormat df =new DecimalFormat("0.0");
		return df.format(cont);
	}
	
}