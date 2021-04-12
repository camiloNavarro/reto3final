package model.logic;

import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ITablaSimbolos;
import model.data_structures.NodeTH;
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
	private ITablaSimbolos <String, ArregloDinamico<YoutubeVideo>> videosHash;

	//	private ITablaSimbolos <String, ArregloDinamico<YoutubeVideo>> subListVideosHash;

	private ILista <Category> videosCategories;

	public static String CSV_SMALL = "./data/videos-small.csv";
	public static String CSV_ALL = "./data/videos-all.csv";
	public static String CSV_CATEGORIES = "./data/category-id.csv";


	public Modelo()
	{
		videosHash = new TablaHash<String, ArregloDinamico<YoutubeVideo>>();
		videosCategories = new ArregloDinamico<Category> (7);
		//		subListVideosHash = null;
	}

	public ITablaSimbolos <String, ArregloDinamico<YoutubeVideo>> getVideosHash ()
	{
		return videosHash;
	}

	//	public ITablaSimbolos <String, ArregloDinamico<YoutubeVideo>> getSubListVideosHash ()
	//	{
	//		return subListVideosHash;
	//	}

	public ILista<Category> getVideosCategories ()
	{
		return videosCategories;
	}

	//	public void getInfoFirst()
	//	{
	//		YoutubeVideo firstVideo = videosHash.getElement(1);
	//		
	//		System.out.println("El primer video cargado fue:");
	//		System.out.println("titulo: "+firstVideo.getTitle());
	//		System.out.println("canal: "+firstVideo.getChannelTitle());
	//		System.out.println("dias en tendencia: "+firstVideo.getTrendingDate());
	//		System.out.println("country: "+firstVideo.getCountry());
	//		System.out.println("views: "+firstVideo.getViews());
	//		System.out.println("likes: "+firstVideo.getLikes());
	//		System.out.println("dislikes: "+firstVideo.getDislikes());
	//	}

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
				getVideosCategories().addLast(nuevaCategoria);
			}
		}
		catch (Exception e)
		{

		}

	}

	public void cargarVideosHash() 
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

				YoutubeVideo newVideo =new YoutubeVideo(videoID, trendingDate, title,channelTitle,categoryID,publishTime,tags,views,likes,dislikes,commentCount,thumbnailLink,commentsDisabled,ratingsDisabled,videoErrorOrRemoved,description,country);
				String videoKey = country + "-" + getCategory(categoryID);
				if (!videosHash.contains(videoKey))
				{
					ArregloDinamico <YoutubeVideo> videoValue = new ArregloDinamico <YoutubeVideo> (2);
					videoValue.addFirst(newVideo);
					videosHash.put(videoKey, videoValue);
				}
				else
				{
					videosHash.get(videoKey).addLast(newVideo);
				}

			}
		}
		catch (Exception e)
		{

		}
	}

	public Category getCategory (String categoryID)
	{
		Category buscada = null;
		for (int i = 1; i <= videosCategories.size(); i++)
		{
			Category actual = videosCategories.getElement(i);
			if(actual.getCategoryID().compareTo(categoryID) == 0)
			{
				buscada = actual;
			}
		}
		return buscada;
	}

	//	public void getSubListaDinamico (int numElementos)
	//	{
	//		subListaVideosDinamico = videosHash.subLista(numElementos);
	//	}

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
		String wantedKey = country + "-" + categoryName;
		ordenarListaViews (videosHash.get(wantedKey), false);

		return videosHash.get(wantedKey);
	}

	public void Req2 (String country)
	{
		ILista<YoutubeVideo> subListCountry = subListByCountry(country);
		ordenarListaNombre(subListCountry, true);

		YoutubeVideo masRepetido = null;
		YoutubeVideo ultimoContado = null;
		int cuentaMaxima = 0;
		int ultimaCuenta = 0;
		for (int i = 1; i <= subListCountry.size(); i++) 
		{
			YoutubeVideo actual = subListCountry.getElement(i);
			if(ultimoContado == null)
			{
				ultimaCuenta++;
			}
			else if (actual.getTitle().compareTo(ultimoContado.getTitle()) == 0) 
			{
				ultimaCuenta++;
			} 
			else if (ultimaCuenta > cuentaMaxima) 
			{
				cuentaMaxima = ultimaCuenta;
				masRepetido = ultimoContado;
			}
			ultimoContado = actual;
		}
		System.out.println("El video con mas dias como tendencia en "+ country + " es:");
		System.out.println("titulo: "+masRepetido.getTitle());
		System.out.println("canal: "+masRepetido.getChannelTitle());
		System.out.println("country: "+masRepetido.getCountry());
		System.out.println("dias en tendencia: "+cuentaMaxima);
	}

	public void Req3 (String categoryName)
	{
		
	}

	public ILista<YoutubeVideo> Req4 (String country, String tag)
	{
		
	}

	public String buscarCategoryID (String categoryName)
	{
		boolean buscado = false;
		String IDbuscado = "";
		for (int i = 1 ; i <= getVideosCategories().size() && !buscado; i++)
		{
			Category actual = getVideosCategories().getElement(i);
			if(actual.getCategoryName().equalsIgnoreCase(categoryName))
			{
				IDbuscado = actual.getCategoryID();
				buscado = true;
			}
		}

		return IDbuscado;
	}

	public ILista<YoutubeVideo> subListByCountry (String country)
	{
		ILista<YoutubeVideo> subListCountry = new ArregloDinamico <YoutubeVideo>(1);

		for (int i = 1; i <= videosCategories.size(); i++)
		{
			String tempKey = country + "-" + videosCategories.getElement(i).getCategoryName();
			ArregloDinamico <YoutubeVideo> tempList = videosHash.get(tempKey);

			for (int j = 1; j <= tempList.size(); j++)
			{
				subListCountry.addLast(tempList.getElement(j));
			}
		}

		return subListCountry;
	}


	//	public ILista<YoutubeVideo> subListaPorPais (ILista<YoutubeVideo> lista, String country)
	//	{
	//		ILista <YoutubeVideo> subListaPais = new ArregloDinamico <YoutubeVideo>(1);
	//
	//		for (int i = 1 ; i <= lista.size(); i ++)
	//		{
	//			YoutubeVideo actual = lista.getElement(i);
	//			if(actual.getCountry().compareTo(country) == 0)
	//			{
	//				subListaPais.addLast(actual);
	//			}
	//
	//		}
	//		return subListaPais;
	//	}
	//
	//	public ILista<YoutubeVideo> subListaPorCategoria (ILista<YoutubeVideo> lista, String categoryID)
	//	{
	//		ILista <YoutubeVideo> subListaCategoria = new ArregloDinamico <YoutubeVideo>(1);
	//
	//		for (int i = 1 ; i <= lista.size(); i ++)
	//		{
	//			YoutubeVideo actual = lista.getElement(i);
	//			if(categoryID.compareTo("23") == 0 && (actual.getCategoryID().compareTo(categoryID)==0 || actual.getCategoryID().compareTo("34")==0) )
	//			{         
	//				subListaCategoria.addLast(actual);
	//			}
	//			else if(actual.getCategoryID().compareTo(categoryID)==0)
	//			{         
	//				subListaCategoria.addLast(actual);
	//			}
	//
	//		}
	//		return subListaCategoria;
	//	}
	//
	//	public ILista<YoutubeVideo> subListaPorPaisCategoria (ILista<YoutubeVideo> lista,String categoryID, String country )
	//	{
	//		ILista <YoutubeVideo> subListaPaisCategoria = new ArregloDinamico <YoutubeVideo>(1);
	//
	//		for (int i = 1 ; i <= lista.size(); i ++)
	//		{
	//			YoutubeVideo actual = lista.getElement(i);
	//			if((categoryID.compareTo("23") == 0 && (actual.getCategoryID().compareTo(categoryID)==0 || actual.getCategoryID().compareTo("34")==0)) && (actual.getCountry().compareTo(country) == 0) )
	//			{         
	//				subListaPaisCategoria.addLast(actual);
	//			}
	//			else if((actual.getCategoryID().compareTo(categoryID)==0) && (actual.getCountry().compareTo(country) == 0))
	//			{         
	//				subListaPaisCategoria.addLast(actual);
	//			}
	//
	//		}
	//		return subListaPaisCategoria;
	//	}
	//
	//	public ILista <YoutubeVideo> subListaPorPaisTag (ILista<YoutubeVideo> lista,String tag, String country )
	//	{
	//		ILista <YoutubeVideo> subListaPaisTag = new ArregloDinamico <YoutubeVideo>(1);
	//
	//		for (int i = 1 ; i <= lista.size(); i ++)
	//		{
	//			YoutubeVideo actual = lista.getElement(i);
	//			if(actual.getCountry().compareTo(country) == 0 && actual.getTags().contains(tag))
	//			{
	//				subListaPaisTag.addLast(actual);
	//			}
	//		}
	//		return subListaPaisTag;
	//	}
}
