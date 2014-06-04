package org.projectsrk.common;
import java.util.*;

import org.projectsrk.common.*;

public interface IStuff {
	public List<Series> GetSerii();
	public Series GetSeriesBySeriesName(String seriesName);
	public Series GetSeriesByGnTui(String gnTui);
	public List<Episode> GetEpisodes(Series mySer);
	public List<Episode> GetEpisodes(String seriesID);
	public Episode GetEpisode(String seriesID, int seasonNumber, int seasonEpisode) ;
	public List<Scene> GetScenes(Episode myEp);
	public List<Scene> GetScenes(String episodeID);
	public Scene GetScene(EntourageMatchData match);
	public List<CastMember> GetCastMembers(String sceneID);
	public List<Clothes> GetClothes (String castMemberID, String sceneID);
}
