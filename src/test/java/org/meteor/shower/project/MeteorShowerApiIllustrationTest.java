package org.meteor.shower.project;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;

public class MeteorShowerApiIllustrationTest extends TestCase {
	private SessionFactory sessionFactory;
	
	@Override
	protected void setUp() throws Exception{
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testBasicUsage() {
		MeteorShowerDBManager.initializeDB(sessionFactory);
		
		List<MeteorShower> allResults = MeteorShowerDBManager.getAllMeteorShowers(sessionFactory);
		for(MeteorShower ms : allResults) {
			System.out.println(ms.toString());
		}
		
		MeteorShower singleShower = MeteorShowerDBManager.getMeteorShowerByIAU("ELY", sessionFactory);
		System.out.println(singleShower.toString());
		
		List<MeteorShower> angDistResults = MeteorShowerDBManager.getMeteorShowersByLocationDegrees(287.0, 44.0, 80.2, sessionFactory);
		for(MeteorShower ms : angDistResults) {
			System.out.println(ms.toString());
		}
	}
}
