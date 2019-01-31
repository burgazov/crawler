/**
 * 
 */
package com.wipro.challenge.crawler.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wipro.challenge.crawler.SimpleWebCrawlerChallenge;

/**
 * @author ferdinand.burgazov
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleWebCrawlerChallenge.class)
public class CrawlerControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	public final static String SITEMAP_35_177_112_78 = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<sitemap>"
			+ "<url><loc>http://35.177.112.78</loc></url>"
			+ "<url><loc>https://fonts.googleapis.com/css?family=Oxygen+Mono</loc></url>"
			+ "</sitemap>";
	
	public final static String SITEMAP_35_177_112_78_8080 = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<sitemap>"
			+ "<url><loc>http://35.177.112.78:8080</loc></url>"
			+ "</sitemap>";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * Test method for {@link com.wipro.challenge.crawler.controller.CrawlerController#getAllLinks(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetAllLinks() throws Exception {
		mockMvc.perform(  MockMvcRequestBuilders.get( "/crawl?url=http://35.177.112.78") )
					.andExpect( status().isOk() )
					.andExpect( content().contentType("text/plain;charset=UTF-8") )
					.andExpect( jsonPath("$").value( SITEMAP_35_177_112_78 ) )
					.andDo( print() );
		
		mockMvc.perform(  MockMvcRequestBuilders.get( 
			"/crawl?url=http://35.177.112.78:8080") )
				.andExpect( status().isOk() )
				.andExpect( content().contentType("text/plain;charset=UTF-8") )
				.andDo( print() );
	}

	/**
	 * Test method for {@link com.wipro.challenge.crawler.controller.CrawlerController#getAllLinks(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test // resolved exception ResponseStatusException
	public void testGetAllLinksException() throws Exception {
		mockMvc.perform(  MockMvcRequestBuilders.get( 
			"/crawl?url=http://http://ferdinand.burgazov.com") )
				.andExpect( status().is( HttpStatus.BAD_REQUEST.value() ) )
				.andDo( print() );
	}

}
