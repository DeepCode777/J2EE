import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class storeData {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("employee.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Employee e1 = new Employee();
		e1.setDepartment("BCA");
		e1.setFullName("Ashish Vekariya");
		e1.setId(1);
		session.persist(e1);
		
		tx.commit();
	}
}
