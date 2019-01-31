/**
 * 
 */
package com.wipro.challenge.crawler.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ferdinand.burgazov
 *
 */
public class SiteMap  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Set<LocationMarker> sitemap = new LinkedHashSet<>();
	
	/**
	 * 
	 */
	public SiteMap() {}
	
	public SiteMap(Set<LocationMarker> sitemap) { 
		this.sitemap = new LinkedHashSet<>( sitemap );
	}
	
	public boolean add(LocationMarker image) {
		return sitemap.add(image);
	}
	
	public boolean addAll(Set<LocationMarker> images) {
		return sitemap.addAll(images);
	}
	
	public Set<LocationMarker> getSitemap(){
		return new LinkedHashSet<>( sitemap );	
	}

}
