package ac.infoscience.mc.test2;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import ac.otago.infoscience.mcws2.Util;

public class TestMcrestMale {

	String head = "<?xml version=\"1.0\"?><MR><RISK>";
	
	static HashMap numIn100 = new HashMap();
	
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numIn100.put("1_30", "64");
		numIn100.put("1_37", "38");
		numIn100.put("1_45", "20");
		numIn100.put("1_50", "15");
		numIn100.put("1_70", "4");
		
		numIn100.put("2_30", "29");
		numIn100.put("2_37", "23");
		numIn100.put("2_45", "13");
		numIn100.put("2_50", "10");
		numIn100.put("2_70", "4");
		
		numIn100.put("3_30", "87");
		numIn100.put("3_37", "30");
		numIn100.put("3_45", "24");
		numIn100.put("3_50", "14");
		numIn100.put("3_70", "6");
		
		numIn100.put("4_30", "42");
		numIn100.put("4_37", "29");
		numIn100.put("4_45", "19");
		numIn100.put("4_50", "13");
		numIn100.put("4_70", "5");
		
		numIn100.put("5_30", "200");
		numIn100.put("5_45", "61");
		numIn100.put("5_50", "46");
		numIn100.put("5_53", "36");
		numIn100.put("5_61", "18");
		numIn100.put("5_70", "12");
		
		numIn100.put("6_30", "90");
		numIn100.put("6_45", "39");
		numIn100.put("6_50", "29");
		numIn100.put("6_53", "25");
		numIn100.put("6_61", "18");
		numIn100.put("6_70", "12");
		
		numIn100.put("7_30", "273");
		numIn100.put("7_45", "74");
		numIn100.put("7_50", "42");
		numIn100.put("7_53", "36");
		numIn100.put("7_61", "22");
		numIn100.put("7_70", "17");
		
		numIn100.put("8_30", "130");
		numIn100.put("8_45", "58");
		numIn100.put("8_50", "39");
		numIn100.put("8_53", "32");
		numIn100.put("8_61", "24");
		numIn100.put("8_70", "15");
		
		numIn100.put("9_30", "800");
		numIn100.put("9_37", "474");
		numIn100.put("9_50", "184");
		numIn100.put("9_53", "142");
		numIn100.put("9_70", "47");
		
		numIn100.put("10_30", "358");
		numIn100.put("10_37", "286");
		numIn100.put("10_50", "114");
		numIn100.put("10_53", "99");
		numIn100.put("10_70", "47");
		
		numIn100.put("11_30", "1,092");
		numIn100.put("11_37", "369");
		numIn100.put("11_50", "167");
		numIn100.put("11_53", "143");
		numIn100.put("11_70", "67");
		
		numIn100.put("12_30", "518");
		numIn100.put("12_37", "365");
		numIn100.put("12_50", "157");
		numIn100.put("12_53", "126");
		numIn100.put("12_70", "57");
		
		numIn100.put("13_30", "24,614");
		numIn100.put("13_50", "5,650");
		numIn100.put("13_61", "2,172");
		numIn100.put("13_70", "1,421");
		
		numIn100.put("14_30", "10,997");
		numIn100.put("14_50", "3,489");
		numIn100.put("14_61", "2,174");
		numIn100.put("14_70", "1,430");
		
		numIn100.put("15_30", "33,600");
		numIn100.put("15_50", "5,136");
		numIn100.put("15_61", "2,683");
		numIn100.put("15_70", "2,041");
		
		numIn100.put("16_30", "15,925");
		numIn100.put("16_50", "4,808");
		numIn100.put("16_61", "2,916");
		numIn100.put("16_70", "1,743");
		
	}

	String getTail_old(String risk) {
		String result = "";
		String cat = Util.calculateRiskCategory(new Float(risk));
		String desc = Util.getRiskDescription(cat,new Float(risk));
		result = "</RISK><RISK_CATEGORY>" + cat + "</RISK_CATEGORY>" + "<DESCRIPTION>" + desc + "</DESCRIPTION></MR>";
		return result;

	}
	
	
	String getTail(String risk, String numIn100) {
		System.out.println("risk:"+risk);
		String result ="";
		String cat = Util.calculateRiskCategory(new Float(risk));
		String desc = Util.getRiskDescriptionTest(cat,new Float(risk),numIn100);
		result ="</RISK><RISK_CATEGORY>" + cat +"</RISK_CATEGORY>" +
				"<DESCRIPTION>" + desc +"</DESCRIPTION></MR>";
		System.out.println(result);
		return result;
		
	}
	
	/*
	 * These next 2 methods are almost the same as the previous 2, except
	 * that we do not calculate the risk category, but we pass in the
	 * expected description.
	 */
	
		String getTailB(String catExpected, String descExpected,  String numIn100 ) {
			String result = "";
			result = "</RISK><RISK_CATEGORY>" + catExpected + "</RISK_CATEGORY>" + "<DESCRIPTION>" + descExpected + "</DESCRIPTION></MR>";
			return result;

		}

	public void genericTestMale(String parms, String risk, String numIn100 ) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIMale(parms));

		String result = service.accept(MediaType.TEXT_XML).get(String.class);

		String expected = head + risk + getTail(risk, numIn100);
		assertEquals("x ", expected, result);

	}
	
	
	

		public void genericTestMaleB(String id, String parms, String risk,String catExpected, String descExpected,  String numIn100 ) {

			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);

			WebResource service = client.resource(getBaseURIMale(parms));

			String result = service.accept(MediaType.TEXT_XML).get(String.class);

			String expected = head + risk + getTailB(catExpected, descExpected, numIn100);
			
			int ind = expected.indexOf("</DESCRIPTION");
			
			String expectedFull = expected.substring(0,ind) + "The risk is " + Util.toPerc(new Float(risk)) + "% in 5 years. \n " 
			
			+ "1 in "	+ numIn100 + " people of the same age and gender, and with the same characteristics as this person would be expected to develop melanoma in the next 5 years."	
				+	"</DESCRIPTION></MR>";
			
			assertEquals("x ", expectedFull, result);

		}

	@Test
	public void MaleTes1_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0156";
		genericTestMale( parms, risk, (String) numIn100.get("1_30"));
		

	}
	
	// test the risk category description
	@Test
	public void MaleTes1_30B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0156";
		genericTestMaleB("1-1", parms, risk,Util.HIGH_RISK,Util.HIGH_RISK_TEXT, (String) numIn100.get("1_30"));
		

	}

	@Test
	public void MaleTes1_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=north";
		String risk = "0.0263";
		genericTestMale( parms, risk, (String) numIn100.get("1_37"));
		

	}
	
	@Test
	public void MaleTes1_37B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=north";
		String risk = "0.0263";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("1_37"));
		

	}
	

	@Test
	public void MaleTes1_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=north";
		String risk = "0.0508";
		
		genericTestMale( parms, risk, (String) numIn100.get("1_45"));
		

	}
	
	@Test
	public void MaleTes1_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0663";
		genericTestMale( parms, risk, (String) numIn100.get("1_50"));

	}
	@Test
	public void MaleTes1_50B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0663";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("1_50"));

	}

	@Test
	public void MaleTes1_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.2377";
		genericTestMale( parms, risk, (String) numIn100.get("1_70"));

	}
	
	@Test
	public void MaleTes1_70B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.2377";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("1_70"));

	}


	@Test
	public void MaleTes2_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=midland";
		String risk = "0.0431";
		genericTestMale( parms, risk, (String) numIn100.get("2_37"));

	}
	
	@Test
	public void MaleTes2_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=midland";
		String risk = "0.0793";
		genericTestMale( parms, risk, (String) numIn100.get("2_45"));

	}

	@Test
	public void MaleTes2_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.1051";
		genericTestMale( parms, risk, (String) numIn100.get("2_50"));

	}

	@Test
	public void MaleTes2_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.2364";
		genericTestMale( parms, risk, (String) numIn100.get("2_70"));

	}

	@Test
	public void MaleTes3_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0115";
		genericTestMale( parms, risk, (String) numIn100.get("3_30"));

	}


	@Test
	public void MaleTes3_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=central";
		String risk = "0.0336";
		genericTestMale( parms, risk, (String) numIn100.get("3_37"));

	}

	@Test
	public void MaleTes3_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=central";
		String risk = "0.0418";
		genericTestMale( parms, risk, (String) numIn100.get("3_45"));

	}

	@Test
	public void MaleTes3_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0727";
		genericTestMale( parms, risk, (String) numIn100.get("3_50"));

	}

	@Test
	public void MaleTes3_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.1725";
		genericTestMale( parms, risk, (String) numIn100.get("3_70"));

	}

	@Test
	public void MaleTes4_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0240";
		genericTestMale( parms, risk, (String) numIn100.get("4_30"));

	}
	
	@Test
	public void MaleTes4_30B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0240";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("4_30"));
	}

	@Test
	public void MaleTes4_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=south";
		String risk = "0.0339";
		genericTestMale( parms, risk, (String) numIn100.get("4_37"));

	}@Test
	public void MaleTes4_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=south";
		String risk = "0.0534";
		genericTestMale( parms, risk, (String) numIn100.get("4_45"));

	}

	@Test
	public void MaleTes4_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0774";
		genericTestMale( parms, risk, (String) numIn100.get("4_50"));

	}

	@Test
	public void MaleTes4_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.1987";
		genericTestMale( parms, risk, (String) numIn100.get("4_70"));

	}

	@Test
	public void MaleTes4_70B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.1987";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("4_70"));

	}

	//
	// MEDIUM HIGH
	//
	//
	@Test
	public void MaleTes5_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0050";
		genericTestMale( parms, risk, (String) numIn100.get("5_30"));

	}
	
	@Test
	public void MaleTes5_30B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0050";
		genericTestMaleB("1-1", parms, risk,Util.MODERATE_RISK,Util.MODERATE_RISK_TEXT, (String) numIn100.get("5_30"));

	}

	
	@Test
	public void MaleTes5_45() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=45&area=north";
		String risk = "0.0164";
		genericTestMale( parms, risk, (String) numIn100.get("5_45"));

	}

	
	@Test
	public void MaleTes5_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0216";
		genericTestMale( parms, risk, (String) numIn100.get("5_50"));

	}
	

	@Test
	public void MaleTes5_50B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0216";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("5_50"));

	}

	
	@Test
	public void MaleTes5_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=north";
		String risk = "0.0279";
		genericTestMale( parms, risk, (String) numIn100.get("5_53"));

	}
	
	@Test
	public void MaleTes5_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=north";
		String risk = "0.0552";
		genericTestMale( parms, risk, (String) numIn100.get("5_61"));

	}
	
	@Test
	public void MaleTes5_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.0830";
		genericTestMale( parms, risk, (String) numIn100.get("5_70"));

	}

	@Test
	public void MaleTes6_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0112";
		genericTestMale( parms, risk, (String) numIn100.get("6_30"));

	}
	
	@Test
	public void MaleTes6_30B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0112";
		genericTestMaleB("1-1", parms, risk,Util.HIGH_RISK,Util.HIGH_RISK_TEXT, (String) numIn100.get("6_30"));

	}

	@Test
	public void MaleTes6_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.0347";
		genericTestMale( parms, risk, (String) numIn100.get("6_50"));

	}
	
	@Test
	public void MaleTes6_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=midland";
		String risk = "0.0397";
		genericTestMale( parms, risk, (String) numIn100.get("6_53"));

	}@Test
	public void MaleTes6_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=midland";
		String risk = "0.0552";
		genericTestMale( parms, risk, (String) numIn100.get("6_61"));

	}

	@Test
	public void MaleTes6_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.0826";
		genericTestMale( parms, risk, (String) numIn100.get("6_70"));

	}

	@Test
	public void MaleTes7_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0037";
		genericTestMale( parms, risk, (String) numIn100.get("7_30"));

	}

	@Test
	public void MaleTes7_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0237";
		genericTestMale( parms, risk, (String) numIn100.get("7_50"));

	}
	
	@Test
	public void MaleTes7_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=central";
		String risk = "0.0277";
		genericTestMale( parms, risk, (String) numIn100.get("7_53"));

	}
	@Test
	public void MaleTes7_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=central";
		String risk = "0.0449";
		genericTestMale( parms, risk, (String) numIn100.get("7_61"));

	}

	@Test
	public void MaleTes7_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.0586";
		genericTestMale( parms, risk, (String) numIn100.get("7_70"));

	}

	@Test
	public void MaleTes8_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0077";
		genericTestMale( parms, risk, (String) numIn100.get("8_30"));

	}

	@Test
	public void MaleTes8_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0253";
		genericTestMale( parms, risk, (String) numIn100.get("8_50"));

	}
	
	@Test
	public void MaleTes8_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=south";
		String risk = "0.0314";
		genericTestMale( parms, risk, (String) numIn100.get("8_53"));

	}@Test
	public void MaleTes8_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=south";
		String risk = "0.0414";
		genericTestMale( parms, risk, (String) numIn100.get("8_61"));

	}

	@Test
	public void MaleTes8_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0683";
		genericTestMale( parms, risk, (String) numIn100.get("8_70"));

	}

	@Test
	public void MaleTes8_70B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0683";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("8_70"));

	}
	
	//
	//
	// MEDIUM LOW RISK
	//
	//
	@Test
	public void MaleTes9_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0012";
		genericTestMale( parms, risk, (String) numIn100.get("9_30"));

	}

	@Test
	public void MaleTes9_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=north";
		String risk = "0.0021";
		genericTestMale( parms, risk, (String) numIn100.get("9_37"));

	}
	@Test
	public void MaleTes9_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0054";
		genericTestMale( parms, risk, (String) numIn100.get("9_50"));

	}
	
	@Test
	public void MaleTes9_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=north";
		String risk = "0.0070";
		genericTestMale( parms, risk, (String) numIn100.get("9_53"));

	}

	@Test
	public void MaleTes9_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.0214";
		genericTestMale( parms, risk, (String) numIn100.get("9_70"));

	}

	@Test
	public void MaleTes10_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0028";
		genericTestMale( parms, risk, (String) numIn100.get("10_30"));

	}
	
	@Test
	public void MaleTes10_30B() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0028";
		genericTestMaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT, (String) numIn100.get("10_30"));

	}

	@Test
	public void MaleTes10_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=midland";
		String risk = "0.0035";
		genericTestMale( parms, risk, (String) numIn100.get("10_37"));

	}

	@Test
	public void MaleTes10_37B() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=midland";
		String risk = "0.0035";
		genericTestMaleB("1-1", parms, risk, Util.LOW_RISK,Util.LOW_RISK_TEXT, (String) numIn100.get("10_37"));

	}

	
	@Test
	public void MaleTes10_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.0088";
		genericTestMale( parms, risk, (String) numIn100.get("10_50"));

	}
	
	@Test
	public void MaleTes10_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=midland";
		String risk = "0.0101";
		genericTestMale( parms, risk, (String) numIn100.get("10_53"));

	}

	@Test
	public void MaleTes10_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.0213";
		genericTestMale( parms, risk, (String) numIn100.get("10_70"));

	}

	@Test
	public void MaleTes11_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0009";
		genericTestMale( parms, risk, (String) numIn100.get("11_30"));

	}
	
	@Test
	public void MaleTes11_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=central";
		String risk = "0.0027";
		genericTestMale( parms, risk, (String) numIn100.get("11_37"));

	}

	@Test
	public void MaleTes11_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0060";
		genericTestMale( parms, risk, (String) numIn100.get("11_50"));

	}


	@Test
	public void MaleTes11_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=central";
		String risk = "0.0070";
		genericTestMale( parms, risk, (String) numIn100.get("11_53"));

	}

	
	@Test
	public void MaleTes11_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.0150";
		genericTestMale( parms, risk, (String) numIn100.get("11_70"));

	}

	@Test
	public void MaleTes12_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0019";
		genericTestMale( parms, risk, (String) numIn100.get("12_30"));

	}
	
	@Test
	public void MaleTes12_30B() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0019";
		genericTestMaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT, (String) numIn100.get("12_30"));

	}
	

	@Test
	public void MaleTes12_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=south";
		String risk = "0.0027";
		genericTestMale( parms, risk, (String) numIn100.get("12_37"));

	}


	@Test
	public void MaleTes12_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0064";
		genericTestMale( parms, risk, (String) numIn100.get("12_50"));

	}

	@Test
	public void MaleTes12_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=south";
		String risk = "0.0079";
		genericTestMale( parms, risk, (String) numIn100.get("12_53"));

	}

	
	@Test
	public void MaleTes12_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0175";
		genericTestMale( parms, risk, (String) numIn100.get("12_70"));

	}

	//
	//
	// LOWEST RELATIVE RISK
	//
	//
	@Test
	public void MaleTes13_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=north";
		String risk = "0.0000";
		genericTestMale( parms, risk, (String) numIn100.get("13_30"));

	}
	
	@Test
	public void MaleTes13_30B() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=north";
		String risk = "0.0000";
		genericTestMaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT, (String) numIn100.get("13_30"));

	}

	

	
	@Test
	public void MaleTes13_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=north";
		String risk = "0.0002";
		genericTestMale( parms, risk, (String) numIn100.get("13_50"));

	}

	@Test
	public void MaleTes13_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=north";
		String risk = "0.0005";
		genericTestMale( parms, risk, (String) numIn100.get("13_61"));

	}
	
	@Test
	public void MaleTes13_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=north";
		String risk = "0.0007";
		genericTestMale( parms, risk, (String) numIn100.get("13_70"));

	}

	@Test
	public void MaleTes14_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=midland";
		String risk = "0.0001";
		genericTestMale( parms, risk, (String) numIn100.get("14_30"));

	}

	@Test
	public void MaleTes14_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=midland";
		String risk = "0.0003";
		genericTestMale( parms, risk, (String) numIn100.get("14_50"));

	}


	@Test
	public void MaleTes14_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=midland";
		String risk = "0.0005";
		genericTestMale( parms, risk, (String) numIn100.get("14_61"));

	}

	
	@Test
	public void MaleTes14_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=midland";
		String risk = "0.0007";
		genericTestMale( parms, risk, (String) numIn100.get("14_70"));

	}

	@Test
	public void MaleTes15_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=central";
		String risk = "0.0000";
		genericTestMale( parms, risk, (String) numIn100.get("15_30"));

	}

	@Test
	public void MaleTes15_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=central";
		String risk = "0.0002";
		genericTestMale( parms, risk, (String) numIn100.get("15_50"));

	}


	@Test
	public void MaleTes15_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=central";
		String risk = "0.0004";
		genericTestMale( parms, risk, (String) numIn100.get("15_61"));

	}


	
	@Test
	public void MaleTes15_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=central";
		String risk = "0.0005";
		genericTestMale( parms, risk, (String) numIn100.get("15_70"));

	}

	@Test
	public void MaleTes16_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=south";
		String risk = "0.0001";
		genericTestMale( parms, risk, (String) numIn100.get("16_30"));

	}

	@Test
	public void MaleTes16_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=south";
		String risk = "0.0002";
		genericTestMale( parms, risk, (String) numIn100.get("16_50"));

	}


	@Test
	public void MaleTes16_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=south";
		String risk = "0.0003";
		genericTestMale( parms, risk, (String) numIn100.get("16_61"));

	}

	
	@Test
	public void MaleTes16_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=south";
		String risk = "0.0006";
		genericTestMale( parms, risk, (String) numIn100.get("16_70"));

	}

	private static URI getBaseURIMale(String parms) {

		/// infoscience deployment production.
		// return
		// UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
		// String url =
		// "http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcf?" + parms;

		// local tomcat

		String host = "http://soblinux01.otago.ac.nz:8090/";

		boolean testLocal = true;
		if (testLocal) {
			host = "http://localhost:8080/";

		}

		String url = host + "mcrest2/getmelcalcm?" + parms;
		//url = host + "mcresttest/getmelcalcm?" + parms;
		return UriBuilder.fromUri(url).build();

	}

}
