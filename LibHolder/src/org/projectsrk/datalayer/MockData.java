package org.projectsrk.datalayer;

import java.util.*;

import org.projectsrk.common.*;

public class MockData implements IStuff {

	private List<Series> seriesList;
	private List<Episode> episodeList;
	private List<Scene> sceneList;
	private List<CastMember> castMemberList;
	private List<Clothes> clothesList;
	
	public MockData() {
		seriesList = new ArrayList<Series>();
		seriesList.add(new Series("1", "The Simpsons", "http://images3.wikia.nocookie.net/__cb20130423174553/simpsons/images/2/2a/Homerj.simpson.jpg", "1"));
		seriesList.add(new Series("2", "Family Guy", "http://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Peter_Griffin.png/220px-Peter_Griffin.png", "2"));
		seriesList.add(new Series("3", "The Big Bang Theory", "http://upload.wikimedia.org/wikipedia/en/1/1b/BigBangTheoryTitleCard.png", "238006978"));
		seriesList.add(new Series("4", "Dummy data", "http://upload.wikimedia.org/wikipedia/en/thumb/4/44/Question_mark_%28black_on_white%29.png/475px-Question_mark_%28black_on_white%29.png", "4"));
		seriesList.add(new Series("5", "Here we go", "http://upload.wikimedia.org/wikipedia/en/thumb/4/44/Question_mark_%28black_on_white%29.png/475px-Question_mark_%28black_on_white%29.png", "5"));
		seriesList.add(new Series("6", "OK", "http://upload.wikimedia.org/wikipedia/en/thumb/4/44/Question_mark_%28black_on_white%29.png/475px-Question_mark_%28black_on_white%29.png", "6"));
		seriesList.add(new Series("7", "Test", "http://upload.wikimedia.org/wikipedia/en/thumb/4/44/Question_mark_%28black_on_white%29.png/475px-Question_mark_%28black_on_white%29.png", "7"));
		seriesList.add(new Series("8", "#8", "http://upload.wikimedia.org/wikipedia/en/thumb/4/44/Question_mark_%28black_on_white%29.png/475px-Question_mark_%28black_on_white%29.png", "8"));
		
		episodeList = new ArrayList<Episode>();
		//episodeList.add(new Episode(1, 1, 1, "The Bart, The", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(2, 1, 2, "Homerpalooza", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(3, 1, 3, "Lisa Needs Braces", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(4, 2, 1, "First ep", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(5, 2, 2, "Second ep", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(6, 2, 3, "Third ep", "Nothing of consequence happens here"));
		//episodeList.add(new Episode(7, 1, 4, "Fourth ep - Out of order", "Nothing of consequence happens here"));
		episodeList.add(new Episode("6", "3", 3, 6, 46, "The Cornhusker Vortex", "Leonard learns football"));

		sceneList = new ArrayList<Scene>();
		
		//sceneList.add(new Scene(1, 1, 1, 50, "Intro"));
		//sceneList.add(new Scene(2, 1, 2, 50, "First Act"));
		//sceneList.add(new Scene(3, 1, 3, 40, "Second Act"));
		sceneList.add(new Scene("1", "6", 1, 1, 5, "First Scene"));
		sceneList.add(new Scene("2", "6", 2, 5, 11, "Second Scene"));
		sceneList.add(new Scene("3", "6", 3, 11, 15, "Third Scene"));
		sceneList.add(new Scene("4", "6", 4, 15, 20, "Fourth Scene"));
		sceneList.add(new Scene("5", "6", 5, 20, 25, "Fifth Scene"));
		sceneList.add(new Scene("6", "6", 6, 25, 30, "Sixth Scene"));
	
		
		castMemberList = new ArrayList<CastMember>();
		
		//castMemberList.add(new CastMember(1, 1, "Homer Simpson", "", "Head of the household, works in Springfield Nuclear Power Plant"));
		//castMemberList.add(new CastMember(2, 1, "Bart Simpson", "", "Son of Homer, rides a skateboard"));
		CastMember cm = new CastMember("1", "3", "Penny", "Penny Actress", "Girl across the hall");
		cm.set_imgUrl("http://4.bp.blogspot.com/-KgiUb-PsVCI/T05us5SkX8I/AAAAAAAABL8/PeJIlw78GOU/s1600/Kaley_Cuoco.jpg");
		castMemberList.add(cm);
		
		cm = new CastMember("2", "3", "Leonard", "Leonard Acress", "Main CalTech Scientist");
		cm.set_imgUrl("http://www.gcmarketingservices.com/wp-content/uploads/2012/04/JohnnyGalecki-sm.png");
		castMemberList.add(cm);
		
				
		clothesList = new ArrayList<Clothes>();
		
		//clothesList.add(new Clothes(1, 1, 1, "White polo shirt", "3454672354636288272", "http://dimg.dillards.com/is/image/DillardsZoom/03750407_zi_white", "http://www.dillards.com/product/Calvin-Klein-Liquid-Cotton-Polo-Shirt_301_-1_301_503215078", "Google Shopping", "white+polo+shirt"));
//		Penny's Clothes
		clothesList.add(new Clothes("1", "1", "2", 
				"Grey Nebraska T-shirt", 
				"[Outside ID]", 
				"http://c-product.images.fansedge.com/52-24/52-24799-F.jpg", 
				"http://www.fansedge.com/Nebraska-Cornhuskers-Dark-Heather-Perennial-II-T-Shirt-_1123835081_PD.html", 
				"Google Shopping", 
				"grey+nebraska+shirt"));
		clothesList.add(new Clothes("2", "1", "2", 
				"Red Running Shorts", 
				"[Outside ID]", 
				"http://i.ebayimg.com/12/!CCK1W(wCGk~$(KGrHqYOKiwEzVfe9p-9BNKRtquoTg~~_35.JPG", 
				"http://www.viciousstyle.com/vintage-retro-shorts-many-color-combos-p-533.html/221145120938?pt=US_CSA_WC_Shorts&var=520119385146&hash=item337d46c0aa", 
				"Google Shopping", 
				"red+running+shorts"));
		clothesList.add(new Clothes("3", "1", "2", 
				"Red and White Striped Tube Socks", 
				"[Outside ID]", 
				"http://thumbs1.ebaystatic.com/d/l225/m/m4qOAWY6wcbNcp-lpsk7f_Q.jpg", 
				"http://www.ebay.co.uk/itm/Unisex-Sports-Socks-Stripe-Knee-High-Tube-GYM-Running-Soccer-Stockings-Color-Red-/290935687814", 
				"Google Shopping", 
				"red+white+striped+tube+socks"));
		clothesList.add(new Clothes("4", "1", "2", 
				"Converse Low Top Shoes", 
				"[Outside ID]", 
				"http://cdn2.iofferphoto.com/img/item/187/078/724/converse-chuck-taylor-all-star-low-top-shoes-black-0053f.jpg", 
				"http://www.conversehightopsneakersoutlet.com/classic-converse-chuck-taylor-all-star-low-top-optical-black-canvas-sneakers-p-89.html", 
				"Google Shopping", 
				"converse+low+top+shoes"));
		
		//		Leonard's Clothes
		clothesList.add(new Clothes("5", "2", "2", 
				"Blue T-Shirt", 
				"[Outside ID]", 
				"http://img0.etsystatic.com/005/0/6048599/il_fullxfull.362509224_bvj8.jpg", 
				"http://www.etsy.com/listing/92604491/50-off-l-purple-mens-tshirt-with", 
				"Google Shopping", 
				"blue+t+shirt"));
		clothesList.add(new Clothes("6", "2", "2", 
				"Purple Zip-down Hooded Sweatshirt", 
				"[Outside ID]", 
				"http://www.wigglestatic.com/product-media/5360080559/oakley-pennycross-2.0-hoodie-full-zip-sweatshirt-royal-purple-front-2013.jpg", 
				"http://www.wiggle.co.uk/oakley-pennycross-20-full-zip-sweatshirt/", 
				"Google Shopping", 
				"purple+zip+down+hooded+sweatshirt"));
		clothesList.add(new Clothes("7", "2", "2", 
				"Stonewashed Jeans", 
				"[Outside ID]", 
				"http://ecx.images-amazon.com/images/I/91Jugbj1KOL._SL1500_.jpg", 
				"http://www.amazon.com/Levis-Mens-Slim-Straight-Jean/dp/B0018OQ93Y", 
				"Google Shopping", 
				"stonewashed+jeans"));
		clothesList.add(new Clothes("8", "2", "2", 
				"Converse All-star Low Top Shoes", 
				"[Outside ID]", 
				"http://cdn2.iofferphoto.com/img/item/187/078/724/converse-chuck-taylor-all-star-low-top-shoes-black-0053f.jpg", 
				"http://www.conversehightopsneakersoutlet.com/classic-converse-chuck-taylor-all-star-low-top-optical-black-canvas-sneakers-p-89.html", 
				"Google Shopping", 
				"converse+low+top+shoes"));
		clothesList.add(new Clothes("9", "2", "2", 
				"Khaki Jacket with Pockets", 
				"[Outside ID]", 
				"http://www.bananarepublic.com/products/res/mainimg/khaki-cotton-four-pocket-jacket.jpg", 
				"http://bananarepublic.gap.com/browse/product.do?pid=409954&locale=en_US&kwid=1&sem=false&sdReferer=http%3A%2F%2Fwww.bananarepublic.com%2Fproducts%2Fmens-outerwear.jsp&scid=409954002&vid=0", 
				"Google Shopping", 
				"khaki+jacket"));
		
		clothesList.add(new Clothes("10", "1", "5", 
				"Converse Low Top Shoes", 
				"[Outside ID]", 
				"http://cdn2.iofferphoto.com/img/item/187/078/724/converse-chuck-taylor-all-star-low-top-shoes-black-0053f.jpg", 
				"http://www.conversehightopsneakersoutlet.com/classic-converse-chuck-taylor-all-star-low-top-optical-black-canvas-sneakers-p-89.html", 
				"Google Shopping", 
				"converse+low+top+shoes"));
		clothesList.add(new Clothes("11", "2", "5", 
				"Khaki Jacket with Pockets", 
				"[Outside ID]", 
				"http://www.bananarepublic.com/products/res/mainimg/khaki-cotton-four-pocket-jacket.jpg", 
				"http://bananarepublic.gap.com/browse/product.do?pid=409954&locale=en_US&kwid=1&sem=false&sdReferer=http%3A%2F%2Fwww.bananarepublic.com%2Fproducts%2Fmens-outerwear.jsp&scid=409954002&vid=0", 
				"Google Shopping", 
				"khaki+jackets"));
	}
	
	public List<Series> GetSerii() {
		return seriesList;
	}
	
	public Series GetSeriesBySeriesName(String seriesName) {
		for (Series s : seriesList) {
			if (s.get_name().equals(seriesName)) {
				return s;
			}
		}
		
		return null;
	}
	
	public Series GetSeriesByGnTui(String gnTui) {
		for (Series s : seriesList) {
			if (s.get_gnTui().equals(gnTui)) {
				return s;
			}
		}
		
		return null;
	}
	
	public List<Episode> GetEpisodes(Series mySer) {
		return GetEpisodes(mySer.get_seriesID());
	}
	
	public List<Episode> GetEpisodes(String seriesID) {
		List<Episode> eList = new ArrayList<Episode>();		
		for (Episode e : episodeList) {
			if (e.get_seriesID().equals(seriesID)) {
				eList.add(e);
			}
		}
		return eList;
	}
	
	public Episode GetEpisode(String seriesID, int seasonNumber, int seasonEpisode) {		
		for (Episode e : episodeList) {
			if (e.get_seriesID().equals(seriesID)
					&& e.get_seasonNumber() == seasonNumber
					&& e.get_seasonEpisodeNumber() == seasonEpisode) {
				return e; 
			}
		}
		return null;
	}
	
	public List<Scene> GetScenes(Episode myEp) {
		return GetScenes(myEp.get_episodeID());
	}
	
	public List<Scene> GetScenes(String episodeID) {
		List<Scene> sList = new ArrayList<Scene>();
		for (Scene s: sceneList) {
			if (s.get_episodeID().equals(episodeID)) {
				sList.add(s);
			}
		}
		return sList;
	}
	
	public boolean IsMatchWithinScene(int matchMilli, Scene sc) {
		return ((matchMilli > (1000 * 60 * sc.get_sceneStart()) &&
				matchMilli < (1000 * 60 * sc.get_sceneEnd())));
	}
	
	public Scene GetScene(EntourageMatchData match) {
		Series s = GetSeriesByGnTui(match.get_gnTui());
		
		if (s == null) {
			s = GetSeriesBySeriesName(match.get_seriesName());
		}
		
		if (s != null) {
			Episode e = GetEpisode(s.get_seriesID(), match.get_seasonNumber(), match.get_seasonEpisodeNumber());
			if (e != null) {
				List<Scene> sceneList = GetScenes(e.get_episodeID());
				for (Scene sc: sceneList) {
					if (IsMatchWithinScene(match.get_adjustedMilliseconds(), sc)) {
						return sc;
					}
				}
			}
		}
		return null;
	}
	
	public List<CastMember> GetCastMembers(String sceneID) {
		List<CastMember> cmList = new ArrayList<CastMember>();
		for (Clothes c: clothesList) {
			if (c.get_sceneID().equals(sceneID)) {
				for (CastMember cm: castMemberList) {
					if (cm.get_castmemberID().equals(c.get_castMemberID())) {
						if (!cmList.contains(cm)) {
							cmList.add(cm);
						}
					}
				}
			}
		}
		return cmList;
	}
	
	public List<Clothes> GetClothes (String castMemberID, String sceneID) {
		List<Clothes> cList = new ArrayList<Clothes>();
		for (Clothes c: clothesList) {
			if (c.get_sceneID().equals(sceneID) && c.get_castMemberID().equals(castMemberID)) {
				cList.add(c);
			}
		}
		return cList;
	}
	
}
