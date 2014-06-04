package org.projectsrk.common;

public class CastMember {
	public CastMember() {		
	}
	public void set_castmemberID(String _castmemberID) {
		this._castmemberID = _castmemberID;
	}
	public void set_seriesID(String _seriesID) {
		this._seriesID = _seriesID;
	}
	public void set_characterName(String _characterName) {
		this._characterName = _characterName;
	}
	public void set_actorName(String _actorName) {
		this._actorName = _actorName;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public CastMember(String _castmemberID, String _seriesID, String _characterName,
			String _actorName, String _description) {
		super();
		this._castmemberID = _castmemberID;
		this._seriesID = _seriesID;
		this._characterName = _characterName;
		this._actorName = _actorName;
		this._description = _description;
	}
	private String _castmemberID; // primary key
	private String _seriesID; // foreign key to series
	private String _characterName;
	private String _actorName;
	private String _description;
	private String _imgUrl;
	
	public String get_castmemberID() {
		return _castmemberID;
	}
	public String get_seriesID() {
		return _seriesID;
	}
	public String get_characterName() {
		return _characterName;
	}
	public String get_actorName() {
		return _actorName;
	}
	public String get_description() {
		return _description;
	}
	public String get_imgUrl() {
		return _imgUrl;
	}
	public void set_imgUrl(String _imgUrl) {
		this._imgUrl = _imgUrl;
	}
}
