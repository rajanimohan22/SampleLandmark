package uk.co.landmark.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.json.simple.parser.JSONParser;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import uk.co.landmark.model.Fruit;
import uk.co.landmark.services.DataServices;
import uk.co.landmark.utility.Utilities;

@Controller
@RequestMapping("/fruit-stock")
public class FileController {

	@Autowired
	DataServices dataServices;
	
	@Autowired
	Properties propertyConfigurer; 
	
	@Value("${invalid.file}")
	private String invalidFile;
	
	@Value("${empty.file}")
	private String emptyFile;
	
	static final Logger logger = Logger.getLogger(FileController.class);
	
	@RequestMapping(value = "/uploadJSON", method = RequestMethod.POST)
	public ModelAndView saveJSON(@RequestParam("file") MultipartFile file,  Model map) {
		String fileName = null;
		JSONParser parser = new JSONParser();
		ModelAndView model = null;
		
		if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                
                JSONObject jsonObject = (JSONObject) parser.parse(new String(bytes));
        		JSONArray fruitsList = (JSONArray) jsonObject.get("data");
        		@SuppressWarnings("unchecked")
        		Iterator<JSONObject> iterator = fruitsList.iterator();
        		ObjectMapper mapper = new ObjectMapper();
        		
        		logger.info("About to delete all the existing records in Fruit Table");
        		dataServices.deleteAllEntities();
        		
        		while (iterator.hasNext()) {
        			Fruit fruit = mapper.readValue( iterator.next().toString(), Fruit.class);
        			logger.debug("About to add to Database " + fruit);
        			dataServices.addEntity(fruit);
                }
        		
                List<Fruit> fruitList = dataServices.getAllEntities();
                logger.debug("The List retrieved from the Database " + fruitList);
                
                model = new ModelAndView("fruitDetail");
                Utilities.sortFruitList(fruitList, "updated");
                model.addObject("fruitList", fruitList);
            } catch (Exception e) {
            	logger.error("You failed to upload " + fileName + ": " + e.getMessage());
            	model = new ModelAndView("home");
            
                model.addObject("fileError", invalidFile);
            }
        } else {
        	logger.debug("The user input file is Empty");
        	model = new ModelAndView("home");
            model.addObject("fileError", emptyFile);
        }
    	return model;
    }
}
