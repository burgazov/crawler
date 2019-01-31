/**
 * 
 */
package com.wipro.challenge.crawler.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ferdinand.burgazov
 *
 */

public class Location implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String loc;
	
	public Location(String loc) {
		this.loc = loc;
	}
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
}
