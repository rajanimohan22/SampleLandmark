package uk.co.landmark.dao;

import java.util.List;

import uk.co.landmark.model.Fruit;


public interface DataDao {
	public boolean addEntity(Fruit fruit) throws Exception;
	public boolean deleteAllEntities() throws Exception;
	public List<Fruit> getAllEntities() throws Exception;
	public void updatePrice(Long id, float updatedPrice) throws Exception;
	public List<Fruit> filterFruitsByName(String filterValue) throws Exception;
}
