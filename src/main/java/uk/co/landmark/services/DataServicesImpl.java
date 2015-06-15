package uk.co.landmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.landmark.model.Fruit;

import uk.co.landmark.dao.DataDao;

public class DataServicesImpl implements DataServices {

	@Autowired
	DataDao dataDao;
	
	@Override
	public boolean addEntity(Fruit fruit) throws Exception {
		return dataDao.addEntity(fruit);
	}

	@Override
	public boolean deleteAllEntities() throws Exception {
		return dataDao.deleteAllEntities();
	}
	
	@Override
	public List<Fruit> getAllEntities() throws Exception {
		return dataDao.getAllEntities();
	}
	
	@Override
	public void updatePrice(Long id, float updatedPrice) throws Exception {
		dataDao.updatePrice(id, updatedPrice);
	}
	
	
	@Override
	public  List<Fruit> filterFruitsByName(String filterValue) throws Exception {
		return dataDao.filterFruitsByName(filterValue);
	}
}
