package org.projectsrk.common;

public class Clothes {
	
	public Clothes() {		
	}
	public Clothes(String _clothesID, String _castMemberID, String _sceneID,
			String _description, String _outsideIdentifier, String _imgUrl, String _link, String _source, String _searchTerms) {
		super();
		this._clothesID = _clothesID;
		this._castMemberID = _castMemberID;
		this._sceneID = _sceneID;
		this._description = _description;
		this._outsideIdentifier = _outsideIdentifier;
		this._imgUrl = _imgUrl;
		this._link = _link;
		this._source = _source;
		this._searchTerms = _searchTerms;
	}
	private String _clothesID;
	private String _castMemberID; // foreign key to character
	private String _sceneID; // foreign key to scene
	private String _description;
	private String _outsideIdentifier; // for now, only use Amazon and assume we are given unique identifier for product
	private String _source;
	private String _imgUrl;
	private String _link;
	
	public String get_imgUrl() {
		return _imgUrl;
	}
	public String get_link() {
		return _link;
	}
	public String get_source() {
		return _source;
	}
	public String get_searchTerms() {
		return _searchTerms;
	}
	private String _searchTerms;
	
	public String get_clothesID() {
		return _clothesID;
	}
	public String get_castMemberID() {
		return _castMemberID;
	}
	public String get_sceneID() {
		return _sceneID;
	}
	public String get_description() {
		return _description;
	}
	public String get_outsideIdentifier() {
		return _outsideIdentifier;
	}
	public void set_clothesID(String _clothesID) {
		this._clothesID = _clothesID;
	}
	public void set_castMemberID(String _castMemberID) {
		this._castMemberID = _castMemberID;
	}
	public void set_sceneID(String _sceneID) {
		this._sceneID = _sceneID;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public void set_outsideIdentifier(String _outsideIdentifier) {
		this._outsideIdentifier = _outsideIdentifier;
	}
	public void set_source(String _source) {
		this._source = _source;
	}
	public void set_imgUrl(String _imgUrl) {
		this._imgUrl = _imgUrl;
	}
	public void set_link(String _link) {
		this._link = _link;
	}
	public void set_searchTerms(String _searchTerms) {
		this._searchTerms = _searchTerms;
	}
}
