package com.sample.sampleproject.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.sample.sampleproject.core.services.SampleOSGIAddService;
import com.sample.sampleproject.core.services.SampleOSGIAddServiceProperties;

@Component(service=SampleOSGIAddService.class)
@Designate(ocd=SampleOSGIAddServiceProperties.class)
public class SampleOSGIAddServiceImpl implements SampleOSGIAddService {
	
	int firstValue;
	int secondValue;
	
	@Activate
	public final void activate(SampleOSGIAddServiceProperties properties){
		this.firstValue = properties.firstValue();
		this.secondValue = properties.secondValue();
		
	}

	@Override
	public int additionValue() {
		// TODO Auto-generated method stub
		return this.firstValue + this.secondValue;
	}

}
