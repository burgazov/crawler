/**
 * 
 */
package com.wipro.challenge.crawler.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author ferdinand.burgazov
 *
 */
@Service("webCrawler")
public class WebCrawlerService implements WebCrawler {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebCrawlerService.class);

	private final Set<String> links = new LinkedHashSet<String>();
	private final Set<String> images = new LinkedHashSet<String>();
	
	private String hostName;
	private InetAddress inetAddress;
	
	// To increase the performance it neeeds to be mutlithreaded and paralellized
	private void recursiveCrawl(String url, String parentUrl) {
		try {
			
			if(!links.contains( url ) ) {
				links.add(url); 
				InetAddress address = getInetAddress( url, parentUrl);
				
				if( address.equals(inetAddress)  )  {
					String html = getHtml( url );		
		
					Set<String> imgLnks = Stream.of( html.split( 
						WebCrawler.IMAGES_REGEX_MAP.values().parallelStream().collect(Collectors.joining("|")) ) )
							.parallel()
							.filter( href -> ( href.startsWith( "http") || href.startsWith( "/") )
									&& ! href.startsWith( "<") )
							.map( href -> href.substring( 0, href.indexOf( "\""  ) == -1?  
									href.indexOf( "'"  ):  href.indexOf( "\""  ) ) )
							.map(href -> { int idx = href.indexOf( "'"  ); 
								if(idx > 0) return href.substring( 0, idx); else return href; })
							.collect( Collectors.toSet());
					images.addAll(imgLnks);	
		
					Set<String> siteLnks = Stream.of( html.split( 
						WebCrawler.LINKS_REGEX_MAP.values().parallelStream().collect(Collectors.joining("|")) ) )
							.parallel()
							.filter( href -> ! href.startsWith( "<") // && ! href.startsWith("//")
								&& ( href.startsWith("http") || href.startsWith("/") || href.startsWith("www") ) )
							.map( href -> href.contains("\"") || href.contains("'") ? 
									href = href.split( "\"|'" )[ 0 ]: href )
							.map(href ->  href.startsWith("/") && !(href.startsWith( "http" )  )? 
									( href.startsWith("//")? "http:" + href: url + href): href)
							.filter( href -> ! links.contains(href) )
							.collect( Collectors.toSet());
					
					parentUrl = url;
					siteLnks.parallelStream().forEach( x -> 
						{ LOGGER.info( x ); if( ! links.contains( x ) ) recursiveCrawl( x, url ); } ); 
				}
			}
			
		} catch( URISyntaxException | IllegalArgumentException | IOException urise) {
			LOGGER.error(urise.getMessage());
//			e.printStackTrace();
		}			
	}

	@Override
	public String[] crawl(String url) 
			throws URISyntaxException, IllegalArgumentException, 
			MalformedURLException, IOException  {

	    hostName = getHost(url, url); // 
	    inetAddress = getInetAddress( url, url);
	    
	    links.clear();
	    images.clear();
	    
	    
	    if( hostName != null && ! hostName.isEmpty())
	    	recursiveCrawl( url, url );
		
	    String[] result = new String[ links.size() + images.size() + 2 ];	
	    result[ 0 ] = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sitemap>";
	    
	    int counter = 1;
	    for( String str: links)
	    	result[ counter++ ] = "<url><loc>" + str + "</loc></url>";
	    for( String str: images)
	    	result[ counter++ ] = "<image><loc>" + str + "</loc></image>";	    

	    result[ result.length - 1 ] = "</sitemap>";
		return result;
	}	

}
