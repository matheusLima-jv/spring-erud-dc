package br.nbb.ymlconvert;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMsgConv extends AbstractJackson2HttpMessageConverter{

	public YamlJackson2HttpMsgConv() {
		super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType("app/x-yaml"),
				MediaType.parseMediaType("app/xml"),
				MediaType.parseMediaType("app/json"));	
	}
	
	

}
