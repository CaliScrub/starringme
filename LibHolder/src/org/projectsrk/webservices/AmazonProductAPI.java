package org.projectsrk.webservices;

import com.ECS.client.jax.*;
import javax.xml.namespace.QName;

public class AmazonProductAPI {
	
	private String AWS_Key = "AKIAIJ7UED5LLQLVOQVQ";
	private String AWS_Private_Key = "dnYeBiXwbi0FMrxluA5K6wy447XfAqtA2tbgDkfa";
	private String AssociateTag = "FakeTag";
	private AWSECommerceService AmazonService;
	private AWSECommerceServicePortType AmazonServicePort;
	
	
	public AmazonProductAPI() {
		// Set the service:
		AmazonService = new AWSECommerceService(); 
		AmazonService.setHandlerResolver(new com.ECS.client.jax.AWSHandlerResolver(AWS_Private_Key));  

		//Set the service port:
		AmazonServicePort = AmazonService.getAWSECommerceServicePort();		
	}
	
	public ItemSearchResponse Search(String searchIndexType, String searchWords) {
		//Get the operation object:
		ItemSearchRequest itemRequest = new ItemSearchRequest();

		//Fill in the request object:
		itemRequest.setSearchIndex(searchIndexType);
		itemRequest.setKeywords(searchWords);
		ItemSearch ItemElement= new ItemSearch();
		ItemElement.setAssociateTag(AssociateTag);
		ItemElement.setAWSAccessKeyId(AWS_Key);
		ItemElement.getRequest().add(itemRequest);

		//Call the Web service operation and store the response
		//in the response object:
		ItemSearchResponse response = AmazonServicePort.itemSearch(ItemElement);
		
		return response;
	}
	
	public ItemLookupResponse GetItem(String ASIN) {
		ItemLookupRequest itemRequest = new ItemLookupRequest();
		
		itemRequest.setIdType("ASIN");
		itemRequest.getItemId().add(ASIN);
		
		ItemLookup ItemElement = new ItemLookup();
		ItemElement.setAssociateTag(AssociateTag);
		ItemElement.setAWSAccessKeyId(AWS_Key);
		ItemElement.getRequest().add(itemRequest);
		
		//Call the Web service operation and store the response
		//in the response object:
		ItemLookupResponse response = AmazonServicePort.itemLookup(ItemElement);
		
		return response;
	}
	
	public SimilarityLookupResponse GetSimilarItems(String ASIN) {
		SimilarityLookupRequest itemRequest = new SimilarityLookupRequest();
		
		itemRequest.getItemId().add(ASIN);
		
		SimilarityLookup ItemElement = new SimilarityLookup();
		ItemElement.setAssociateTag(AssociateTag);
		ItemElement.setAWSAccessKeyId(AWS_Key);
		ItemElement.getRequest().add(itemRequest);
		
		//Call the Web service operation and store the response
		//in the response object:
		SimilarityLookupResponse response = AmazonServicePort.similarityLookup(ItemElement);
		
		return response;	
	}
}
