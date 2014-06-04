package org.projectsrk.common;

public class Scene {
	public Scene() {		
	}
	public Scene(String _sceneID, String _episodeID, int _sceneNumber,
			int _sceneStart, int _sceneEnd, String _sceneDescription) {
		super();
		this._sceneID = _sceneID;
		this._episodeID = _episodeID;
		this._sceneNumber = _sceneNumber;
		this._sceneStart = _sceneStart;
		this._sceneEnd = _sceneEnd;
		this._sceneDescription = _sceneDescription;
	}
	private String _sceneID; // primary key
	private String _episodeID; // foreign key to episode primary key
	private int _sceneNumber; // scene # relative to episode
	private int _sceneStart; // time stamp of scene start
	private int _sceneEnd; // time stamp of scene end
	private String _sceneDescription;
	
	public String get_sceneID() {
		return _sceneID;
	}
	public String get_episodeID() {
		return _episodeID;
	}
	public int get_sceneNumber() {
		return _sceneNumber;
	}
	public int get_sceneStart() {
		return _sceneStart;
	}
	public int get_sceneEnd() {
		return _sceneEnd;
	}
	public void set_sceneID(String _sceneID) {
		this._sceneID = _sceneID;
	}
	public void set_episodeID(String _episodeID) {
		this._episodeID = _episodeID;
	}
	public void set_sceneNumber(int _sceneNumber) {
		this._sceneNumber = _sceneNumber;
	}
	public void set_sceneStart(int _sceneStart) {
		this._sceneStart = _sceneStart;
	}
	public void set_sceneEnd(int _sceneEnd) {
		this._sceneEnd = _sceneEnd;
	}
	public void set_sceneDescription(String _sceneDescription) {
		this._sceneDescription = _sceneDescription;
	}
	public String get_sceneDescription() {
		return _sceneDescription;
	}	
	
}
