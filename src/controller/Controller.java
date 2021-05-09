package controller;

import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.Queue;
import model.logic.Audio;
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
				view.printMessage("Cargando en arbol ");
				modelo.cargarVideosDinamico(); 

				view.printMenuRequerimientos();
				int optionReq = lector.nextInt();
				switch (optionReq){
				case 1:
					view.printMessage("Sobre cual categoria desea aplicar el requerimiento?");
					String categoryNameR1 = lector.next();
					view.printMessage("cuanto es el valor minimo?");
					float min = lector.nextFloat();
					view.printMessage("Cuanto es el valor maximo?");
					float max = lector.nextFloat();
					long timereq1= System.currentTimeMillis();
					view.printMessage("++++++ Req No. 1 results... ++++++");
                    Queue<Audio> res=modelo.req1(categoryNameR1, min, max);
                    view.printMessage(categoryNameR1+" is between "+min+" and "+max);
                    int cant =res.size();
                    ArregloDinamico<String>artistas=new ArregloDinamico<>(10);
                    Audio prueba=null;
                    while(res.size()!=0)
                    {
                    	prueba=res.dequeue();
                    	if(artistas.isPresent(prueba.getArtistiID())==-1){
                    		artistas.addLast(prueba.getArtistiID());
                    	}
                    }
                    view.printMessage("total of reproductions: "+cant+" total of unique artists: "+artistas.size());
                    
					
					break;
				case 2:
					view.printMessage("cual es el valor minimo de energy?");
					float min1 = lector.nextFloat();
					view.printMessage("cual es el valor maximo de energy?");
					float max1 = lector.nextFloat();
					view.printMessage("cual es el valor minimo de danceability?");
					float min2 = lector.nextFloat();
					view.printMessage("cual es el valor maximo de danceability?");
					float max2 = lector.nextFloat();
					//long timereq2= System.currentTimeMillis();
					ArregloDinamico<Audio> ans=modelo.req2(min1, min2, max1, max2);
					view.printMessage("++++++ Req No. 2 results... ++++++");
					view.printMessage("energy is between: "+min1+" and "+max1);
					view.printMessage("danceability is between: "+min2+" and "+max2);
					view.printMessage("total of unique traks: "+ans.size());
					view.printMessage("");
					view.printMessage("--- unique trak id --- ");
					for(int i =1;i<6;i++)
					{
						view.printMessage("track "+i+" : "+ans.getElement(i).getAudioID()+" with energy of "+ans.getElement(i).getEnergy()+" with danceability of "+ans.getElement(i).getDanceability());
					}
					
					break;
				case 3:
					view.printMessage("cual es el valor minimo de instrumentalness?");
					float min11 = lector.nextFloat();
					view.printMessage("cual es el valor maximo de instrumentalness?");
					float max11 = lector.nextFloat();
					view.printMessage("cual es el valor minimo de tempo?");
					float min22 = lector.nextFloat();
					view.printMessage("cual es el valor maximo de tempo?");
					float max22 = lector.nextFloat();
					//long timereq2= System.currentTimeMillis();
					ArregloDinamico<Audio> ans1=modelo.req3(min11, min22, max11, max22);
					view.printMessage("++++++ Req No. 2 results... ++++++");
					view.printMessage("instrumentalness is between: "+min11+" and "+max11);
					view.printMessage("tempo is between: "+min22+" and "+max22);
					view.printMessage("total of unique traks: "+ans1.size());
					view.printMessage("");
					view.printMessage("--- unique trak id --- ");
					for(int i =1;i<6;i++)
					{
						view.printMessage("track "+i+" : "+ans1.getElement(i).getAudioID());
					}
					break;
				case 4:
					ArregloDinamico<String> buscados=new ArregloDinamico<String>(10);
					view.printMessage("cual egenero desea consultar?");
					String primero = lector.next();
					buscados.addFirst(primero);
					//view.printMessage(buscados.getElement(1));
					String mensr="for reggae the tempo is between 60.0 and 90.0 BPM";
					String mensd="for down-tempo the tempo is between 70.0 and 100.0 BPM";
					String mensc="for chill-out the tempo is between 90.0 and 120.0 BPM";
					String mensh="for hip-hop the tempo is between 85.0 and 115.0 BPM";
					String mensj="for jazz and funk the tempo is between 120.0 and 125.0 BPM";
					String mensp="for pop the tempo is between 100.0 and 130.0 BPM";
					String mensrb="for R&B the tempo is between 60.0 and 80.0 BPM";
					String mensro="for rock the tempo is between 110.0 and 140.0 BPM";
					String mensm="for metal the tempo is between 100.0 and 160.0 BPM";
					boolean enc =true;
					while(enc ==true)
					{
						view.printMessage("desea consultar otro genero?");
						view.printMessage("1. si");
						view.printMessage("2. no");
						int optionReq1 = lector.nextInt();
						switch(optionReq1)
						{
						case 1:
							view.printMessage("cual egenero desea consultar?");
							String sig = lector.next();
							buscados.addLast(sig);
							break;
							
						case 2:
							enc = false;
							break;
						}
					}
					view.printMessage("++++++ Req No. 4 results... ++++++");
					int tot=modelo.req4num(buscados);
					view.printMessage("Total of reproductions: "+tot);
					for(int i=1;i<(buscados.size()+1);i++){
						view.printMessage("======== "+buscados.getElement(i)+" ========");
						if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensr);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensd);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensc);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensh);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensj);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensp);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensrb);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensro);
						else if(buscados.getElement(i).compareToIgnoreCase("reggae")==0)
							view.printMessage(mensm);
						else
							view.printMessage("no existe el genero");
						view.printMessage(buscados.getElement(i)+" reproductions: "+modelo.req4(buscados.getElement(i)).size());	
						view.printMessage("----- Distinct artist for "+buscados.getElement(i)+" ----- ");
						for(int j=1;j<11;j++){
							view.printMessage("artist "+j+" :"+modelo.req4(buscados.getElement(i)).getElement(j).getArtistiID());
						}
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