/**
 * 
 */
package com.wipro.challenge.crawler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;

/**
 * @author ferdinand.burgazov
 *
 */
public interface WebCrawler {
	public static final Map<String, String> LINKS_REGEX_MAP = Arrays.stream(new String[][] {
		{"<a tags", "<a [^>]*href=['\"]?"}, {"<link tags", "<link [^>]*href=['\"]?"}, 
		{"<meta property=og:url", "\"og:url\" [^>]*content=['\"]?"}
	}).collect(Collectors.toMap( kv -> kv[ 0 ], kv -> kv[ 1 ]));
	
	public static final Map<String, String> IMAGES_REGEX_MAP = Arrays.stream(new String[][] {
		{"<img tags", "<img [^>]*src=['\"]?"}, {"<meta name or prop :image", ":image\" [^>]*content=['\"]?"}
		, {"<meta name or prop :image:secure_url", ":secure_url\" [^>]*content=['\"]?"}
		, {"header-image\"", "header-image\" [^>]*url\\(&quot;['\"]?"} // 
		, {"background: url('", "\"background: [^>]*url\\(\'['\"]?"}
	}).collect( Collectors.toConcurrentMap( x -> x[ 0 ], x -> x[ 1 ]));
	
	public String[] crawl(String url) 
			throws URISyntaxException, IllegalArgumentException, 
			MalformedURLException, IOException;
	
//	public default String getBaseUri(String url) 
//			throws IllegalArgumentException, IOException, 
//				MalformedURLException, URISyntaxException{
//		return new URI( url ).getPath();
//	}
	
	public default String getHtml(String url) 
			throws IllegalArgumentException, IOException, MalformedURLException {
		String result = new BufferedReader(new InputStreamReader( 
				new URL( url ).openStream() )).lines()
					.parallel().collect(Collectors.joining("\n"));	
		
		return result.replaceAll("\n", "").replaceAll("\r", "").trim();
	}
	
	public default InetAddress getInetAddress(String url, String uriMain) 
			throws UnknownHostException, URISyntaxException {
		return InetAddress.getByName(getHost( url, uriMain));
	}
	
	public default String getHost(String url, String parent) 
			throws URISyntaxException {
		String host = new URI(url).getHost();
		
		if(host == null)
			if( url != null && ! url.isEmpty() && url.startsWith("/"))
				host = new URI(parent + url).getHost();
			else 
				return null;
			
		return host;
	}
}
