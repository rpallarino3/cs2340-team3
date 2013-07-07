package model;

import java.util.ArrayList;  
import java.util.Hashtable;
import java.util.Arrays;

/**
 * Build a network of all territories and continents utilized in Risk.
 *
 * @author Jaemin
 *
**/
  
public class RiskMapNetwork {

  private ArrayList<Territory> asia;
  private ArrayList<Territory> northAmerica;
  private ArrayList<Territory> southAmerica;
  private ArrayList<Territory> australia;
  private ArrayList<Territory> europe;
  private ArrayList<Territory> africa;
  private Hashtable<String,Territory> territories;  
  
  
  
  public RiskMapNetwork(){
  
	  initializeContinents();
	  setNorthAmerica();
	  setSouthAmerica();
	  setEurope();
	  setAfrica();
	  setAsia();
	  setAustralia();
	  initializeAdjacentTerritories();
  }
    private void initializeContinents() {
	  territories = new Hashtable<String, Territory>();
      asia = new ArrayList<Territory>();
      northAmerica = new ArrayList<Territory>();
      southAmerica = new ArrayList<Territory>();
      australia = new ArrayList<Territory>();
      europe = new ArrayList<Territory>();
      africa = new ArrayList<Territory>();
	  territories.put("Alaska",new Territory("Alaska",20,100));
	  territories.put("Northwest",new Territory("Northwest",100,100));
	  territories.put("Greenland",new Territory("Greenland",280,70));
	  territories.put("Alberta",new Territory("Alberta",100,150));
	  territories.put("Ontario",new Territory("Ontario",180,160));
	  territories.put("EasternCanada",new Territory("Eastern Canada",250,145));
	  territories.put("WestUS",new Territory("West US",100,200));
	  territories.put("EastUS",new Territory("East US",190,220));
	  territories.put("CentralAmerica",new Territory("Central America",100,250));
	  territories.put("Arawak", new Territory("Arawak",160,320));
	  territories.put("Kariri", new Territory("Kariri",250,370));
	  territories.put("Baniva", new Territory("Baniva",180,390));
	  territories.put("Ika", new Territory("Ika",190,460));
	  territories.put("Sweden", new Territory("Sweden",380,100));
	  territories.put("Britain", new Territory("Britain",340,160));
	  territories.put("Germany", new Territory("Germany",380,180));
	  territories.put("Ukraine", new Territory("Ukraine",450,170));
	  territories.put("Spain", new Territory("Spain",330,220));
	  territories.put("RomanEmpire", new Territory("Roman Empire",390,215));
	  territories.put("Egypt", new Territory("Egypt",400,290));
	  territories.put("Dinka", new Territory("Dinka",350,340));
	  territories.put("Xhosa", new Territory("Xhosa",430,350));
	  territories.put("Ngbandi", new Territory("Ngbandi",390,405));
	  territories.put("Sakalava", new Territory("Sakalava",395,470));
	  territories.put("Kurdish", new Territory("Kurdish",580,100));
	  territories.put("Pashtun", new Territory("Pashtun",680,100));
	  territories.put("Buryats", new Territory("Buryats",540,130));
	  territories.put("Puyuma", new Territory("Puyuma",650,150));
	  territories.put("Seediq", new Territory("Seediq",520,200));
	  territories.put("Khakas", new Territory("Khakas",670,190));
	  territories.put("China", new Territory("China",630,250));
	  territories.put("MiddleEast", new Territory("Middle East",470,270));
	  territories.put("India", new Territory("India",565,300));
	  territories.put("Korea", new Territory("Korea",630,315));
	  territories.put("Tuvans", new Territory("Tuvans",760,100));
	  territories.put("Singapore", new Territory("Singapore",650,380));
	  territories.put("NewZealand", new Territory("New Zealand",730,380));
	  territories.put("WesternAustralia", new Territory("Western Australia",610,470));
	  territories.put("EasternAustralia", new Territory("Eastern Australia",730,450));
	  
  }  
  
  private void setNorthAmerica() {

      northAmerica.add(territories.get("Alaska"));
      northAmerica.add(territories.get("Northwest"));
      northAmerica.add(territories.get("Greenland"));
      northAmerica.add(territories.get("Alberta"));
      northAmerica.add(territories.get("Ontario"));
      northAmerica.add(territories.get("EasternCanada"));
      northAmerica.add(territories.get("WestUS"));
      northAmerica.add(territories.get("EastUS"));
      northAmerica.add(territories.get("CentralAmerica"));  
  }
  
  private void setSouthAmerica() {

      southAmerica.add(territories.get("Arawak"));
      southAmerica.add(territories.get("Kariri"));
      southAmerica.add(territories.get("Baniva"));
      southAmerica.add(territories.get("Ika"));
  }
  
  private void setEurope() {
	  
      europe.add(territories.get("Sweden"));
      europe.add(territories.get("Britain"));
      europe.add(territories.get("Germany"));
      europe.add(territories.get("Ukraine"));
      europe.add(territories.get("Spain"));
      europe.add(territories.get("RomanEmpire"));
  }
  private void setAfrica() {

      africa.add(territories.get("Egypt"));
      africa.add(territories.get("Dinka"));
      africa.add(territories.get("Xhosa"));
      africa.add(territories.get("Ngbandi"));
      africa.add(territories.get("Sakalava"));
  }
  
  private void setAsia() {

      asia.add(territories.get("Kurdish"));
      asia.add(territories.get("Pashtun"));
      asia.add(territories.get("Buryats"));
      asia.add(territories.get("Puyuma"));
      asia.add(territories.get("Seediq"));
      asia.add(territories.get("Khakas"));
      asia.add(territories.get("China"));
      asia.add(territories.get("MiddleEast"));
      asia.add(territories.get("India"));
      asia.add(territories.get("Korea"));
      asia.add(territories.get("Tuvans"));
  }
  
  private void setAustralia() {

	  australia.add(territories.get("Singapore"));
      australia.add(territories.get("NewZealand"));
      australia.add(territories.get("WesternAustralia"));
      australia.add(territories.get("EasternAustralia"));
  }
  
  public ArrayList<Territory> getNorthAmerica(){
	return northAmerica;
  }
  public ArrayList<Territory> getSouthAmerica(){
	return southAmerica;
  }
  public ArrayList<Territory> getAsia(){
	return asia;
  }
  public ArrayList<Territory> getAustralia(){
	return australia;
  }
  public ArrayList<Territory> getEurope(){
	return europe;
  }
  public ArrayList<Territory> getAfrica(){
	return africa;
  }
  public Hashtable<String, Territory> getTerritories(){
	return territories;
  }
  
  private void initializeAdjacentTerritories(){

	addAdjacentTerritories("Alaska", new String[]{"Northwest", "Alberta", "Tuvans"});	
	addAdjacentTerritories("Northwest", new String[]{"Alaska", "Alberta", "Greenland", "Ontario"});
	addAdjacentTerritories("Alberta", new String[]{"Alaska", "Northwest", "Ontario", "WestUS"});	
	addAdjacentTerritories("Ontario", new String[]{"Alberta", "Northwest", "WestUS", "EastUS", "EasternCanada", "Greenland"});
	addAdjacentTerritories("Greenland", new String[]{"Northwest", "Ontario", "EasternCanada", "Sweden"});	
	addAdjacentTerritories("EasternCanada", new String[]{"Ontario", "Greenland", "EastUS"});
	addAdjacentTerritories("WestUS", new String[]{"Alberta", "Ontario", "EastUS", "CentralAmerica"});	
	addAdjacentTerritories("EastUS", new String[]{"WestUS", "Ontario", "EasternCanada", "CentralAmerica"});	
	addAdjacentTerritories("CentralAmerica", new String[]{"WestUS", "EastUS", "Arawak"});	
	addAdjacentTerritories("Arawak", new String[]{"CentralAmerica", "Baniva", "Kariri"});
	addAdjacentTerritories("Baniva", new String[]{"Arawak", "Kariri", "Ika"});
	addAdjacentTerritories("Kariri", new String[]{"Arawak", "Baniva", "Ika", "Dinka"});		
	addAdjacentTerritories("Ika", new String[]{"Baniva"});	
	addAdjacentTerritories("Dinka", new String[]{"Kariri", "Spain", "RomanEmpire", "Egypt", "Xhosa", "Ngbandi", "Sakalava"});		
	addAdjacentTerritories("Sakalava", new String[]{"Ngbandi", "Xhosa"});	
	addAdjacentTerritories("Ngbandi", new String[]{"Dinka", "Xhosa", "Sakalava"});		
	addAdjacentTerritories("Xhosa", new String[]{"Egypt", "Dinka", "Ngbandi", "Sakalava", "MiddleEast"});		
	addAdjacentTerritories("Egypt", new String[]{"Dinka", "RomanEmpire", "MiddleEast", "Xhosa"});	
	addAdjacentTerritories("WesternAustralia", new String[]{"EasternAustralia", "Singapore", "NewZealand"});
	addAdjacentTerritories("EasternAustralia", new String[]{"WesternAustralia", "Singapore", "NewZealand"});	
	addAdjacentTerritories("Singapore", new String[]{"WesternAustralia", "EasternAustralia", "NewZealand", "India", "Korea", "China"});
	addAdjacentTerritories("NewZealand", new String[]{"WesternAustralia", "Singapore", "EasternAustralia"});	
	addAdjacentTerritories("Sweden", new String[]{"Greenland", "Britain", "Germany", "Ukraine"});	
	addAdjacentTerritories("Britain", new String[]{"Sweden", "Spain", "Germany"});	
	addAdjacentTerritories("Spain", new String[]{"Dinka", "Britain", "Germany", "RomanEmpire"});
	addAdjacentTerritories("Germany", new String[]{"Britain", "Sweden", "Spain", "RomanEmpire", "Ukraine"});	
	addAdjacentTerritories("RomanEmpire", new String[]{"Spain", "Germany", "Dinka", "Egypt", "Ukraine", "MiddleEast"});	
	addAdjacentTerritories("Ukraine", new String[]{"Sweden", "Germany", "RomanEmpire", "Seediq", "Buryats", "MiddleEast"});	
	addAdjacentTerritories("MiddleEast", new String[]{"RomanEmpire", "Ukraine", "Egypt", "Xhosa", "Seediq", "India"});
	addAdjacentTerritories("India", new String[]{"MiddleEast", "Seediq", "Korea", "China", "Singapore"});	
	addAdjacentTerritories("Korea", new String[]{"India", "China", "Singapore"});
	addAdjacentTerritories("Seediq", new String[]{"Ukraine", "MiddleEast", "India", "China", "Buryats"});	
	addAdjacentTerritories("China", new String[]{"Singapore", "Korea", "India", "Seediq", "Buryats", "Kurdish", "Khakas"});	
	addAdjacentTerritories("Khakas", new String[]{"Puyuma", "China", "Kurdish", "Tuvans"});
	addAdjacentTerritories("Buryats", new String[]{"Ukraine", "Seediq", "China", "Kurdish"});	
	addAdjacentTerritories("Kurdish", new String[]{"Buryats", "China", "Khakas", "Puyuma", "Pashtun"});
	addAdjacentTerritories("Puyuma", new String[]{"Kurdish", "Khakas", "Pashtun", "Tuvans"});	
	addAdjacentTerritories("Pashtun", new String[]{"Kurdish", "Puyuma", "Tuvans"});
	addAdjacentTerritories("Tuvans", new String[]{"Pashtun", "Puyuma", "Khakas", "Alaska"});
  }
  
  private void addAdjacentTerritories(String homeTerritoryName, String[] neighborTerritoriesNames){
  
	Territory[] neighborTerritories = new Territory[neighborTerritoriesNames.length];
	
	for (int i = 0; i < neighborTerritoriesNames.length; i++){
		neighborTerritories[i] = territories.get(neighborTerritoriesNames[i]);
	}

	ArrayList<Territory> adjacentTerritoriesList = new ArrayList<Territory>(Arrays.asList(neighborTerritories));
	territories.get(homeTerritoryName).setAdjacentTerritories(adjacentTerritoriesList);
  }
  
 }