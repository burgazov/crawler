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
//@XmlRootElement(name = "image")
public class Image implements Serializable, LocationMarker{
	private static final long serialVersionUID = 1L;
	
	private Location image;

	/**
	 * 
	 */
	public Image(Location loc) {
		this.image = loc;
	}
	
	public Image(String loc) {
		this.image = new Location(loc);
	}

	public Location getImage() {
		return image;
	}

	public void setImage(Location image) {
		this.image = image;	}	
	
	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash( this.getImage() );
	}
}
