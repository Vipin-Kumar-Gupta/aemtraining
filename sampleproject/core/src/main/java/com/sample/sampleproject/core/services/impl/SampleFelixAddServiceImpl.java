package com.sample.sampleproject.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.sample.sampleproject.core.services.SampleFelixAddService;

@Service(value=SampleFelixAddService.class)
@Component(label="Sample Felix Add Serive",description="This is the sample Service",metatype=true)
@Properties({
	@Property(name="addition.first",label="Enter First Value",description="Description 1",intValue=20),
	@Property(name="addition.second",label="Enter Second Value",description="Description 2",intValue=20)
})
public class SampleFelixAddServiceImpl implements SampleFelixAddService {
	
	int firstValue;
	int secondValue;
	
	String pageTitle;
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;	
	
	ResourceResolver resourceResolver;
	
	
	@Activate
	public final void activate(final Map<String,Object> properties){
		this.firstValue = (Integer)properties.get("addition.first");
		this.secondValue = (Integer)properties.get("addition.second");
	}

	@Override
	public String getPageTitle() {
		try {
			// Access the Title Property of Banner Component.
			Map<String, Object> param = new HashMap<String,Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "sampleService");
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
			
			Resource resource = resourceResolver.getResource("/content/personal0/jcr:content/body/personalbannermponen");		
			//ValueMap properties = resource.adaptTo(ValueMap.class); // Try it.
			return resource.getValueMap().get("text", String.class);
					
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageTitle;
	}

	
	@Override
	public int additionValue() {
		
		// TODO Auto-generated method stub
		return this.firstValue + this.secondValue;
	}

}
