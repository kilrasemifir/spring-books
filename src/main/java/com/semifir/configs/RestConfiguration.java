package com.semifir.configs;

import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;

@Configuration
public class RestConfiguration extends AbstractElasticsearchConfiguration {

	@Override
	public RestHighLevelClient elasticsearchClient() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme"));
		RestClientBuilder builder = RestClient.builder( 
			new HttpHost("localhost", 9200))
				.setHttpClientConfigCallback(new HttpClientConfigCallback() {
		    		public HttpAsyncClientBuilder customizeHttpClient( HttpAsyncClientBuilder httpClientBuilder) {
		    			return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		    		}
		    	}
		    );
		return  new RestHighLevelClient(builder);
	}
	
	@Bean
	@Override
	public EntityMapper entityMapper() {                                               
		ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
				new DefaultConversionService());
		entityMapper.setConversions(elasticsearchCustomConversions());
		return entityMapper;
	}

	
	@Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
	public ElasticsearchRestTemplate elasticsearchTemplate() throws UnknownHostException { 
		return new ElasticsearchRestTemplate(elasticsearchClient(), entityMapper());
	}
}

