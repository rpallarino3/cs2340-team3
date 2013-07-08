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
	  territories.put("Alaska",new Territory("Alaska",50,110));
	  territories.put("Northwest",new Territory("Northwest",150,110));
	  territories.put("Greenland",new Territory("Greenland",370,70));
	  territories.put("Alberta",new Territory("Alberta",150,160));
	  territories.put("Ontario",new Territory("Ontario",230,170));
	  territories.put("EasternCanada",new Territory("Eastern Canada",290,175));
	  territories.put("WestUS",new Territory("West US",165,220));
	  territories.put("EastUS",new Territory("East US",235,235));
	  territories.put("CentralAmerica",new Territory("Central America",170,275));
	  territories.put("Arawak", new Territory("Arawak",220,360));
	  territories.put("Kariri", new Territory("Kariri",290,440));
	  territories.put("Baniva", new Territory("Baniva",220,445));
	  territories.put("Ika", new Territory("Ika",245,510));
	  territories.put("Sweden", new Territory("Sweden",485,110));
	  territories.put("Britain", new Territory("Britain",380,190));
	  territories.put("Germany", new Territory("Germany",455,200));
	  territories.put("Ukraine", new Territory("Ukraine",530,170));
	  territories.put("Spain", new Territory("Spain",410,255));
	  territories.put("RomanEmpire", new Territory("Roman Empire",495,250));
	  territories.put("Egypt", new Territory("Egypt",490,335));
	  territories.put("Dinka", new Territory("Dinka",400,360));
	  territories.put("Xhosa", new Territory("Xhosa",515,400));
	  territories.put("Ngbandi", new Territory("Ngbandi",485,455));
	  territories.put("Sakalava", new Territory("Sakalava",480,540));
	  territories.put("Kurdish", new Territory("Kurdish",695,120));
	  territories.put("Pashtun", new Territory("Pashtun",800,110));
	  territories.put("Buryats", new Territory("Buryats",650,140));
	  territories.put("Puyuma", new Territory("Puyuma",780,165));
	  territories.put("Seediq", new Territory("Seediq",630,230));
	  territories.put("Khakas", new Territory("Khakas",800,215));
	  territories.put("China", new Territory("China",730,270));
	  territories.put("MiddleEast", new Territory("Middle East",560,320));
	  territories.put("India", new Territory("India",665,335));
	  territories.put("Korea", new Territory("Korea",740,340));
	  territories.put("Tuvans", new Territory("Tuvans",890,110));
	  territories.put("Singapore", new Territory("Singapore",775,415));
	  territories.put("NewZealand", new Territory("New Zealand",865,410));
	  territories.put("WesternAustralia", new Territory("Western Australia",780,520));
	  territories.put("EasternAustralia", new Territory("Eastern Australia",830,500));
	  
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