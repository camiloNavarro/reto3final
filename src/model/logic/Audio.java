package model.logic;

import java.util.Comparator;

public class Audio implements Comparable<Audio>{
	
	private String audioID;
	private int instrumentalness;
	private int liveness;
	private	int speechiness;
	private int danceability;
	private int valence;
	private int loudness;
	private int tempo;
	private int acousticness;
	private int energy;
	private String artist_id;
	
	public Audio(String pAudioID, int ins, int liv,int spe, int dan, int val, int loud, int tem, int aco, int ene, String art)
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
	}
	
	public String getAudioID()
	{
		return audioID;
	}
	
	public int getInstrumentalness()
	{
		return instrumentalness;
	}
	
	public int getILivness()
	{
		return liveness;
	}
	
	public int getSpeechiness()
	{
		return speechiness;
	}
	
	public int getDanceability()
	{
		return danceability;
	}
	
	public int getValence()
	{
		return valence;
	}
	
	public int getLoudness()
	{
		return loudness;
	}
	
	public int getTempo()
	{
		return tempo;
	}
	
	public int getAcoustince()
	{
		return acousticness;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public String getArtistiID()
	{
		return artist_id;
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
			return a1.getInstrumentalness()-a2.getInstrumentalness();
		}

	}
	
	public static class ComparadorXlivness implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getILivness()-a2.getILivness();
		}

	}
	
	public static class ComparadorXSpeech implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getSpeechiness()-a2.getSpeechiness();
		}

	}
	
	public static class ComparadorXDance implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getDanceability()-a2.getDanceability();
		}

	}
	
	public static class ComparadorXValence implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getValence()-a2.getValence();
		}

	}
	
	public static class ComparadorXLoud implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getLoudness()-a2.getLoudness();
		}

	}
	
	public static class ComparadorXTempo implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getTempo()-a2.getTempo();
		}

	}
	
	public static class ComparadorXAcouns implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getAcoustince()-a2.getAcoustince();
		}

	}

	public static class ComparadorXEnergy implements Comparator<Audio> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(Audio a1, Audio a2) 
		{
			return a1.getEnergy()-a2.getEnergy();
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
