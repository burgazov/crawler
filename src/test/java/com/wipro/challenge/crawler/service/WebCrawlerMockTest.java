/**
 * 
 */
package com.wipro.challenge.crawler.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author ferdinand.burgazov
 *
 */
public class WebCrawlerMockTest {
	@Mock private WebCrawler webCrawler;
	@Mock Socket anySocket;
	@Mock SocketChannel anySocketChannel;
	
	public static String HTML35_177_112_78;
	public static String HTML_WIPRO_TEST;

	public static final String URL_WIPRO = "https://www.wiprodigital.com";
	public static final String URL_35_177_112_78 = "http://35.177.112.78";
	public static final String URL_35_177_112_78_8080 = "http://35.177.112.78:8080";	

	public static final String HOST_WIPRO = "www.wiprodigital.com";
	public static final String HOST_35_177_112_78 = "35.177.112.78";
	
	public static final String INET_WIPRO = "www.wiprodigital.com/52.7.121.233";	
	public static final String INET_35_177_112_78 = "/35.177.112.78";

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
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when( webCrawler.getHost( URL_35_177_112_78, URL_35_177_112_78 ) )
		.thenReturn( HOST_35_177_112_78  );

		when( webCrawler.getHost( URL_35_177_112_78_8080, URL_35_177_112_78_8080 ) )
		.thenReturn( HOST_35_177_112_78  );

		when( webCrawler.getHost( URL_WIPRO, URL_WIPRO ) )
		.thenReturn( HOST_WIPRO  );

		when( webCrawler.getHtml( URL_35_177_112_78 ) )
		.thenReturn( HTML35_177_112_78  );

		when( webCrawler.getHtml( URL_WIPRO ) )
		.thenReturn( HTML_WIPRO_TEST  );

//		when( webCrawler.getInetAddress( URL_WIPRO, URL_WIPRO ) )
//		.thenReturn(  null   ); // INET_WIPRO
//
//		when( webCrawler.getInetAddress( URL_35_177_112_78_8080, URL_35_177_112_78_8080 )  )
//		.thenReturn(  null );
//
//		when( webCrawler.getInetAddress( URL_35_177_112_78, URL_35_177_112_78 ).toString() )
//		.thenReturn( null  );
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
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
			throws IllegalArgumentException, MalformedURLException, 
				URISyntaxException, IOException {
//		String[] sitemap = webCrawler.crawl( URL_35_177_112_78 );
//		assertEquals( CrawlerControllerTest.SITEMAP_35_177_112_78, 
//				String.join("", sitemap) );
//		
//		sitemap = webCrawler.crawl( URL_35_177_112_78_8080 );
//		assertEquals( CrawlerControllerTest.SITEMAP_35_177_112_78_8080, 
//				String.join("", sitemap) );
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
		String html = webCrawler.getHtml( URL_35_177_112_78 );
		assertEquals( HTML35_177_112_78, html);
		
		html = webCrawler.getHtml( URL_WIPRO );
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
//		InetAddress inetAddress = webCrawler.getInetAddress( URL_35_177_112_78, URL_35_177_112_78);
//		assertEquals("/35.177.112.78", inetAddress.toString());
//		
//		inetAddress = webCrawler.getInetAddress( URL_35_177_112_78_8080, URL_35_177_112_78_8080);
//		assertEquals("/35.177.112.78", inetAddress.toString());
//		
//		inetAddress = webCrawler.getInetAddress( URL_WIPRO, URL_WIPRO);
//		assertEquals("www.wiprodigital.com/52.7.121.233", inetAddress.toString());
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
	}

}
