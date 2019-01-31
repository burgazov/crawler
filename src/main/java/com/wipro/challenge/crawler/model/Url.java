/**
 * 
 */
package com.wipro.challenge.crawler.model;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ferdinand.burgazov
 *
 */
@XmlRootElement(name = "url")
public class Url  implements Serializable, LocationMarker{
	private static final long serialVersionUID = 1L;
	
	private Location url;
	
	/**
	 * 
	 */
	public Url(Location loc) {
		this.url = loc;
	}
	
	public Url(String loc) {
		this.url = new Location(loc);
	}

	public Location getUrl() {
		return url;
	}

	public void setUrl(Location url) {
		this.url = url;
	}
	
	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash( this.url );
	}

}
