package uk.co.landmark.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.landmark.model.Fruit;


public class DataDaoImpl implements DataDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Fruit fruit) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(fruit);
		tx.commit();
		session.close();

		return false;
	}

	
	@Override
	public boolean deleteAllEntities()
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		Query query = session.createQuery("delete from Fruit");
		session.beginTransaction();
		query.executeUpdate();
		tx.commit();
		return false;
	}
	
	@Override
	public List<Fruit> getAllEntities()
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Query query = session.createQuery("from Fruit"); 
     	@SuppressWarnings("unchecked")
		List<Fruit> fruitList = query.list(); 
		tx.commit();
		return fruitList;
	}
	
	@Override
	public void updatePrice(Long id, float updatedPrice) throws Exception {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Fruit fruit = (Fruit) session.load(Fruit.class, id);
		fruit.setPrice(updatedPrice);
		session.saveOrUpdate(fruit);
     	tx.commit();
	}
	
	@Override
	public List<Fruit> filterFruitsByName(String filterValue) throws Exception {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Fruit.class)
				   .add(Restrictions.like("name", filterValue, MatchMode.ANYWHERE));
		@SuppressWarnings("unchecked")
		List<Fruit> filteredList = (List<Fruit>)criteria.list();
		tx.commit();
		return filteredList;
	}
}
