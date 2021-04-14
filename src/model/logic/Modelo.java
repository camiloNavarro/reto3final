package model.logic;

import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
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
	private ILista <YoutubeVideo> videosDinamico;

	private ILista <YoutubeVideo> subListaVideosDinamico;

	private ILista <Category> categoriasVideos;

	public static String CSV_SMALL = "./data/videos-small.csv";
	public static String CSV_ALL = "./data/videos-all.csv";
	public static String CSV_CATEGORIES = "./data/category-id.csv";


	public Modelo()
	{
		videosDinamico = new ArregloDinamico<YoutubeVideo>(7);
		categoriasVideos = new ArregloDinamico<Category> (7);
		subListaVideosDinamico = null;
	}

	public ILista<YoutubeVideo> darVideosDinamico ()
	{
		return videosDinamico;
	}

	public ILista<YoutubeVideo> darsubListaVideosDinamico ()
	{
		return subListaVideosDinamico;
	}

	public ILista<Category> darCategoriasVideos ()
	{
		return categoriasVideos;
	}

	public void getInfoFirst()
	{
		YoutubeVideo firstVideo = videosDinamico.getElement(1);

		System.out.println("El primer video cargado fue:");
		System.out.println("titulo: "+firstVideo.getTitle());
		System.out.println("canal: "+firstVideo.getChannelTitle());
		System.out.println("dias en tendencia: "+firstVideo.getTrendingDate());
		System.out.println("country: "+firstVideo.getCountry());
		System.out.println("views: "+firstVideo.getViews());
		System.out.println("likes: "+firstVideo.getLikes());
		System.out.println("dislikes: "+firstVideo.getDislikes());
	}

	public void cargarCategorias ()
	{
		try
		{
			Reader in = new FileReader(CSV_CATEGORIES);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withTrim().withFirstRecordAsHeader().withDelimiter('\t').parse(in);
			for (CSVRecord record : records) 
			{
				String categoryID = record.get("id");
				String categoryName = record.get("name");
				categoryName = categoryName.trim();
				
				Category nuevaCategoria = new Category (categoryID, categoryName);
				if (categoriasVideos.isPresent(nuevaCategoria) == -1)
				{
					darCategoriasVideos().addLast(nuevaCategoria);
				}
			}
		}
		catch (Exception e)
		{

		}

	}

	public void cargarVideosDinamico() 
	{
		try
		{
			Reader in = new FileReader(CSV_ALL);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) 
			{
				String videoID = record.get("video_id");
				String trendingDate = record.get("trending_date");
				String title = record.get("title");
				String channelTitle = record.get("channel_title");
				String categoryID = record.get("category_id");
				String publishTime = record.get("publish_time");
				String tags = record.get("tags");
				int views = Integer.parseInt(record.get("views"));
				int likes = Integer.parseInt(record.get("likes"));
				int dislikes = Integer.parseInt(record.get("dislikes"));
				int commentCount = Integer.parseInt(record.get("comment_count"));
				String thumbnailLink = record.get("thumbnail_link");
				String commentsDisabled = record.get("comments_disabled");
				String ratingsDisabled = record.get("ratings_disabled");
				String videoErrorOrRemoved = record.get("video_error_or_removed");
				String description = record.get("description");
				String country = record.get("country");

				YoutubeVideo nuevo=new YoutubeVideo(videoID, trendingDate, title,channelTitle,categoryID,publishTime,tags,views,likes,dislikes,commentCount,thumbnailLink,commentsDisabled,ratingsDisabled,videoErrorOrRemoved,description,country);
				darVideosDinamico().addLast(nuevo);
				//System.out.println("videos:"+ darVideosDinamico().size());
			}
		}
		catch (Exception e)
		{

		}
	}

	public Category getCategory (String categoryID)
	{
		Category buscada = null;
		boolean stop = false;
		for (int i = 1; i <= categoriasVideos.size() && !stop; i++)
		{
			Category actual = categoriasVideos.getElement(i);
			if(actual.getCategoryID().compareTo(categoryID) == 0)
			{
				buscada = actual;
				stop=true;
			}
		}
		return buscada;
	}

	public void getSubListaDinamico (int numElementos)
	{
		subListaVideosDinamico = videosDinamico.subLista(numElementos);
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

	public ILista<YoutubeVideo> Req1 (String categoryName, String country)
	{
		TablaHash<String, ArregloDinamico<YoutubeVideo>> videosHash = new TablaHash<String, ArregloDinamico<YoutubeVideo>>();
		for (int i = 1; i <= videosDinamico.size(); i++)
		{
			YoutubeVideo actual = videosDinamico.getElement(i);
			String categoryNameActual = getCategory(actual.getCategoryID()).getCategoryName();
			String keyActual = actual.getCountry()+"-"+categoryNameActual;

			if (!videosHash.contains(keyActual))
			{
				ArregloDinamico <YoutubeVideo> videoValue = new ArregloDinamico <YoutubeVideo> (2);
				videoValue.addLast(actual);
				//System.out.println(videoValue.getElement(1).getTitle());
				videosHash.put(keyActual, videoValue);
				//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
			}
			else
			{
				ArregloDinamico <YoutubeVideo> videoValue = videosHash.get(keyActual);
				videoValue.addLast(actual);
				//System.out.println(actual.getTitle());
				//videosHash.put(keyActual, videoValue);
			}
		}
		
		String wantedKey = country+"-"+categoryName;
		if(videosHash.contains(wantedKey)==true)
		{
		ArregloDinamico <YoutubeVideo> valorHash = videosHash.get(wantedKey);
		ordenarListaViews (valorHash, false);
		return valorHash;
		}
		else
		{
			ArregloDinamico <YoutubeVideo> vacia =new ArregloDinamico <YoutubeVideo>(1);
			return vacia;
		}

		
	}

	public void Req2 (String country)
	{
		TablaHash<String, ArregloDinamico<YoutubeVideo>> videosHash = new TablaHash<String, ArregloDinamico<YoutubeVideo>>();
		for (int i = 1; i <= videosDinamico.size(); i++)
		{
			YoutubeVideo actual = videosDinamico.getElement(i);
			String keyActual = actual.getCountry();

			if (!videosHash.contains(keyActual))
			{
				ArregloDinamico <YoutubeVideo> videoValue = new ArregloDinamico <YoutubeVideo> (2);
				videoValue.addLast(actual);
				//System.out.println(videoValue.getElement(1).getTitle());
				videosHash.put(keyActual, videoValue);
				//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
			}
			else
			{
				ArregloDinamico <YoutubeVideo> videoValue = videosHash.get(keyActual);
				videoValue.addLast(actual);
				//System.out.println(actual.getTitle());
				//videosHash.put(keyActual, videoValue);
			}
		}
		
		String wantedKey = country;
		if(videosHash.contains(wantedKey)==true)
		{
		ArregloDinamico <YoutubeVideo> valorHash = videosHash.get(wantedKey);
		ordenarListaNombre (valorHash, false);
		//System.out.println( valorHash.getElement(1).getTitle());
		YoutubeVideo mejor=null;
		int mas=0;
		YoutubeVideo ultimo=null;
		int cont=0;
		for(int i = 1; i<=valorHash.size();i++)
		{
			YoutubeVideo actual = valorHash.getElement(i);
			if(ultimo == null)
			{
				ultimo=actual;
				cont++;
			}
			else if (actual.getTitle().compareTo(ultimo.getTitle()) == 0)
			{
				cont++;
			}
			else  
			{
				if(cont>mas)
				{
						mas = cont;
						mejor = ultimo;
						//System.out.println( mejor.getTitle());
					   ultimo = actual;
					   cont=1;
				}
				else
				{
					ultimo = actual;
					cont=1;
				}
			}
		}
		System.out.println("el video mas veces trending en "+ country+" es:");
		System.out.println("titulo:  "+ mejor.getTitle());
		System.out.println("canal:  "+ mejor.getChannelTitle());
		System.out.println("pais:  "+ mejor.getCountry());
		System.out.println("dias:  "+ mas);
		}
		else
		{
			ArregloDinamico <YoutubeVideo> vacia =new ArregloDinamico <YoutubeVideo>(1);
		}
	}

	public void Req3 (String categoryName)
	{
		TablaHash<String, ArregloDinamico<YoutubeVideo>> videosHash = new TablaHash<String, ArregloDinamico<YoutubeVideo>>();
		for (int i = 1; i <= videosDinamico.size(); i++)
		{
			YoutubeVideo actual = videosDinamico.getElement(i);
			String categoryNameActual = getCategory(actual.getCategoryID()).getCategoryName();
			String keyActual = categoryNameActual;

			if (!videosHash.contains(keyActual))
			{
				ArregloDinamico <YoutubeVideo> videoValue = new ArregloDinamico <YoutubeVideo> (2);
				videoValue.addLast(actual);
				//System.out.println(videoValue.getElement(1).getTitle());
				videosHash.put(keyActual, videoValue);
				//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
			}
			else
			{
				ArregloDinamico <YoutubeVideo> videoValue = videosHash.get(keyActual);
				videoValue.addLast(actual);
				//System.out.println(actual.getTitle());
				//videosHash.put(keyActual, videoValue);
			}
		}
		
		String wantedKey = categoryName;
		if(videosHash.contains(wantedKey)==true)
		{
		ArregloDinamico <YoutubeVideo> valorHash = videosHash.get(wantedKey);
		ordenarListaNombre (valorHash, false);
		//System.out.println( valorHash.getElement(1).getTitle());
		YoutubeVideo mejor=null;
		int mas=0;
		YoutubeVideo ultimo=null;
		int cont=0;
		for(int i = 1; i<=valorHash.size();i++)
		{
			YoutubeVideo actual = valorHash.getElement(i);
			if(ultimo == null)
			{
				ultimo=actual;
				cont++;
			}
			else if (actual.getTitle().compareTo(ultimo.getTitle()) == 0)
			{
				cont++;
			}
			else  
			{
				if(cont>mas)
				{
						mas = cont;
						mejor = ultimo;
						//System.out.println( mejor.getTitle());
					   ultimo = actual;
					   cont=1;
				}
				else
				{
					ultimo = actual;
					cont=1;
				}
			}
		}
		System.out.println("el video mas veces trending de la categoria "+ categoryName+" es:");
		System.out.println("titulo:  "+ mejor.getTitle());
		System.out.println("canal:  "+ mejor.getChannelTitle());
		System.out.println("category id:  "+ mejor.getCategoryID());
		System.out.println("dias:  "+ mas);
		}
		else
		{
			ArregloDinamico <YoutubeVideo> vacia =new ArregloDinamico <YoutubeVideo>(1);
		}
	}

	public void Req4 (String tag, int n)
	{
		TablaHash<String, ArregloDinamico<YoutubeVideo>> videosHash = new TablaHash<String, ArregloDinamico<YoutubeVideo>>();
		for (int i = 1; i <= videosDinamico.size(); i++)
		{
			YoutubeVideo actual = videosDinamico.getElement(i);
			String keyActual = null;
			if(actual.getTags().contains(tag)==true)
			{
				keyActual = tag;
			}
			else
			{
				keyActual = actual.getTags();
			}
			if (!videosHash.contains(keyActual))
			{
				ArregloDinamico <YoutubeVideo> videoValue = new ArregloDinamico <YoutubeVideo> (2);
				videoValue.addLast(actual);
				//System.out.println(videoValue.getElement(1).getTitle());
				videosHash.put(keyActual, videoValue);
				//System.out.println(keyActual+"1VIDEO:"+videosHash.get(keyActual).getElement(1).getTitle());
			}
			else
			{
				ArregloDinamico <YoutubeVideo> videoValue = videosHash.get(keyActual);
				videoValue.addLast(actual);
				//System.out.println(actual.getTitle());
				//videosHash.put(keyActual, videoValue);
			}
		}
		
		String wantedKey = tag;
		if(videosHash.contains(wantedKey)==true)
		{
		ArregloDinamico <YoutubeVideo> valorHash = videosHash.get(wantedKey);
		ordenarListaLikes (valorHash, false);
		System.out.println("los "+n+" videos con mas likes con el tag "+tag+" son:");
		System.out.println("-------------------------------------------------");
		for(int i = 1; i <= n; i++)
		{
			System.out.println("titulo: "+valorHash.getElement(i).getChannelTitle());
			System.out.println("titulo del canal: "+valorHash.getElement(i).getChannelTitle());
			System.out.println("tiempo de publicacion: "+valorHash.getElement(i).getPublishTime());
			System.out.println("views: "+valorHash.getElement(i).getViews());
			System.out.println("likes: "+valorHash.getElement(i).getLikes());
			System.out.println("dislikes: "+valorHash.getElement(i).getDislikes());
			System.out.println("tags: "+valorHash.getElement(i).getTags());
			System.out.println("-------------------------------------------------");
		}
		}
		else
		{
			ArregloDinamico <YoutubeVideo> vacia =new ArregloDinamico <YoutubeVideo>(1);
		}
	}

	public String buscarCategoryID (String categoryName)
	{
		boolean buscado = false;
		String IDbuscado = "";
		for (int i = 1 ; i <= darCategoriasVideos().size() && !buscado; i++)
		{
			Category actual = darCategoriasVideos().getElement(i);
			if(actual.getCategoryName().equalsIgnoreCase(categoryName))
			{
				IDbuscado = actual.getCategoryID();
				buscado = true;
			}
		}

		return IDbuscado;
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