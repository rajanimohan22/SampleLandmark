package uk.co.landmark.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import uk.co.landmark.model.Fruit;
import uk.co.landmark.services.DataServices;
import uk.co.landmark.utility.Utilities;

@Controller
@RequestMapping("/priceAndFilter")
public class PriceAndFilterController {

	@Autowired
	DataServices dataServices;

	static final Logger logger = Logger.getLogger(PriceAndFilterController.class);
	
	@RequestMapping(value = "/item/{mainItemPrice}/{decimalItemPrice}/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean saveJSON(@PathVariable String mainItemPrice, @PathVariable String decimalItemPrice, @PathVariable String itemId) {
		boolean isSuccess = true;
		String floatValueStr = null;
		
		logger.info("Checking if the input params are right ..");
		if(!"undefined".equals(decimalItemPrice)) {
			logger.debug("Incorrect path variable value for decimalItemPrice");
			floatValueStr = mainItemPrice + "." + decimalItemPrice;
		}
		else {
			floatValueStr = mainItemPrice;
		}
		
		try {
			logger.debug("About to update the price of itemId " + itemId + " with Price = " + floatValueStr);
			dataServices.updatePrice(Long.parseLong(itemId), Float.parseFloat(floatValueStr));
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			logger.debug("Lots of problem. There is an exception while updating the price of item" + ex.getStackTrace());
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/filterFruits/{filterValue}", method = RequestMethod.GET)
	@ResponseBody
	public List<Fruit> filterFruitsByName(@PathVariable String filterValue) {
		List<Fruit> filteredList = null;
		List<Long> filteredListIds = new ArrayList<Long>();
		
		logger.info("Filtering the Fruit List .......");
		try {
			logger.debug("Fruits are about to be filtered by the filterValue = " + filterValue);
			filteredList = dataServices.filterFruitsByName(filterValue);
			for(Fruit fruit : filteredList) {
				filteredListIds.add(fruit.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("An error occurred while processing the Filtering of Fruits " + e.getStackTrace());
			e.printStackTrace();
		}
		
		logger.info("Sort the filtered List by Updated field");
		Utilities.sortFruitList(filteredList, "updated");
		return filteredList;
	}
	
	@RequestMapping(value = "/displayAllFruits", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayFruitDetails() {
		ModelAndView model = new ModelAndView("fruitDetail");
		try {
			List<Fruit> fruitList = dataServices.getAllEntities();
			logger.debug("FruitsList are to be ordered");
            Utilities.sortFruitList(fruitList, "updated");
            model.addObject("fruitList", fruitList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("An error occurred while displaying the  Fruits " + e.getStackTrace());
			e.printStackTrace();
		}
		return model;
	}
}
