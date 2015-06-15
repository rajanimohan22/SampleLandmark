package uk.co.landmark.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uk.co.landmark.model.Fruit;

public class Utilities {
	public static void sortFruitList(List<Fruit> fruitList, final String sortField) {
		Collections.sort(fruitList, new Comparator<Fruit>() {
			  public int compare(Fruit o1, Fruit o2) {
				  if("name".equalsIgnoreCase(sortField)) {
					  return o1.getName().compareTo(o2.getName());
					  
				  }
				  else if("price".equalsIgnoreCase(sortField)) {
					  return new Float(o1.getPrice()).compareTo(o2.getPrice());
					  
				  }
				  else if("stock".equalsIgnoreCase(sortField)) {
					  return new Integer(o1.getStock()).compareTo(o2.getStock());
					  
				  }
				  else if("updated".equalsIgnoreCase(sortField)) {
					  return o1.getUpdated().compareTo(o2.getUpdated());
					  
				  }
				  else {
					  return 0;
				  }
			  }
			});
	}
}
