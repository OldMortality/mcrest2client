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

public class TestMcrestFemale {

	
	String head ="<?xml version=\"1.0\"?><MR><RISK>";
	
	static HashMap numIn100 = new HashMap();
	
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numIn100.put("1_30", "64");
		numIn100.put("1_48", "19");
		numIn100.put("5_30", "149");
		numIn100.put("5_32", "119");
		numIn100.put("9_30", "1033");
		numIn100.put("9_70", "141");
		numIn100.put("13_50", "4,929");
		
		
		
	}
  
	String getTail_old(String risk) {
		System.out.println("risk:"+risk);
		String result ="";
		String cat = Util.calculateRiskCategory(new Float(risk));
		String desc = Util.getRiskDescription(cat,new Float(risk));
		result ="</RISK><RISK_CATEGORY>" + cat +"</RISK_CATEGORY>" +
				"<DESCRIPTION>" + desc +"</DESCRIPTION></MR>";
		System.out.println(result);
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
	
	public void genericTestFemale(String parms, String risk, String numIn100) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIFemale(parms));

		String result = service.accept(MediaType.TEXT_XML).get(String.class);
		
		String expected = head + risk + getTail(risk,numIn100);
		assertEquals("x", expected, result);

	}
	
	public void genericTestFemaleB(String id, String parms, String risk,String catExpected, String descExpected,  String numIn100 ) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIFemale(parms));
		
		
		String result = service.accept(MediaType.TEXT_XML).get(String.class);
		
		String expected = head + risk + getTailB(catExpected, descExpected, numIn100);
		
		int ind = expected.indexOf("</DESCRIPTION");
		
		String expectedFull = expected.substring(0,ind) + "The risk is " + Util.toPerc(new Float(risk)) + "% in 5 years. \n " 
		
		+ "1 in "	+ numIn100 + " people of the same age and gender, and with the same characteristics as this person would be expected to develop melanoma in the next 5 years."	
			+	"</DESCRIPTION></MR>";
		
		assertEquals("x ", expectedFull, result);

	}

	
	
	

	// 
	// 
	// VERY HIGH RISK
	//
	//
	@Test
	public void female1_30() {

		String parms =
				"occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=north";

		String risk ="0.0155";
		String numIn100 ="64";
		genericTestFemale( parms, risk,numIn100);

	}
	
	// test the risk category description
	@Test
	public void female1_30B() {

		String parms =
				"occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=north";

		String risk ="0.0155";
		genericTestFemaleB("1-1", parms, risk,Util.HIGH_RISK,Util.HIGH_RISK_TEXT, (String) numIn100.get("1_30"));
			

		}


	@Test
	public void female1_48() {

		String parms =
				"occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=north";

		String risk ="0.0534";
		String numIn100 ="19";
		
		genericTestFemale( parms, risk,numIn100);

	}

	// test the risk category description
	@Test
	public void female1_48B() {
		String parms =
		"occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=north";

		String risk ="0.0534";			
		genericTestFemaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT, (String) numIn100.get("1_48"));
				

	}
	
	@Test
	public void female1_50() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=north";

		String risk ="0.0548";
		String numIn100 ="18";
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female1_56() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=north";

		String risk ="0.0768";
		String numIn100 ="13";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female1_70() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=north";

		String risk ="0.1085";
		String numIn100 ="9";
		genericTestFemale(parms, risk, numIn100);
		

	}

	@Test
	public void female2_30() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=midland";

		String risk ="0.0430";
		
		String numIn100 ="23";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female2_48() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=midland";

		String risk ="0.0755";
		String numIn100 ="13";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female2_50() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=midland";

		String risk ="0.0837";
		String numIn100 ="12";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female2_56() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=midland";

		String risk ="0.1020";
		String numIn100 ="10";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female2_70() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=midland";

		String risk ="0.1469";
		String numIn100 ="7";
		
		genericTestFemale( parms, risk,numIn100);

	}
	
	@Test
	public void female3_30() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=central";

		String risk ="0.0239";
		String numIn100 ="42";
		
		genericTestFemale( parms, risk,numIn100);

	}

	@Test
	public void female3_48() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=central";

		String risk ="0.0472";
		String numIn100 ="21";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female3_50() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=central";

		String risk ="0.0481";
		String numIn100 ="21";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female3_56() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=central";
		
		
		String risk ="0.0677";
		String numIn100 ="15";
		genericTestFemale(parms, risk,numIn100);

	}

	
	@Test
	public void female3_70() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=central";

		String risk ="0.0968";
		String numIn100 ="10";
		
		genericTestFemale(parms, risk,numIn100);

	}

	
	@Test
	public void female4_30() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=south";

		String risk ="0.0272";
		String numIn100 ="37";
		
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female4_48() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=south";

		String risk ="0.0675";
		String numIn100 ="15";
		genericTestFemale(parms, risk,numIn100);

	}

	// ////////////////////////

		 
	
	

	@Test
	public void female4_50() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=south";

		String risk ="0.0709";
		String numIn100 ="14";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female4_56() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=south";

		String risk ="0.0832";
		String numIn100 ="12";
		genericTestFemale(parms, risk,numIn100);

	}
	
 
	




	@Test
	public void female4_70() {

		String parms ="occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=south";

		String risk ="0.1330";
		String numIn100 ="8";
		
		genericTestFemale(parms, risk,numIn100);

	}
	// //////////////////

	 

 

	

	//
	//
	// MEDIUM HIGH RELATIVE RISK
	//
	//
	@Test
	public void female5_30() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=north";

		String risk ="0.0067";
		String numIn100 ="149";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	// test the risk category description
	@Test
	public void female5_30B() {
		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=north";

		String risk ="0.0067";
		genericTestFemaleB("1-1", parms, risk,Util.MODERATE_RISK,Util.MODERATE_RISK_TEXT, (String) numIn100.get("5_30"));
					

	}


	@Test
	public void female5_32() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=north";

		String risk ="0.0084";
		String numIn100 ="119";
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female5_35() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=north";

		String risk ="0.0109";
		String numIn100 ="92";
		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female5_40() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=north";

		String risk ="0.0198";
		String numIn100 ="51";
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female5_50() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=north";

		String risk ="0.0239";
		String numIn100 ="42";
		genericTestFemale(parms, risk,numIn100);

	}
	
	
		
	@Test
	public void female5_70() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=north";

		String risk ="0.0482";
		String numIn100 ="21";
		genericTestFemale(parms, risk,numIn100);

	}

	

	

	
	@Test
	public void female6_30() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=midland";

		String risk ="0.0187";
		String numIn100 ="53";
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female6_32() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=midland";

		String risk ="0.0189";
		String numIn100 ="53";
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female6_35() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=midland";

		String risk ="0.0193";
		String numIn100 ="52";
		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female6_40() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=midland";

		String risk ="0.0206";
		String numIn100 ="49";
		genericTestFemale(parms, risk,numIn100);

	}

	
	@Test
	public void female6_50() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=midland";

		String risk ="0.0369";
		String numIn100 ="27";
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female6_70() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=midland";

		String risk ="0.0661";
		String numIn100 ="15";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	

	
	@Test
	public void female7_30() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=central";

		String risk ="0.0103";
		String numIn100 ="97";
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female7_32() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=central";

		String risk ="0.0105";
		String numIn100 ="95";
		
		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female7_35() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=central";

		String risk ="0.0108";
		String numIn100 ="93";
		
		genericTestFemale(parms, risk,numIn100);

	}

	

	@Test
	public void female7_40() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=central";

		String risk ="0.0165";
		String numIn100 ="60";
		genericTestFemale(parms, risk, numIn100);

	}

	 

	@Test
	public void female7_50() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=central";

		String risk ="0.0209";
		String numIn100 ="48";
		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female7_70() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=central";

		String risk ="0.0429";
		String numIn100 ="23";

		genericTestFemale(parms, risk,numIn100);

	}

	
	
	@Test
	public void female8_30() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=south";

		String risk ="0.0118";
		String numIn100 ="85";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female8_32() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=south";

		String risk ="0.0130";
		String numIn100 ="77";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female8_35() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=south";

		String risk ="0.0149";
		String numIn100 ="67";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female8_40() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=south";

		String risk ="0.0203";
		String numIn100 ="49";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female8_50() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=south";

		String risk ="0.0311";
		String numIn100 ="32";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female8_70() {

		String parms ="occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=south";

		String risk ="0.0596";
		String numIn100 ="17";

		genericTestFemale(parms, risk,numIn100);

	}



	
	


	
	
	//
	//
	// MEDIUM LOW RISK
	//
	//
	String parms_9_12 ="occ=in&hair=black&freckles=few&moles=0&famhxmoles=yes&nmsc=no"; 
	@Test
	public void female9_30() {

		String parms = parms_9_12 +"&age=30&area=north";

		String risk ="0.0010";
		String numIn100 ="1,033";
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female9_35() {

		String parms = parms_9_12 +"&age=35&area=north";

		String risk ="0.0016";
		String numIn100 ="632";
		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female9_48() {

		String parms = parms_9_12 +"&age=48&area=north";

		String risk ="0.0034";
		String numIn100 ="295";
		genericTestFemale(parms, risk,numIn100);

	}
	 
	@Test
	public void female9_50() {

		String parms = parms_9_12 +"&age=50&area=north";
        System.out.println(parms);
		String risk ="0.0035";
		String numIn100 ="287";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female9_70() {

		String parms = parms_9_12 +"&age=70&area=north";

		String risk ="0.0071";
		String numIn100 ="141";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female9_70B() {
		String parms = parms_9_12 +"&age=70&area=north";

		String risk ="0.0071";
		genericTestFemaleB("1-1", parms, risk,Util.MODERATE_RISK,Util.MODERATE_RISK_TEXT, (String) numIn100.get("9_70"));
					

	}


	
	@Test
	public void female10_30() {

		String parms = parms_9_12 + "&age=30&area=midland";
        System.out.println(parms);
		String risk ="0.0027";
		String numIn100 ="368";

		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female10_35() {

		String parms = parms_9_12 + "&age=35&area=midland";
        System.out.println(parms);
		String risk ="0.0028";
		String numIn100 ="357";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female10_48() {

		String parms = parms_9_12 + "&age=48&area=midland";
        System.out.println(parms);
		String risk ="0.0049";
		String numIn100 ="206";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female10_50() {

		String parms = parms_9_12 +"&age=50&area=midland";

		String risk ="0.0054";
		String numIn100 ="185";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female10_70() {

		String parms = parms_9_12 +"&age=70&area=midland";

		String risk ="0.0098";
		String numIn100 ="102";

		genericTestFemale(parms, risk,numIn100);

	}



	@Test
	public void female11_30() {

		String parms = parms_9_12 +"&age=30&area=central";

		String risk ="0.0015";
		String numIn100 ="669";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female11_35() {

		String parms = parms_9_12 +"&age=35&area=central";

		String risk ="0.0016";
		String numIn100 ="640";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female11_48() {

		String parms = parms_9_12 +"&age=48&area=central";

		String risk ="0.0030";
		String numIn100 ="335";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female11_50() {

		String parms = parms_9_12 +"&age=50&area=central";

		String risk ="0.0030";
		String numIn100 ="328";
		genericTestFemale(parms, risk,numIn100);

	}
	

 	
@Test
public void female11_70() {

String parms = parms_9_12 +"&age=70&area=central";

String risk ="0.0063";
String numIn100 ="159";
genericTestFemale(parms, risk,numIn100);

}




	@Test
	public void female12_30() {

		String parms = parms_9_12 +"&age=30&area=south";
		String numIn100 ="587";

		String risk ="0.0017";
		
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female12_35() {

		String parms = parms_9_12 +"&age=35&area=south";

		String risk ="0.0022";
		String numIn100 ="464";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female12_48() {

		String parms = parms_9_12 +"&age=48&area=south";

		String risk ="0.0043";
		String numIn100 ="231";

		genericTestFemale(parms, risk,numIn100);

	}


		 

	 

		

	@Test
	public void female12_50() {

		String parms = parms_9_12 +"&age=50&area=south";

		String risk ="0.0045";
		String numIn100 ="220";

		genericTestFemale(parms, risk,numIn100);

	}

	


	@Test
	public void female12_70() {

		String parms = parms_9_12 +"&age=70&area=south";

		String risk ="0.0088";
		String numIn100 ="113";

		genericTestFemale(parms, risk,numIn100);

	}

	//
	//
	// LOW RISK 
	//
	//
	
	@Test
	public void female13_30() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=north";

		String risk ="0.0001";
		String numIn100 ="17,771";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female13_32() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=north";

		String risk ="0.0001";
		String numIn100 ="14,174";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female13_50() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=north";

		String risk ="0.0002";
		String numIn100 ="4,929";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female13_50B() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=north";

		String risk ="0.0002";
		genericTestFemaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT, (String) numIn100.get("13_50"));
					

	}


	
	@Test
	public void female13_56() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=north";

		String risk ="0.0003";
		String numIn100 ="3,477";

		genericTestFemale(parms, risk,numIn100);

	}
	
	@Test
	public void female13_70() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=north";

		String risk ="0.0004";
		String numIn100 ="2,417";

		genericTestFemale(parms, risk,numIn100);

	}
	
	
	

	@Test
	public void female14_30() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=midland";

		String risk ="0.0002";
		String numIn100 ="6,326";
		genericTestFemale(parms, risk,numIn100);

	}
	
	 


	@Test
	public void female14_32() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=midland";

		String risk ="0.0002";
		String numIn100 ="6,248";
		genericTestFemale(parms, risk,numIn100);

	}


	@Test
	public void female14_50() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=midland";

		String risk ="0.0003";
		String numIn100 ="3,181";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female14_56() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=midland";

		String risk ="0.0004";
		String numIn100 ="2,582";

		genericTestFemale(parms, risk,numIn100);

	}
	

	@Test
	public void female14_70() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=midland";

		String risk ="0.0006";
		String numIn100 ="1,747";

		genericTestFemale(parms, risk,numIn100);

	}


	
	
	@Test
	public void female15_30() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=central";

		String risk ="0.0001";
		String numIn100 ="11,505";

		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female15_32() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=central";

		String risk ="0.0001";
		String numIn100 ="11,298";
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female15_50() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=central";

		String risk ="0.0002";
		String numIn100 ="5,643";

		genericTestFemale(parms, risk,numIn100);

	}

	


	@Test
	public void female15_56() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=central";

		String risk ="0.0003";
		String numIn100 ="3,965";
		
		genericTestFemale(parms, risk,numIn100);

	}

	
	@Test
	public void female15_70() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=central";
		                 
		String risk ="0.0004";
		String numIn100 ="2,726";
		
		genericTestFemale(parms, risk,numIn100);

	}

	
	@Test
	public void female16_30() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=south";

		String risk ="0.0001";
		String numIn100 ="10,095";
		
		genericTestFemale(parms, risk,numIn100);

	}
	


	@Test
	public void female16_32() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=south";

		String risk ="0.0001";
		String numIn100 ="9,126";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	 
	
		
	

	@Test
	public void female16_50() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=south";

		String risk ="0.0003";
		String numIn100 ="3,782";
		
		genericTestFemale(parms, risk,numIn100);

	}

	@Test
	public void female16_56() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=south";

		String risk ="0.0003";
		String numIn100 ="3,200";
		
		genericTestFemale(parms, risk, numIn100);

	}

	
	

	
	
	 	
	
	

	@Test
	public void female16_70() {

		String parms ="occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=south";

		String risk ="0.0005";
		String numIn100 ="1,944";
		
		genericTestFemale(parms, risk,numIn100);

	}
	
	
	private static URI getBaseURIFemale(String parms) {

		// infoscience deployment production.
		// return
		// UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
		// String url =
		//"http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcf?"+ parms;

		// local tomcat

		String host ="http://soblinux01.otago.ac.nz:8090/";

		boolean testLocal = false;
		if (testLocal) {
			host ="http://localhost:8080/";
				
		}
		
		String url = host + "mcrest2/getmelcalcf?" + parms;
		//url = host + "mcresttest/getmelcalcf?" + parms;
		
		return UriBuilder.fromUri(url).build();
				
		

	}

}
