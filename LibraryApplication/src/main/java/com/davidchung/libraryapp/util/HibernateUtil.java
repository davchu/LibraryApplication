package com.davidchung.libraryapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	// XML based configuration
	private static SessionFactory sessionFactory;
	
	// Annotation based configuration
	private static SessionFactory sessionAnnotationFactory;
	
	// Property based configuration
//	private static SessionFactory sessionJavaConfigFactory;

	
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			System.out.println("Hibernate Configuration loaded");
			
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
            
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            return sessionFactory;
   		} catch(Throwable ex) {
   			// Make sure you lgo the exception, aas it might be swallowed
   			System.err.println("Initial SessionFactory creationfailed."+ex);
   			throw new ExceptionInInitializerError(ex);
   		}
	}

	private static SessionFactory buildSessionAnnotationFactory() {
		try {
			// Create the SessionFactory from hibernate-annotation.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");
			System.out.println("Hibernate Annotation serviceRegistry created");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");
             
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
            return sessionFactory;
            
		} catch(Throwable ex) {
			// Make sure ou log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
//	private static SessionFactory buildSessionJavaConfigFactory() {
//		try {
//			Configuration configuration = new Configuration();
//			
//			//Create Properties, can be read from property files too
//			Properties props = new Properties();
//			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//		    props.put("hibernate.connection.url", "jdbc:mysql://localhost/testhibernate");
//		    props.put("hibernate.connection.username", "davidchung");
//		    props.put("hibernate.connection.password", "monfries6");
//		    props.put("hibernate.current_session_context_class", "thread");
//		    
//		    configuration.setProperties(props);
//		    
//		    // we can set mapping file or class with annotation 
//		    // addClass(Employee1.class) will look for resource
//		    // com/davidchung/hibernate/model/Employee1.html.xml (not good)
//		    configuration.addAnnotatedClass(LibraryMember.class);
//		    configuration.addAnnotatedClass(Book.class);
//		    configuration.addAnnotatedClass(Loan.class);
//		    
//		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//	        System.out.println("Hibernate Java Config serviceRegistry created");
//	         
//	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//	         
//	        return sessionFactory;
//		} catch(Throwable ex) {
//			System.err.println("Initial SessionFactory creationfailed."+ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
	
	public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
     
    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
     
}