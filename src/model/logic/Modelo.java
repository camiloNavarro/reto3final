package model.logic;

import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ArregloDinamico;
import model.data_structures.IArbol;
import model.data_structures.ILista;
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
	
	private IArbol <Audio> arbolAudios;

	
	public static String CONTEXT = 	"./data/context_content_features-large.csv";


	public Modelo()
	{
		ArbolRojoNegro arbolAudios = new ArbolRojoNegro<Audio>();
	}

	public ArregloDinamico<Audio> darAudiosDinamico ()
	{
		return arbolAudios.darPreorden();
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
				int instrumentalness = Integer.parseInt(record.get("instrumentalness"));
				int liveness = Integer.parseInt(record.get("liveness"));
				int speechiness = Integer.parseInt(record.get("speechiness"));
				int danceability = Integer.parseInt(record.get("danceability"));
				int valence = Integer.parseInt(record.get("valence"));
				int loudness = Integer.parseInt(record.get("loudness"));
				int tempo = Integer.parseInt(record.get("tempo"));
				int acousticness = Integer.parseInt(record.get("acousticness"));
				int energy = Integer.parseInt(record.get("energy"));
				String artist_id = record.get("artist_id");
				
				Audio nuevo=new Audio(audioID, instrumentalness, liveness, speechiness, danceability, valence, loudness, tempo, acousticness, energy, artist_id );
				arbolAudios.insertar(nuevo);
				System.out.println("audio:"+ audioID);
			}
		}
		catch (Exception e)
		{

		}
	}
	


	public void req1(String cat,int min, int max)
	{
		ordenarListaInstr(darAudiosDinamico(), true, cat);
		ArregloDinamico<Audio> buscados=new ArregloDinamico<>(10);
		
		if(cat.compareToIgnoreCase("instrumentalness")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getInstrumentalness()>=min && darAudiosDinamico().getElement(i).getInstrumentalness()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getInstrumentalness()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("liveness")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getILivness()>=min && darAudiosDinamico().getElement(i).getILivness()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getILivness()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("speechiness")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getSpeechiness()>=min && darAudiosDinamico().getElement(i).getSpeechiness()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getSpeechiness()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("danceability")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getDanceability()>=min && darAudiosDinamico().getElement(i).getDanceability()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getDanceability()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("valence")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getValence()>=min && darAudiosDinamico().getElement(i).getValence()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getValence()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("loudness")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getLoudness()>=min && darAudiosDinamico().getElement(i).getLoudness()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getLoudness()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("tempo")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getTempo()>=min && darAudiosDinamico().getElement(i).getTempo()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getTempo()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("acousticness")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getAcoustince()>=min && darAudiosDinamico().getElement(i).getAcoustince()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getAcoustince()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else if(cat.compareToIgnoreCase("energy")==0){
			for(int i=1;i<darAudiosDinamico().size();i++){
				if(darAudiosDinamico().getElement(i).getEnergy()>=min && darAudiosDinamico().getElement(i).getEnergy()<=max){
					buscados.addLast(darAudiosDinamico().getElement(i));
				}
				if(darAudiosDinamico().getElement(i).getEnergy()>max){
					i=darAudiosDinamico().size();
				}
			}
		}
		
		else{
			System.out.println("no existe esa caracteristica");
			return;
		}
		
		int artistas =0;
		String art="artista";
		ordenarListaInstr(buscados, true, art);
		String actual="";
		
		for(int i =1 ; i<buscados.size(); i++){
			if(buscados.getElement(i).getArtistiID().compareToIgnoreCase(actual)!=0){
				artistas++;
				actual = buscados.getElement(i).getArtistiID();
			}
		}
		
		System.out.println("+++++ Req No. 1 Results... +++++");
		System.out.println(cat +" is between "+min +" and " + max);
		System.out.println("total reproductions: " + buscados.size() + " total of unique artists: " + artistas);
	}
	
	public void req2(int min1,int min2, int max1, int max2)
	{
		String ener = "energy";
		ordenarListaInstr(darAudiosDinamico(), true, ener);
		ArregloDinamico<Audio> buscados=new ArregloDinamico<>(10);
		
		for(int i=1; i<darAudiosDinamico().size(); i++){
			if(darAudiosDinamico().getElement(i).getEnergy()>=min1 && darAudiosDinamico().getElement(i).getEnergy()<=max1){
				buscados.addLast(darAudiosDinamico().getElement(i));
			}
			if(darAudiosDinamico().getElement(i).getEnergy()>max1){
				i=darAudiosDinamico().size();
			}
		}
		
		String dance = "danceability";
		ordenarListaInstr(buscados, true, dance);
		ArregloDinamico<Audio> buscados2=new ArregloDinamico<>(10);
		
		for(int i=1;i<buscados2.size();i++){
			if(buscados.getElement(i).getDanceability()>=min2 && buscados.getElement(i).getDanceability()<=max2){
				buscados2.addLast(darAudiosDinamico().getElement(i));
			}
			if(darAudiosDinamico().getElement(i).getDanceability()>max2){
				i=darAudiosDinamico().size();
			}
		}
		
		int artistas =0;
		String id="id";
		ordenarListaInstr(buscados2, true, id);
		String actual="";
		
		for(int i =1 ; i<buscados2.size(); i++){
			if(buscados2.getElement(i).getAudioID().compareToIgnoreCase(actual)!=0){
				artistas++;
				actual = buscados2.getElement(i).getAudioID();
			}
		}
		
		System.out.println("+++++ Req No. 2 Results... +++++");
		System.out.println(" energy is between "+min1 +" and " + max1);
		System.out.println(" danceability is between "+min2 +" and " + max2);
		System.out.println(" total of unique tracks: " + artistas);
		for(int i =1; i<6; i++){
			System.out.println(" track"+i+": "+ buscados2.getElement(i).getArtistiID()+" with energy: "+buscados2.getElement(i).getEnergy()+" and danceability: "+buscados2.getElement(i).getDanceability());
		}
		
	}
	
	public void req3(int min1,int min2, int max1, int max2)
	{
		String ener = "instrumentalness";
		ordenarListaInstr(darAudiosDinamico(), true, ener);
		ArregloDinamico<Audio> buscados=new ArregloDinamico<>(10);
		
		for(int i=1; i<darAudiosDinamico().size(); i++){
			if(darAudiosDinamico().getElement(i).getInstrumentalness()>=min1 && darAudiosDinamico().getElement(i).getInstrumentalness()<=max1){
				buscados.addLast(darAudiosDinamico().getElement(i));
			}
			if(darAudiosDinamico().getElement(i).getInstrumentalness()>max1){
				i=darAudiosDinamico().size();
			}
		}
		
		String dance = "tempo";
		ordenarListaInstr(buscados, true, dance);
		ArregloDinamico<Audio> buscados2=new ArregloDinamico<>(10);
		
		for(int i=1;i<buscados.size();i++){
			if(buscados.getElement(i).getTempo()>=min2 && buscados.getElement(i).getTempo()<=max2){
				buscados2.addLast(darAudiosDinamico().getElement(i));
			}
			if(darAudiosDinamico().getElement(i).getDanceability()>max2){
				i=buscados.size();
			}
		}
		
		int artistas =0;
		String id="id";
		ordenarListaInstr(buscados2, true, id);
		String actual="";
		
		for(int i =1 ; i<buscados2.size(); i++){
			if(buscados2.getElement(i).getAudioID().compareToIgnoreCase(actual)!=0){
				artistas++;
				actual = buscados2.getElement(i).getAudioID();
			}
		}
		
		System.out.println("+++++ Req No. 2 Results... +++++");
		System.out.println(" instrumentalness is between "+min1 +" and " + max1);
		System.out.println(" tempo is between "+min2 +" and " + max2);
		System.out.println(" total of unique tracks: " + artistas);
		for(int i =1; i<6; i++){
			System.out.println(" track"+i+": "+ buscados2.getElement(i).getArtistiID()+" with instrumentalness: "+buscados2.getElement(i).getInstrumentalness()+" and tempo: "+buscados2.getElement(i).getTempo());
		}
		
	}
	
	public void req4()
	{
		
	}

	public void ordenarListaInstr (ILista <Audio> lista, boolean ascendente,String cat)
	{
		if(cat.compareToIgnoreCase("instrumentalness")==0){
			Comparator<Audio> comparadorXistrumental = new Audio.ComparadorXistrumental();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXistrumental, ascendente);
		}
		else if(cat.compareToIgnoreCase("liveness")==0){
			Comparator<Audio> comparadorXLiv = new Audio.ComparadorXlivness();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXLiv, ascendente);
		}
		else if(cat.compareToIgnoreCase("speechiness")==0){
			Comparator<Audio> comparadorXSpe = new Audio.ComparadorXSpeech();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXSpe, ascendente);
		}
		else if(cat.compareToIgnoreCase("danceability")==0){
			Comparator<Audio> comparadorXDan = new Audio.ComparadorXDance();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXDan, ascendente);
		}
		else if(cat.compareToIgnoreCase("valence")==0){
			Comparator<Audio> comparadorXVal = new Audio.ComparadorXValence();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXVal, ascendente);
		}
		else if(cat.compareToIgnoreCase("loudness")==0){
			Comparator<Audio> comparadorXLou = new Audio.ComparadorXLoud();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXLou, ascendente);
		}
		else if(cat.compareToIgnoreCase("tempo")==0){
			Comparator<Audio> comparadorXTem = new Audio.ComparadorXTempo();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXTem, ascendente);
		}
		else if(cat.compareToIgnoreCase("acousticness")==0){
			Comparator<Audio> comparadorXAco = new Audio.ComparadorXAcouns();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXAco, ascendente);
		}
		else if(cat.compareToIgnoreCase("energy")==0){
			Comparator<Audio> comparadorXener = new Audio.ComparadorXAcouns();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXener, ascendente);
		}
		else if(cat.compareToIgnoreCase("artista")==0){
			Comparator<Audio> comparadorXar = new Audio.ComparadorXArtista();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXar, ascendente);
		}
		else if(cat.compareToIgnoreCase("id")==0){
			Comparator<Audio> comparadorXid = new Audio.ComparadorXId();
			Ordenamiento<Audio> algsOrdenamientoVideos = new Ordenamiento<Audio>();

				algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXid, ascendente);
		}
		else{
			System.out.println("no existe la caracteristica");
		}
		
	}
	

	public ILista<YoutubeVideo> subListaPorPais (ILista<YoutubeVideo> lista, String country)
	{
		ILista <YoutubeVideo> subListaPais = new ArregloDinamico <YoutubeVideo>(1);

		for (int i = 1 ; i <= lista.size(); i ++)
		{
			YoutubeVideo actual = lista.getElement(i);
			if(actual.getCountry().compareTo(country) == 0)
			{
				subListaPais.addLast(actual);
			}

		}
		return subListaPais;
	}

	public ILista<YoutubeVideo> subListaPorCategoria (ILista<YoutubeVideo> lista, String categoryID)
	{
		ILista <YoutubeVideo> subListaCategoria = new ArregloDinamico <YoutubeVideo>(1);

		for (int i = 1 ; i <= lista.size(); i ++)
		{
			YoutubeVideo actual = lista.getElement(i);
			if(categoryID.compareTo("23") == 0 && (actual.getCategoryID().compareTo(categoryID)==0 || actual.getCategoryID().compareTo("34")==0) )
			{         
				subListaCategoria.addLast(actual);
			}
			else if(actual.getCategoryID().compareTo(categoryID)==0)
			{         
				subListaCategoria.addLast(actual);
			}

		}
		return subListaCategoria;
	}

	public ILista<YoutubeVideo> subListaPorPaisCategoria (ILista<YoutubeVideo> lista,String categoryID, String country )
	{
		ILista <YoutubeVideo> subListaPaisCategoria = new ArregloDinamico <YoutubeVideo>(1);

		for (int i = 1 ; i <= lista.size(); i ++)
		{
			YoutubeVideo actual = lista.getElement(i);
			if((categoryID.compareTo("23") == 0 && (actual.getCategoryID().compareTo(categoryID)==0 || actual.getCategoryID().compareTo("34")==0)) && (actual.getCountry().compareTo(country) == 0) )
			{         
				subListaPaisCategoria.addLast(actual);
			}
			else if((actual.getCategoryID().compareTo(categoryID)==0) && (actual.getCountry().compareTo(country) == 0))
			{         
				subListaPaisCategoria.addLast(actual);
			}

		}
		return subListaPaisCategoria;
	}

	public ILista <YoutubeVideo> subListaPorPaisTag (ILista<YoutubeVideo> lista,String tag, String country )
	{
		ILista <YoutubeVideo> subListaPaisTag = new ArregloDinamico <YoutubeVideo>(1);

		for (int i = 1 ; i <= lista.size(); i ++)
		{
			YoutubeVideo actual = lista.getElement(i);
			if(actual.getCountry().compareTo(country) == 0 && actual.getTags().contains(tag))
			{
				subListaPaisTag.addLast(actual);
			}
		}
		return subListaPaisTag;
	}
}