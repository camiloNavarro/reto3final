package model.logic;

import java.util.Comparator;

public class YoutubeVideo  implements Comparable<YoutubeVideo>{

	private String videoID;
	private String trendingDate;
	private String title;
	private	String channelTitle;
	private String categoryID;
	private String publishTime;
	private String tags;
	private int views;
	private int likes;
	private int dislikes;
	private int commentCount;
	private String thumbnailLink;
	private boolean commentsDisabled;
	private boolean ratingsDisabled;
	private boolean videoErrorOrRemoved;
	private String description;
	private String country;

	public YoutubeVideo(String pVideoID, String pTrendingDate, String pTitle, String pChannelTitle, String pCategoryID, String pPublishTime, String pTags, int pViews, int pLikes, int pDislikes, int pCommentCount, String pThumbnailLink, String pCommentsDisabled, String pRatingsDisabled, String pVideoErrorOrRemoved, String pDescription, String pCountry)
	{
		videoID= pVideoID;
		trendingDate=pTrendingDate;
		title= pTitle;
		channelTitle = pChannelTitle;
		categoryID = pCategoryID;
		publishTime = pPublishTime;
		tags = pTags;
		views = pViews;
		likes = pLikes;
		dislikes = pDislikes;
		commentCount = pCommentCount;
		thumbnailLink = pThumbnailLink;
		commentsDisabled = pCommentsDisabled.equalsIgnoreCase("true") ? true: false;
		ratingsDisabled = pRatingsDisabled.equalsIgnoreCase("true") ? true: false;
		videoErrorOrRemoved = pVideoErrorOrRemoved.equalsIgnoreCase("true") ? true: false;
		description = pDescription;
		country = pCountry;
	}
	
	public String getVideoID()
	{
		return videoID;
	}
	public String getTrendingDate()
	{
		return trendingDate;
	}
	public String getTitle()
	{
		return title;
	}
	public String getChannelTitle()
	{
		return channelTitle;
	}
	public String getCategoryID()
	{
		return categoryID;
	}
	public String getPublishTime()
	{
		return publishTime;
	}
	public String getTags()
	{
		return tags;
	}
	public int getViews()
	{
		return views;
	}
	public int getLikes()
	{
		return likes;
	}
	public int getDislikes()
	{
		return dislikes;
	}
	public int getCommentCount()
	{
		return commentCount;
	}
	public String getThumbnailLink()
	{
		return thumbnailLink;
	}
	public boolean getCommentsDisabled()
	{
		return commentsDisabled;
	}
	public boolean getRatingsDisabled()
	{
		return ratingsDisabled;
	}
	public boolean getVideoErrorOrRemoved()
	{
		return videoErrorOrRemoved;
	}
	public String getDescription()
	{
		return description;
	}
	public String getCountry()
	{
		return country;
	}
	
	public String[] getSeparatedTags()
	{
		String[] separatedTags = tags.split("\"\\|\"");
		return separatedTags;
	}

	/** Comparación natural de acuerdo a algún atributo con identificación única
	 * @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro.
	 * Numero positivo this es mayor a otro */
	public int compareTo(YoutubeVideo otro) 
	{
		return this.getTrendingDate().compareTo(otro.getTrendingDate()); 
	}

	/** Comparador alterno de 2 videos por número de likes*/

	public static class ComparadorXLikes implements Comparator<YoutubeVideo> {
		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 * valor negativo si video1 tiene menos likes que video2.
		 * valor positivo si video1 tiene más likes que video2. */
		public int compare(YoutubeVideo video1, YoutubeVideo video2) 
		{
			return video1.getLikes()-video2.getLikes();
		}
	}
	
	/** Comparador alterno de 2 videos por número de views*/
	
	public static class ComparadorXViews implements Comparator<YoutubeVideo> {
		/** Comparador alterno de acuerdo al número de views
		 * @return valor 0 si video1 y video2 tiene los mismos views.
		 * valor negativo si video1 tiene menos views que video2.
		 * valor positivo si video1 tiene más views que video2. */
		public int compare(YoutubeVideo video1, YoutubeVideo video2) 
		{
			return video1.getViews()-video2.getViews();
		}
	}
	
	/** Comparador alterno de 2 videos por titulo*/
	public static class ComparadorXTitulo implements Comparator<YoutubeVideo> {
		/** Comparador alterno de acuerdo al titulo
		 * @return valor 0 si video1 y video2 tiene el mismo titulo.
		 * valor negativo si el titulo del video1 es lexicograficamente menor que el del video2.
		 * valor positivo si el titulo del video1 es lexicograficamente mayor que el del video2. */
		public int compare(YoutubeVideo video1, YoutubeVideo video2) 
		{
			return video1.getTitle().compareTo(video2.getTitle());
		}
	}
}
