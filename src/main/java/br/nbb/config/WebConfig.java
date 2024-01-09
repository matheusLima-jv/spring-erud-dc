package br.nbb.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.nbb.ymlconvert.YamlJackson2HttpMsgConv;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MD_TP_APP_YML = MediaType.valueOf("app/x-yaml");
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add( new YamlJackson2HttpMsgConv());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// http://localhost:8080/api/person/v1?mediaType=xml
		/* query param
		configurer.favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
				.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		// // http://localhost:8080/api/person/v1
		// header param
		configurer.favorParameter(false).ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MD_TP_APP_YML);
		
		
		
	}

}
