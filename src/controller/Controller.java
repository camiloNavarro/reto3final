package controller;

import java.util.Scanner;

import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.YoutubeVideo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){

			//Menu para escoger la representacion
			view.printMenuCarga();
			int optionCarga = lector.nextInt();
			switch (optionCarga) {
			case 1:
				view.printMessage("Cargando en tabla hash ");
				modelo.cargarVideosHash(); 
				modelo.cargarCategorias();
				int sizeHash = modelo.getVideosHash().size();
				view.printMessage("Cantidad de registros de videos cargados del archivo: " + sizeHash);
				int sizeCategories = modelo.getVideosCategories().size();
				view.printMessage("Cantidad de registros de videos cargados del archivo: " + sizeCategories);
				//modelo.getInfoFirst();
				view.printMessage("Lista de las categorias: ");
//				for (int i = 1; i <= modelo.darCategoriasVideos().size(); i++)
//				{
//					String categoryID = modelo.darCategoriasVideos().getElement(i).getCategoryID();
//					String categoryName = modelo.darCategoriasVideos().getElement(i).getCategoryName();
//
//					view.printMessage(categoryID + "|||" + categoryName);
//				}

				view.printMenuRequerimientos();
				int optionReq = lector.nextInt();
				switch (optionReq){
				case 1:
					view.printMessage("Sobre cual categoria desea aplicar el requerimiento?");
					String categoryNameR1 = lector.next();
					view.printMessage("Sobre cual pais desea aplicar el requerimiento?");
					String countryR1 = lector.next();
					view.printMessage("Cuantos videos con el mayor numero de reproducciones desea tener?");
					int numVideos = lector.nextInt();
					ILista<YoutubeVideo> subListCategoryCountry = modelo.Req1(categoryNameR1, countryR1);
					view.printMessage("Los " +numVideos+ " videos tendencia en "+countryR1+ " con mas views de la categoria "+categoryNameR1+" son:");
					for (int i = 1; i <= numVideos; i++)
					{
						view.printMessage("-----------------------------------------" );
						view.printMessage("titulo: " +subListCategoryCountry.getElement(i).getTitle());
						view.printMessage("canal: " +subListCategoryCountry.getElement(i).getTrendingDate());
						view.printMessage("publish time: " +subListCategoryCountry.getElement(i).getChannelTitle());
						view.printMessage("views: " +subListCategoryCountry.getElement(i).getPublishTime());
						view.printMessage("likes: " +subListCategoryCountry.getElement(i).getViews());
						view.printMessage("dislikes: " +subListCategoryCountry.getElement(i).getLikes());
						view.printMessage("tags: " +subListCategoryCountry.getElement(i).getDislikes());
					}
					break;
				case 2:
					view.printMessage("Sobre cual pais desea aplicar el requerimiento?");
					String countryR2 = lector.next();
					modelo.Req2(countryR2);
					break;
				case 3:
					view.printMessage("Sobre cual categoria desea aplicar el requerimiento?");
					String categoryNameR3 = lector.next();
					modelo.Req3(categoryNameR3);
					break;
				case 4:
					view.printMessage("Sobre cual pais desea aplicar el requerimiento?");
					String countryR4 = lector.next();
					view.printMessage("Sobre cual tag desea aplicar el requerimiento?");
					String tagR4 = lector.next();
					view.printMessage("Cuantos videos con el mayor numero de likes desea tener?");
					int numVideosR4 = lector.nextInt();
					ILista<YoutubeVideo> subListaPaisTag = modelo.Req4(countryR4, tagR4);
					view.printMessage("Los " +numVideosR4+ " videos de "+countryR4+ " con mas likes y con el tag  "+ tagR4 +" son:");
					for (int i = 1; i <= numVideosR4; i++)
					{
						view.printMessage("-----------------------------------------" );
						view.printMessage("titulo: " +subListaPaisTag.getElement(i).getTitle());
						view.printMessage("canal: " +subListaPaisTag.getElement(i).getChannelTitle());
						view.printMessage("publish time: " +subListaPaisTag.getElement(i).getPublishTime());
						view.printMessage("views: " +subListaPaisTag.getElement(i).getViews());
						view.printMessage("likes: " +subListaPaisTag.getElement(i).getLikes());
						view.printMessage("dislikes: " +subListaPaisTag.getElement(i).getDislikes());
						view.printMessage("tags: " +subListaPaisTag.getElement(i).getTags());
					}
					break;
				case 5:
					fin = true;
					break;
				}
			case 2: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;
			}
		}
	}	
}
