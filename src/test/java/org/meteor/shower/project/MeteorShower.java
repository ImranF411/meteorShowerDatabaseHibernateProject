
package org.meteor.shower.project;

import java.util.Date;

public class MeteorShower {
	private Long id;
	
	private String iau;	//IAU Code
	private String name;
	
	private Date start;
	private Date end;
	private Date peak;
	
	private double ra;	//Right Ascension
	private double dec;	//Declination
	
	private double v;	//Velocity
	private double r;	//Population Index
	
	private int zhr;	//Zenith Hourly Rate

	public MeteorShower() {
	}
	
	public MeteorShower(Long id, String iau, String name, Date start, Date end, Date peak, double ra, double dec, double v,
			double r, int zhr) {
		super();
		this.iau = iau;
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.peak = peak;
		this.ra = ra;
		this.dec = dec;
		this.v = v;
		this.r = r;
		this.zhr = zhr;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getPeak() {
		return peak;
	}

	public void setPeak(Date peak) {
		this.peak = peak;
	}

	public double getRa() {
		return ra;
	}

	public void setRa(double ra) {
		this.ra = ra;
	}

	public double getDec() {
		return dec;
	}

	public void setDec(double dec) {
		this.dec = dec;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public int getZhr() {
		return zhr;
	}

	public void setZhr(int zhr) {
		this.zhr = zhr;
	}

	public String getIau() {
		return iau;
	}

	public void setIau(String iau) {
		this.iau = iau;
	}

	@Override
	public String toString() {
		return "Meteor Shower: (" + getIau() + ") - " + getName();
	}
}
