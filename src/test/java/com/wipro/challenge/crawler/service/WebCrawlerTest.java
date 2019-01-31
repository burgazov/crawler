/**
 * 
 */
package com.wipro.challenge.crawler.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.challenge.crawler.controller.CrawlerControllerTest;

/**
 * @author ferdinand.burgazov
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebCrawlerTest {
	@Autowired private WebCrawler webCrawler;
	
	public static String HTML35_177_112_78;
	public static String HTML_WIPRO_TEST;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HTML35_177_112_78 = new String ( 
				Files.readAllBytes( Paths.get(
					"./src/test/resources/htmls/fe.html") ) );
		
		HTML_WIPRO_TEST = new String ( 
				Files.readAllBytes( Paths.get(
					"./src/test/resources/htmls/wipro.html") ) );
	} 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
	} 

	/**
	 * Test method for {@link com.wipro.challenge.crawler.service.WebCrawler#crawl(java.lang.String)}.
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testCrawl() 
			throws IllegalArgumentException, 
				MalformedURLException, URISyntaxException, IOException {
		String url = "http://35.177.112.78";
		String[] sitemap = webCrawler.crawl( url );
		assertEquals( CrawlerControllerTest.SITEMAP_35_177_112_78, 
				String.join("", sitemap) );
		
		url = "http://35.177.112.78:8080";
		sitemap = webCrawler.crawl( url );
		assertEquals( CrawlerControllerTest.SITEMAP_35_177_112_78_8080, 
				String.join("", sitemap) );
	}

	/**
	 * Test method for {@link com.wipro.challenge.crawler.service.WebCrawler#getHtml(java.lang.String)}.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testGetHtml() 
		throws IllegalArgumentException, MalformedURLException, IOException {
		String url = "http://35.177.112.78";
		String html = webCrawler.getHtml( url );
		assertEquals( HTML35_177_112_78, html);
		
		url = "https://www.wiprodigital.com";
		html = webCrawler.getHtml( url );
		assertEquals( HTML_WIPRO_TEST, html);
	}

	/**
	 * Test method for {@link com.wipro.challenge.crawler.service.WebCrawler#getInetAddress(java.lang.String, java.lang.String)}.
	 * @throws URISyntaxException 
	 * @throws UnknownHostException 
	 */
	@Test
	public void testGetInetAddress() 
			throws UnknownHostException, URISyntaxException {
		String url = "http://35.177.112.78";
		InetAddress inetAddress = webCrawler.getInetAddress( url, url);
		assertEquals("/35.177.112.78", inetAddress.toString());
		
		url = "http://35.177.112.78:8080";
		inetAddress = webCrawler.getInetAddress( url, url);
		assertEquals("/35.177.112.78", inetAddress.toString());
		
		url = "https://www.wiprodigital.com";
		inetAddress = webCrawler.getInetAddress( url, url);
		assertEquals("www.wiprodigital.com/52.7.121.233", inetAddress.toString());
	}

	/**
	 * Test method for {@link com.wipro.challenge.crawler.service.WebCrawler#getHost(java.lang.String, java.lang.String)}.
	 * @throws URISyntaxException 
	 */
	@Test
	public void testGetHost() throws URISyntaxException {
		String url = "http://35.177.112.78";
		String host = webCrawler.getHost( url, url);
		assertEquals("35.177.112.78", host);
		
		url = "http://35.177.112.78:8080";
		host = webCrawler.getHost( url, url);
		assertEquals("35.177.112.78", host);
		
		url = "https://www.wiprodigital.com";
		host = webCrawler.getHost( url, url);
		assertEquals("www.wiprodigital.com", host);
		
		url = "https://www.wiprodigital.com";
		host = webCrawler.getHost( "/what-we-do", url);
		assertEquals("www.wiprodigital.com", host);
	}

}
