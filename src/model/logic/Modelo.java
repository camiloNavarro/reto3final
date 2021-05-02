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

	public ArregloDinamico<Audio> darVideosDinamico ()
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
				//System.out.println("videos:"+ darVideosDinamico().size());
			}
		}
		catch (Exception e)
		{

		}
	}


	public void ordenarLista (ILista <YoutubeVideo> lista,String tipoOrdenamiento, boolean ascendente)
	{
		Comparator<YoutubeVideo> comparadorXLikes = new YoutubeVideo.ComparadorXLikes();
		Ordenamiento<YoutubeVideo> algsOrdenamientoVideos = new Ordenamiento<YoutubeVideo>();

		if(tipoOrdenamiento.equalsIgnoreCase("Insertion"))
		{
			algsOrdenamientoVideos.ordenarInsercion(lista, comparadorXLikes, ascendente);
		}
		else if(tipoOrdenamiento.equalsIgnoreCase("Shell"))
		{
			algsOrdenamientoVideos.ordenarShell(lista, comparadorXLikes, ascendente);
		}
		else if(tipoOrdenamiento.equalsIgnoreCase("Merge"))
		{
			algsOrdenamientoVideos.ordenarMergeSort(lista, comparadorXLikes, ascendente);
		}
		else if(tipoOrdenamiento.equalsIgnoreCase("Quick"))
		{
			algsOrdenamientoVideos.ordenarQuickSort(lista, comparadorXLikes, ascendente);
		}
	}

	public void ordenarListaNombre (ILista <YoutubeVideo> lista, boolean ascendente)
	{
		Comparator<YoutubeVideo> comparadorXTitulo = new YoutubeVideo.ComparadorXTitulo();
		Ordenamiento<YoutubeVideo> algsOrdenamientoVideos = new Ordenamiento<YoutubeVideo>();

		algsOrdenamientoVideos.ordenarMergeSort(lista, comparadorXTitulo, ascendente);
	}

	public void ordenarListaLikes (ILista <YoutubeVideo> lista, boolean ascendente)
	{
		Comparator<YoutubeVideo> comparadorXLikes = new YoutubeVideo.ComparadorXLikes();
		Ordenamiento<YoutubeVideo> algsOrdenamientoVideos = new Ordenamiento<YoutubeVideo>();

		algsOrdenamientoVideos.ordenarMergeSort(lista, comparadorXLikes, ascendente);
	}

	public void ordenarListaViews (ILista <YoutubeVideo> lista, boolean ascendente)
	{
		Comparator<YoutubeVideo> comparadorXViews = new YoutubeVideo.ComparadorXViews();
		Ordenamiento<YoutubeVideo> algsOrdenamientoVideos = new Ordenamiento<YoutubeVideo>();

		algsOrdenamientoVideos.ordenarMergeSort(lista, comparadorXViews, ascendente);
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