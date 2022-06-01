package org.meteor.shower.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.math.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

//List of meteor showers taken from IMO Working list
//https://www.imo.net/members/imo_showers/working_shower_list

public class MeteorShowerDBManager {
	public static void initializeDB(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(
				new MeteorShower(
					(long) 1,
					"GUM",
					"γ-Ursae Minorid",
					Date.from(new GregorianCalendar(2022, 1, 15).toInstant()),
					Date.from(new GregorianCalendar(2022, 1, 25).toInstant()),
					Date.from(new GregorianCalendar(2022, 1, 20).toInstant()),
					229.0,
					67.0,
					31.0,
					3.0,
					3				
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 2,
					"ANT",
					"Antihelion Source",
					Date.from(new GregorianCalendar(2021, 12, 10).toInstant()),
					Date.from(new GregorianCalendar(2022, 9, 10).toInstant()),
					Date.from(new GregorianCalendar(2022, 1, 1).toInstant()),
					187.0,
					-4.0,
					30.0,
					3.0,
					4				
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 4,
					"QUA",
					"Quadrantids",
					Date.from(new GregorianCalendar(2021, 12, 26).toInstant()),
					Date.from(new GregorianCalendar(2022, 1, 12).toInstant()),
					Date.from(new GregorianCalendar(2022, 1, 4).toInstant()),
					230.0,
					49.0,
					41.0,
					2.1,
					120			
				)
			);

		session.save(
				new MeteorShower(
					(long) 5,
					"ACE",
					"α-Centaurids",
					Date.from(new GregorianCalendar(2022, 1, 28).toInstant()),
					Date.from(new GregorianCalendar(2022, 2, 21).toInstant()),
					Date.from(new GregorianCalendar(2022, 2, 8).toInstant()),
					210.0,
					-59.0,
					56.0,
					2.0,
					6			
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 6,
					"GNO",
					"γ-Normids",
					Date.from(new GregorianCalendar(2022, 2, 25).toInstant()),
					Date.from(new GregorianCalendar(2022, 3, 28).toInstant()),
					Date.from(new GregorianCalendar(2022, 3, 14).toInstant()),
					239.0,
					-50.0,
					56.0,
					2.4,
					6			
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 7,
					"LYR",
					"Lyrids",
					Date.from(new GregorianCalendar(2022, 4, 16).toInstant()),
					Date.from(new GregorianCalendar(2022, 4, 25).toInstant()),
					Date.from(new GregorianCalendar(2022, 4, 22).toInstant()),
					271.0,
					34.0,
					49.0,
					2.1,
					18		
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 8,
					"PPU",
					"π-Puppids",
					Date.from(new GregorianCalendar(2022, 4, 15).toInstant()),
					Date.from(new GregorianCalendar(2022, 4, 28).toInstant()),
					Date.from(new GregorianCalendar(2022, 4, 23).toInstant()),
					110.0,
					-45.0,
					18.0,
					2.0,
					0			
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 10,
					"ETA",
					"η-Aquariids",
					Date.from(new GregorianCalendar(2022, 4, 19).toInstant()),
					Date.from(new GregorianCalendar(2022, 5, 28).toInstant()),
					Date.from(new GregorianCalendar(2022, 5, 5).toInstant()),
					338.0,
					-1.0,
					66.0,
					2.40,
					40			
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 11,
					"ELY",
					"η-Lyrids",
					Date.from(new GregorianCalendar(2022, 5, 03).toInstant()),
					Date.from(new GregorianCalendar(2022, 5, 14).toInstant()),
					Date.from(new GregorianCalendar(2022, 5, 8).toInstant()),
					287.0,
					44.0,
					43.0,
					3.0,
					3			
				)
			);
		
		session.save(
				new MeteorShower(
					(long) 23,
					"STA",
					"Southern Taurids",
					Date.from(new GregorianCalendar(2022, 9, 10).toInstant()),
					Date.from(new GregorianCalendar(2022, 11, 20).toInstant()),
					Date.from(new GregorianCalendar(2022, 10, 10).toInstant()),
					32.0,
					9.0,
					27.0,
					2.3,
					5			
				)
			);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static List<MeteorShower> getAllMeteorShowers(SessionFactory sessionFactory){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from MeteorShower").list();
		session.getTransaction().commit();
		session.close();
		return (List<MeteorShower>) result;
	}
	
	public static MeteorShower getMeteorShowerByIAU(String iau, SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		MeteorShower result = (MeteorShower) session.createQuery("from MeteorShower ms where ms.iau = :iau").setParameter("iau", iau).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static List<MeteorShower> getMeteorShowersByLocationDegrees (double ra, double dec, double deg, SessionFactory sessionFactory){
		if(deg < 0) {
			return null;
		}
		double rad = Math.toRadians(deg);
		return getMeteorShowersByLocationRadians(ra, dec, rad, sessionFactory);
	}
	
	public static List<MeteorShower> getMeteorShowersByLocationRadians (double ra, double dec, double rad, SessionFactory sessionFactory){
		List<MeteorShower> all = getAllMeteorShowers(sessionFactory);
		List<MeteorShower> results = new ArrayList<MeteorShower >();
		for(MeteorShower ms : all) {
			//Formula to calculate angular distance taken from:
			//https://www.gyes.eu/calculator/calculator_page1.htm
			double angularDistance = Math.acos(
				Math.sin(Math.toRadians(ms.getDec())) * Math.sin(Math.toRadians(dec)) + Math.cos(Math.toRadians(ms.getDec())) * Math.cos(Math.toRadians(dec)) * Math.cos(Math.toRadians(ra - ms.getRa()))
			);
			if(angularDistance <= rad) {
				results.add(ms);
			}
		}
		return results;
	}
}
