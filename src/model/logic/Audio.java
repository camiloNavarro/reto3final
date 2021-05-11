package model.logic;

import java.util.Comparator;

public class Audio implements Comparable<Audio>{
	
	private String audioID;
	private float instrumentalness;
	private float liveness;
	private	float speechiness;
	private float danceability;
	private float valence;
	private float loudness;
	private float tempo;
	private float acousticness;
	private float energy;
	private String artist_id;
	private String hora;
	
	public Audio(String pAudioID, float ins, float liv,float spe, float dan, float val, float loud, float tem, float aco, float ene, String art, String hor)
	{
		audioID= pAudioID;
		instrumentalness = ins;
		liveness = liv;
		speechiness =spe;
		danceability = dan;
		valence = val;
		loudness = loud;
		tempo = tem;
		acousticness = aco;
		energy = ene;
		artist_id = art;
		hora =hor;
	}
	
	public String getAudioID()
	{
		return audioID;
	}
	
	public float getInstrumentalness()
	{
		return instrumentalness;
	}
	
	public float getILivness()
	{
		return liveness;
	}
	
	public float getSpeechiness()
	{
		return speechiness;
	}
	
	public float getDanceability()
	{
		return danceability;
	}
	
	public float getValence()
	{
		return valence;
	}
	
	public float getLoudness()
	{
		return loudness;
	}
	
	public float getTempo()
	{
		return tempo;
	}
	
	public float getAcoustince()
	{
		return acousticness;
	}
	
	public float getEnergy()
	{
		return energy;
	}
	
	public String getArtistiID()
	{
		return artist_id;
	}
	
	public String getHora()
	{
		return hora;
	}
	
	@Override
	public int compareTo(Audio o) {
		// TODO Auto-generated method stub
		return this.audioID.compareTo(o.audioID);
	}
	
	public static class ComparadorXistrumental implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getInstrumentalness()-a2.getInstrumentalness()>0)
				return 1;
			else if(a1.getInstrumentalness()-a2.getInstrumentalness()<0)
				return -1;
			else
				return 0;
				
		}

	}
	
	public static class ComparadorXlivness implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getILivness()-a2.getILivness()>0)
				return 1;
			else if (a1.getILivness()-a2.getILivness()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXSpeech implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getSpeechiness()-a2.getSpeechiness()>0)
				return 1;
			else if (a1.getSpeechiness()-a2.getSpeechiness()<0)
				return -1;
			else
				return 0;
			
		}

	}
	
	public static class ComparadorXDance implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getDanceability()-a2.getDanceability()>0)
				return 1;
			else if (a1.getDanceability()-a2.getDanceability()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXValence implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getValence()-a2.getValence()>0)
				return 1;
			else if (a1.getValence()-a2.getValence()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXLoud implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getLoudness()-a2.getLoudness()>0)
				return 1;
			else if (a1.getLoudness()-a2.getLoudness()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXTempo implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getTempo()-a2.getTempo()>0)
				return 1;
			else if (a1.getTempo()-a2.getTempo()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXAcouns implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getAcoustince()-a2.getAcoustince()>0)
				return 1;
			else if (a1.getAcoustince()-a2.getAcoustince()<0)
				return -1;
			else
				return 0;
		}

	}

	public static class ComparadorXEnergy implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			if(a1.getEnergy()-a2.getEnergy()>0)
				return 1;
			else if (a1.getEnergy()-a2.getEnergy()<0)
				return -1;
			else
				return 0;
		}

	}
	
	public static class ComparadorXArtista implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{

			return a1.getArtistiID().compareToIgnoreCase(a2.getArtistiID());
		}

	}
	
	public static class ComparadorXId implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getAudioID().compareToIgnoreCase(a2.getAudioID());
		}

	}
}
