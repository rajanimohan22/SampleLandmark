IMPORTANT Note about data format of JSON.
The JSON file should be appended by "data" Key, otherwise the file will be invalid.

Ex: For the given file, the input should be modified as:
  {
	"data" : [{
		"name" : "banana",
		"price" : 0.29,
		"stock" : 20,
		"updated" : "2014-01-02"
	}, {
		"name" : "melon",
		"price" : 1.01,
		"stock" : 3,
		"updated" : "2014-03-28"
	}, {
		"name" : "apple",
		"price" : 1.54,
		"stock" : 22,
		"updated" : "2014-02-05"
	}, {
		"name" : "pear",
		"price" : 0.41,
		"stock" : 12,
		"updated" : "2014-04-19"
	}, {
		"name" : "kumquat",
		"price" : 0.64,
		"stock" : 32,
		"updated" : "2014-06-10"
	}, {
		"name" : "orange",
		"price" : 2.04,
		"stock" : 19,
		"updated" : "2014-05-25"
	}, {
		"name" : "lemon",
		"price" : 1.56,
		"stock" : 9,
		"updated" : "2014-12-30"
	}]
  }



Accessing the Application:

For uploading a JSON File, user can use the direct link (This can be treated as a home Page):
http://localhost:8080/grocery-1.0/

Depending on the application server, the version number '-1.0' can be dropped. I have used Tomcat Server and the war is exploded to include the version.

Or use the following link to see the items present in the database:
http://localhost:8080/grocery-1.0/priceAndFilter/displayAllFruits
If there are no items, then this page will help the user to go to the File upload page (the above link which acts as Home Page)

Functionality as per the requirements is met as:
	1. From the Home Page, the user (admin) can upload a valid JSON file. If the file is not valid, appropriate error message will be displayed.
	2. Retrieval service: The items will be displayed sorted by their 'Updated' date field. Use the following link for retrieval:
			http://localhost:8080/grocery-1.0/priceAndFilter/displayAllFruits
	3. In the same page (above in Step 2), user can update the prices of a fruit using the text box edit. 
	   If the update fails due to wrong data, then an error alert message will be shown. User can see fresh list from database by clicking on 'Clear Filter' button.
	4. There is a text box facility to filter the fruits by name. The list will be refreshed with matches if found. 
	   The list will NOT be refreshed if there are no matches. At any point of time user can see the refreshed data from database using 'Clear Filter' button.
	   
MYSQL Script that needs to be run:

CREATE TABLE fruit (
id int(10) NOT NULL primary key auto_increment,
name VARCHAR(40) NOT NULL, 
price DECIMAL(7,2) NOT NULL, 
stock INT(10) NOT NULL, 
updated DATE NOT NULL
);


