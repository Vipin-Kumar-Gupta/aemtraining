package com.sample.sampleproject.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="Sample OSGI Sub Service Properites")
public @interface SampleOSGIAddServiceProperties {
	
	@AttributeDefinition(name="First Value",description="Enter First Value")
	int firstValue();
	
	@AttributeDefinition(name="Second Value",description="Enter Second Value")
	int secondValue();
	
	@AttributeDefinition(name="Check box field",type=AttributeType.BOOLEAN,defaultValue="true")
	String samplechk();
	
	@AttributeDefinition(name="Multifield Value",type=AttributeType.STRING)
	String[] sampleMultifield() default {"ABC","BCD"};
	
	@AttributeDefinition(name="Password Field Value",type=AttributeType.PASSWORD)
	String samplePasswordField() default "test";
	
	@AttributeDefinition(name="Drop Down properties",description="Drop Down",options = {
		@Option(label="Days",value="days"),
		@Option(label="Months",value="months")
	})
	String dropdownvalues();
	
	
	
	
}
