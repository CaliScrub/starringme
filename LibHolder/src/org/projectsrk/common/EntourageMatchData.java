package org.projectsrk.common;

public class EntourageMatchData {

	private String _seriesName;
	private int _seasonNumber;
	private int _seasonEpisodeNumber;
	private int _seriesEpisodeNumber;
	private int _adjustedMilliseconds;
	private String _gnTui;
	
	public int get_seasonEpisodeNumber() {
		return _seasonEpisodeNumber;
	}
	public void set_seasonEpisodeNumber(int _seasonEpisodeNumber) {
		this._seasonEpisodeNumber = _seasonEpisodeNumber;
	}
	public int get_seriesEpisodeNumber() {
		return _seriesEpisodeNumber;
	}
	public void set_seriesEpisodeNumber(int _seriesEpisodeNumber) {
		this._seriesEpisodeNumber = _seriesEpisodeNumber;
	}
	public String get_gnTui() {
		return _gnTui;
	}
	public void set_gnTui(String _gnTui) {
		this._gnTui = _gnTui;
	}
	public String get_seriesName() {
		return _seriesName;
	}
	public void set_seriesName(String _seriesName) {
		this._seriesName = _seriesName;
	}
	public int get_seasonNumber() {
		return _seasonNumber;
	}
	public void set_seasonNumber(int _seasonNumber) {
		this._seasonNumber = _seasonNumber;
	}
	public int get_adjustedMilliseconds() {
		return _adjustedMilliseconds;
	}
	public void set_adjustedMilliseconds(int _adjustedMilliseconds) {
		this._adjustedMilliseconds = _adjustedMilliseconds;
	}
}
