package proj2.backend.implementation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
	public List<User> getAllUsers(User user) {
		Session session=sessionFactory.openSession();
		//SQL QUERY
		SQLQuery query=session.createSQLQuery(
		"select * from proj2_user where username in (select username from proj2_user where username!=? minus(select to_id from proj2_friend where from_id=? and status!='D' union select from_id from proj2_friend where to_id=? and status!='D'))");
		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(User.class);
		List<User> users=query.list();// list of users to whom 'smith' can send a friend request
		System.out.println(users);
		session.close();
		return users;
		}

}