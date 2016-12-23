package proj2.backend.implementation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import proj2.backend.implementation.UserDao;
import proj2.backend.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User authenticate(User user) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(
		"from User where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		session.close();
		return validUser;
		
	}
	
	public void updateUser(User user) {
		Session session=sessionFactory.openSession();
		User existingUser=(User)session.get(User.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();
	}
	public User registerUser(User user) {
		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		return user;
	}

}