package org.projectsrk.common;

public class Series {
	private String _seriesID;
	private String _name;
	private String _imageloc;
	private String _gnTui;
	
	
	public Series() {
	}

	public Series(String ID, String name, String imageloc, String tui) {
		_name = name;
		_seriesID = ID;
		_imageloc = imageloc;
		_gnTui = tui;
	}


	public String get_seriesID() {
		return _seriesID;
	}

	public String get_name() {
		return _name;
	}
	
	public String get_imageloc() {
		return _imageloc;
	}
	
	public String get_gnTui() {
		return _gnTui;
	}

	public void set_seriesID(String _seriesID) {
		this._seriesID = _seriesID;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_imageloc(String _imageloc) {
		this._imageloc = _imageloc;
	}

	public void set_gnTui(String _gnTui) {
		this._gnTui = _gnTui;
	}
}
