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
	
	Territory[] alaskaNeighbors = {territories.get("Northwest"), territories.get("Alberta"), territories.get("Tuvans")};
	ArrayList<Territory> alaskaList = new ArrayList<Territory>(Arrays.asList(alaskaNeighbors));
	territories.get("Alaska").setAdjacentTerritories(alaskaList);
	
	Territory[] northwestNeighbors = {territories.get("Alaska"), territories.get("Alberta"), territories.get("Greenland"), territories.get("Ontario")};
	ArrayList<Territory> northwestList = new ArrayList<Territory>(Arrays.asList(northwestNeighbors));
	territories.get("Northwest").setAdjacentTerritories(northwestList);
	
	Territory[] albertaNeighbors = {territories.get("Alaska"), territories.get("Northwest"), territories.get("Ontario"), territories.get("WestUS")};
	ArrayList<Territory> albertaList = new ArrayList<Territory>(Arrays.asList(albertaNeighbors));
	territories.get("Alberta").setAdjacentTerritories(albertaList);

	Territory[] ontarioNeighbors = {territories.get("Alberta"), territories.get("Northwest"), territories.get("WestUS"), territories.get("EastUS"), territories.get("EasternCanada"), territories.get("Greenland")};
	ArrayList<Territory> ontarioList = new ArrayList<Territory>(Arrays.asList(ontarioNeighbors));
	territories.get("Ontario").setAdjacentTerritories(ontarioList);
	
	Territory[] greenlandNeighbors = {territories.get("Northwest"), territories.get("Ontario"), territories.get("EasternCanada"), territories.get("Sweden")};
	ArrayList<Territory> greenlandList = new ArrayList<Territory>(Arrays.asList(greenlandNeighbors));
	territories.get("Greenland").setAdjacentTerritories(greenlandList);	
	
	Territory[] easterncanadaNeighbors = {territories.get("Ontario"), territories.get("Greenland"), territories.get("EastUS")};
	ArrayList<Territory> easterncanadaList = new ArrayList<Territory>(Arrays.asList(easterncanadaNeighbors));
	territories.get("EasternCanada").setAdjacentTerritories(easterncanadaList);	
	
	Territory[] westusNeighbors = {territories.get("Alberta"), territories.get("Ontario"), territories.get("EastUS"), territories.get("CentralAmerica")};
	ArrayList<Territory> westusList = new ArrayList<Territory>(Arrays.asList(westusNeighbors));
	territories.get("WestUS").setAdjacentTerritories(westusList);		
	
	Territory[] eastusNeighbors = {territories.get("WestUS"), territories.get("Ontario"), territories.get("EasternCanada"), territories.get("CentralAmerica")};
	ArrayList<Territory> eastusList = new ArrayList<Territory>(Arrays.asList(eastusNeighbors));
	territories.get("EastUS").setAdjacentTerritories(eastusList);	
	
	Territory[] centralamericaNeighbors = {territories.get("WestUS"), territories.get("EastUS"), territories.get("Arawak")};
	ArrayList<Territory> centralamericaList = new ArrayList<Territory>(Arrays.asList(centralamericaNeighbors));
	territories.get("CentralAmerica").setAdjacentTerritories(centralamericaList);	
	
	Territory[] arawakNeighbors = {territories.get("CentralAmerica"), territories.get("Baniva"), territories.get("Kariri")};
	ArrayList<Territory> arawakList = new ArrayList<Territory>(Arrays.asList(arawakNeighbors));
	territories.get("Arawak").setAdjacentTerritories(arawakList);

	Territory[] banivaNeighbors = {territories.get("Arawak"), territories.get("Kariri"), territories.get("Ika")};
	ArrayList<Territory> banivaList = new ArrayList<Territory>(Arrays.asList(banivaNeighbors));
	territories.get("Baniva").setAdjacentTerritories(banivaList);
	
	Territory[] kaririNeighbors = {territories.get("Arawak"), territories.get("Baniva"), territories.get("Ika"), territories.get("Dinka")};
	ArrayList<Territory> kaririList = new ArrayList<Territory>(Arrays.asList(kaririNeighbors));
	territories.get("Kariri").setAdjacentTerritories(kaririList);

	Territory[] ikaNeighbors = {territories.get("Baniva")};
	ArrayList<Territory> ikaList = new ArrayList<Territory>(Arrays.asList(ikaNeighbors));
	territories.get("Ika").setAdjacentTerritories(ikaList);		
	
	Territory[] dinkaNeighbors = {territories.get("Kariri"), territories.get("Spain"), territories.get("RomanEmpire"), territories.get("Egypt"), territories.get("Xhosa"), territories.get("Ngbandi"), territories.get("Sakalava")};
	ArrayList<Territory> dinkaList = new ArrayList<Territory>(Arrays.asList(dinkaNeighbors));
	territories.get("Dinka").setAdjacentTerritories(dinkaList);
	
	Territory[] sakalavaNeighbors = {territories.get("Ngbandi"), territories.get("Xhosa")};
	ArrayList<Territory> sakalavaList = new ArrayList<Territory>(Arrays.asList(sakalavaNeighbors));
	territories.get("Sakalava").setAdjacentTerritories(sakalavaList);
	
	Territory[] ngbandiNeighbors = {territories.get("Dinka"), territories.get("Xhosa"), territories.get("Sakalava")};
	ArrayList<Territory> ngbandiList = new ArrayList<Territory>(Arrays.asList(ngbandiNeighbors));
	territories.get("Ngbandi").setAdjacentTerritories(ngbandiList);

	Territory[] xhosaNeighbors = {territories.get("Egypt"), territories.get("Dinka"), territories.get("Ngbandi"), territories.get("Sakalava"), territories.get("MiddleEast")};
	ArrayList<Territory> xhosaList = new ArrayList<Territory>(Arrays.asList(xhosaNeighbors));
	territories.get("Xhosa").setAdjacentTerritories(xhosaList);
	
	Territory[] egyptNeighbors = {territories.get("Dinka"), territories.get("RomanEmpire"), territories.get("MiddleEast"), territories.get("Xhosa")};
	ArrayList<Territory> egyptList = new ArrayList<Territory>(Arrays.asList(egyptNeighbors));
	territories.get("Egypt").setAdjacentTerritories(egyptList);
	
	Territory[] westernaustraliaNeighbors = {territories.get("EasternAustralia"), territories.get("Singapore"), territories.get("NewZealand")};
	ArrayList<Territory> westernaustraliaList = new ArrayList<Territory>(Arrays.asList(westernaustraliaNeighbors));
	territories.get("WesternAustralia").setAdjacentTerritories(westernaustraliaList);
	
	Territory[] easternaustraliaNeighbors = {territories.get("WesternAustralia"), territories.get("Singapore"), territories.get("NewZealand")};
	ArrayList<Territory> easternaustraliaList = new ArrayList<Territory>(Arrays.asList(easternaustraliaNeighbors));
	territories.get("EasternAustralia").setAdjacentTerritories(easternaustraliaList);
	
	Territory[] singaporeNeighbors = {territories.get("WesternAustralia"), territories.get("EasternAustralia"), territories.get("NewZealand"), territories.get("India"), territories.get("Korea"), territories.get("China")};
	ArrayList<Territory> singaporeList = new ArrayList<Territory>(Arrays.asList(singaporeNeighbors));
	territories.get("Singapore").setAdjacentTerritories(singaporeList);
	
	Territory[] newzealandNeighbors = {territories.get("WesternAustralia"), territories.get("Singapore"), territories.get("EasternAustralia")};
	ArrayList<Territory> newzealandList = new ArrayList<Territory>(Arrays.asList(newzealandNeighbors));
	territories.get("NewZealand").setAdjacentTerritories(newzealandList);
	
	Territory[] swedenNeighbors = {territories.get("Greenland"), territories.get("Britain"), territories.get("Germany"), territories.get("Ukraine")};
	ArrayList<Territory> swedenList = new ArrayList<Territory>(Arrays.asList(swedenNeighbors));
	territories.get("Sweden").setAdjacentTerritories(swedenList);
	
	Territory[] britainNeighbors = {territories.get("Sweden"), territories.get("Spain"), territories.get("Germany")};
	ArrayList<Territory> britainList = new ArrayList<Territory>(Arrays.asList(britainNeighbors));
	territories.get("Britain").setAdjacentTerritories(britainList);
	
	Territory[] spainNeighbors = {territories.get("Dinka"), territories.get("Britain"), territories.get("Germany"), territories.get("RomanEmpire")};
	ArrayList<Territory> spainList = new ArrayList<Territory>(Arrays.asList(spainNeighbors));
	territories.get("Spain").setAdjacentTerritories(spainList);
	
	Territory[] germanyNeighbors = {territories.get("Britain"), territories.get("Sweden"), territories.get("Spain"), territories.get("RomanEmpire"), territories.get("Ukraine")};
	ArrayList<Territory> germanyList = new ArrayList<Territory>(Arrays.asList(germanyNeighbors));
	territories.get("Germany").setAdjacentTerritories(germanyList);

	Territory[] romanempireNeighbors = {territories.get("Spain"), territories.get("Germany"), territories.get("Dinka"), territories.get("Egypt"), territories.get("Ukraine"), territories.get("MiddleEast")};
	ArrayList<Territory> romanempireList = new ArrayList<Territory>(Arrays.asList(romanempireNeighbors));
	territories.get("RomanEmpire").setAdjacentTerritories(romanempireList);
	
	Territory[] ukraineNeighbors = {territories.get("Sweden"), territories.get("Germany"), territories.get("RomanEmpire"), territories.get("Seediq"), territories.get("Buryats"), territories.get("MiddleEast")};
	ArrayList<Territory> ukraineList = new ArrayList<Territory>(Arrays.asList(ukraineNeighbors));
	territories.get("Ukraine").setAdjacentTerritories(ukraineList);
	
	Territory[] middleeastNeighbors = {territories.get("RomanEmpire"), territories.get("Ukraine"), territories.get("Egypt"), territories.get("Xhosa"), territories.get("Seediq"), territories.get("India")};
	ArrayList<Territory> middleeastList = new ArrayList<Territory>(Arrays.asList(middleeastNeighbors));
	territories.get("MiddleEast").setAdjacentTerritories(middleeastList);
	
	Territory[] indiaNeighbors = {territories.get("MiddleEast"), territories.get("Seediq"), territories.get("China"), territories.get("Korea"), territories.get("Singapore")};
	ArrayList<Territory> indiaList = new ArrayList<Territory>(Arrays.asList(indiaNeighbors));
	territories.get("India").setAdjacentTerritories(indiaList);
	
	Territory[] koreaNeighbors = {territories.get("India"), territories.get("China"), territories.get("Singapore")};
	ArrayList<Territory> koreaList = new ArrayList<Territory>(Arrays.asList(koreaNeighbors));
	territories.get("Korea").setAdjacentTerritories(koreaList);
	
	Territory[] seediqNeighbors = {territories.get("Ukraine"), territories.get("MiddleEast"), territories.get("India"), territories.get("China"), territories.get("Buryats")};
	ArrayList<Territory> seediqList = new ArrayList<Territory>(Arrays.asList(seediqNeighbors));
	territories.get("Seediq").setAdjacentTerritories(seediqList);
	
	Territory[] chinaNeighbors = {territories.get("Singapore"), territories.get("Korea"), territories.get("India"), territories.get("Seediq"), territories.get("Buryats"), territories.get("Kurdish"), territories.get("Khakas")};
	ArrayList<Territory> chinaList = new ArrayList<Territory>(Arrays.asList(chinaNeighbors));
	territories.get("China").setAdjacentTerritories(chinaList);
	
	Territory[] khakasNeighbors = {territories.get("Puyuma"), territories.get("China"), territories.get("Kurdish"), territories.get("Tuvans")};
	ArrayList<Territory> khakasList = new ArrayList<Territory>(Arrays.asList(khakasNeighbors));
	territories.get("Khakas").setAdjacentTerritories(khakasList);
	
	Territory[] buryatsNeighbors = {territories.get("Ukraine"), territories.get("Seediq"), territories.get("China"), territories.get("Kurdish")};
	ArrayList<Territory> buryatsList = new ArrayList<Territory>(Arrays.asList(buryatsNeighbors));
	territories.get("Buryats").setAdjacentTerritories(buryatsList);
	
	Territory[] kurdishNeighbors = {territories.get("Buryats"), territories.get("China"), territories.get("Khakas"), territories.get("Puyuma"), territories.get("Pashtun")};
	ArrayList<Territory> kurdishList = new ArrayList<Territory>(Arrays.asList(kurdishNeighbors));
	territories.get("Kurdish").setAdjacentTerritories(kurdishList);
	
	Territory[] puyumaNeighbors = {territories.get("Kurdish"), territories.get("Khakas"), territories.get("Pashtun"), territories.get("Tuvans")};
	ArrayList<Territory> puyumaList = new ArrayList<Territory>(Arrays.asList(puyumaNeighbors));
	territories.get("Puyuma").setAdjacentTerritories(puyumaList);
	
	Territory[] pashtunNeighbors = {territories.get("Kurdish"), territories.get("Puyuma"), territories.get("Tuvans")};
	ArrayList<Territory> pashtunList = new ArrayList<Territory>(Arrays.asList(pashtunNeighbors));
	territories.get("Pashtun").setAdjacentTerritories(pashtunList);
	
	Territory[] tuvansNeighbors = {territories.get("Pashtun"), territories.get("Puyuma"), territories.get("Khakas"), territories.get("Alaska")};
	ArrayList<Territory> tuvansList = new ArrayList<Territory>(Arrays.asList(tuvansNeighbors));
	territories.get("Tuvans").setAdjacentTerritories(tuvansList);
  }
  
 }