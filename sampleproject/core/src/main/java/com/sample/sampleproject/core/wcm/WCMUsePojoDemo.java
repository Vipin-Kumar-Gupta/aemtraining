package com.sample.sampleproject.core.wcm;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class WCMUsePojoDemo extends WCMUsePojo {
	
	private String upperText;
	private String otherComponentProperty;
	private String otherComponentPropertyJCR;
	private String currentPageNavTitle;
	private String differentPageProperty;
	
	private Map<String,Object> currentResourceProperties = new HashMap<String,Object>();

	
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		upperText = getProperties().get("text").toString().toUpperCase();
		
		
		//Fetch all the properties of current component.
		ValueMap properties = getProperties();
		for(Entry<String, Object> entry : properties.entrySet()){
			currentResourceProperties.put(entry.getKey(), entry.getValue());
		}
		
		callOtherComponentProperties();
		callOtherComponentPropertiesJCR();
		
		currentPageNavTitle = getCurrentPage().getNavigationTitle().toString();
		
		//Access different page properties.
		
		ResourceResolver resourceResolver = getResourceResolver();
		Resource resource = resourceResolver.getResource("/content/samplewebsite/product");	
		Page page = resource.adaptTo(Page.class);
		// page == getCurrentPage()
		differentPageProperty = page.getTitle();		 
	}
	

	private void callOtherComponentProperties() {
		// /content/samplewebsite/home/jcr:content/body/samplebannercomp		
		ResourceResolver resourceResolver = getResourceResolver();
		Resource resource = resourceResolver.getResource("/content/samplewebsite/home/jcr:content/body/samplebannercomp");		
		//ValueMap properties = resource.adaptTo(ValueMap.class); // Try it.
		otherComponentProperty = resource.getValueMap().get("description", String.class);			
	}

	private void callOtherComponentPropertiesJCR() throws PathNotFoundException, RepositoryException {
		Session session = getResourceResolver().adaptTo(Session.class);	
		Node node = session.getNode("/content/samplewebsite/home/jcr:content/body/samplebannercomp");	
		otherComponentPropertyJCR = node.getProperty("description").getString();
	}
	
	public String getUpperText() {
		return upperText;
	}

	public Map<String, Object> getCurrentResourceProperties() {
		return currentResourceProperties;
	}
	public String getOtherComponentProperty() {
		return otherComponentProperty;
	}
	
	public String getCurrentPageNavTitle() {
		return currentPageNavTitle;
	}
	public String getDifferentPageProperty() {
		return differentPageProperty;
	}
	public String getOtherComponentPropertyJCR() {
		return otherComponentPropertyJCR;
	}

}
