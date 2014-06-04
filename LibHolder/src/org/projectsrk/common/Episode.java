package org.projectsrk.common;

public class Episode {
	public Episode() {		
	}
	public Episode(String _episodeID, String _seriesID, int _seasonNumber, int _seasonEpisodeNumber,
				int _seriesEpisodeNumber, String _name, String _description) {
		super();
		this._episodeID = _episodeID;
		this._seriesID = _seriesID;
		this._seriesEpisodeNumber = _seriesEpisodeNumber;
		this._seasonNumber = _seasonNumber;
		this._seasonEpisodeNumber = _seasonEpisodeNumber;
		this._name = _name;
		this._description = _description;
	}
	private String _episodeID; // primary key
	public void set_episodeID(String _episodeID) {
		this._episodeID = _episodeID;
	}
	public void set_seriesID(String _seriesID) {
		this._seriesID = _seriesID;
	}
	public void set_seriesEpisodeNumber(int _seriesEpisodeNumber) {
		this._seriesEpisodeNumber = _seriesEpisodeNumber;
	}
	public void set_seasonNumber(int _seasonNumber) {
		this._seasonNumber = _seasonNumber;
	}
	public void set_seasonEpisodeNumber(int _seasonEpisodeNumber) {
		this._seasonEpisodeNumber = _seasonEpisodeNumber;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	private String _seriesID; // foreign key to series
	private int _seriesEpisodeNumber; // actual episode # relative to the series
	private int _seasonNumber;
	private int _seasonEpisodeNumber;
	private String _name;
	private String _description;

	public String get_episodeID() {
		return _episodeID;
	}
	public String get_seriesID() {
		return _seriesID;
	}
	public int get_seriesEpisodeNumber() {
		return _seriesEpisodeNumber;
	}
	public String get_name() {
		return _name;
	}
	public String get_description() {
		return _description;
	}
	public int get_seasonNumber() {
		return _seasonNumber;
	}
	public int get_seasonEpisodeNumber() {
		return _seasonEpisodeNumber;
	}

}
