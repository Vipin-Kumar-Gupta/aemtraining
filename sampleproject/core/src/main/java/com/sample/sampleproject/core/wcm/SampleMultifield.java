package com.sample.sampleproject.core.wcm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.sample.sampleproject.core.bean.Items;
import com.sample.sampleproject.core.services.SampleFelixAddService;
import com.sample.sampleproject.core.services.SampleOSGIAddService;

public class SampleMultifield extends WCMUsePojo {
	
	private List<Items> multiItems = new ArrayList<Items>();
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Items> getMultiItems() {
		return multiItems;
	}
	
	private int totalValue;
	private int totalValue2;
	private String titleValue;	

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		prepareArrayList();
		
		//Call Addition service (Felix Based).
		SampleFelixAddService addService = getSlingScriptHelper().getService(SampleFelixAddService.class);
		totalValue = addService.additionValue();
		titleValue = addService.getPageTitle();
		
		
		//Call Addition service (OSGI Based).
		SampleOSGIAddService addOsgiService = getSlingScriptHelper().getService(SampleOSGIAddService.class);
		totalValue2 = addOsgiService.additionValue();			
		

	}
	
	private void prepareArrayList(){
		
		logger.error("This will print in log level - Error - Multifield");
		logger.debug("This will print in log level - Debug - Multifield");

		Resource  multifield = getResource().getChild("multifield");
		Iterator<Resource> resource = multifield.listChildren();
		
		
		String title;
		String description;
		String imagePath;
		
		while(resource.hasNext()){
			// fetch text,path,descripition and put in some array list
			Resource childItemNode = resource.next(); // item0
			
			title = childItemNode.getValueMap().get("text", String.class);
			description = childItemNode.getValueMap().get("description", String.class);
			imagePath = childItemNode.getValueMap().get("path", String.class);
			
			Items item = new Items();
			item.setText(title);
			item.setDescription(description);
			item.setPath(imagePath);
			
			multiItems.add(item);		
			
		}
			
	}
	
	public int getTotalValue() {
		return totalValue;
	}
	
	public int getTotalValue2() {
		return totalValue2;
	}
	
	public String getTitleValue() {
		return titleValue;
	}


}


