/**
 * 
 */
package com.wipro.challenge.crawler.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.challenge.crawler.service.WebCrawler;

/**
 * @author ferdinand.burgazov
 *
 */
@RestController
@RequestMapping("/crawl")
public class CrawlerController {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(CrawlerController.class);
	
	@Autowired WebCrawler webCrawler;
	
	@RequestMapping(method = RequestMethod.GET, produces = {"text/plain"}) // 
	public  ResponseEntity<?> getAllLinks(
			@RequestParam(value="url", required=true) String url){ 
		LOGGER.info("\n\nCrawled site:" + url);
		
		String[] sitemap;
		try {
			sitemap = webCrawler.crawl(url);
			
			LOGGER.info(sitemap != null && sitemap.length > 0? 
				"\n\n" + String.join("", sitemap): "NO Links or Images FOUND!!!");
			
			ResponseEntity<String> result = new ResponseEntity<>( String.join("", sitemap), 
				sitemap != null && sitemap.length > 0 ?  
						HttpStatus.OK: HttpStatus.NOT_FOUND);
			 return result;
		} catch (URISyntaxException | IllegalArgumentException | IOException e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}
