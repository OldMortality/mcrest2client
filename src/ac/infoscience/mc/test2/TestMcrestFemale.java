package ac.infoscience.mc.test2;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import ac.otago.infoscience.mcws2.Util;

public class TestMcrestFemale {

	
	String head = "<?xml version=\"1.0\"?><MR><RISK>";
  
	String getTail(String risk) {
		System.out.println("risk:"+risk);
		String result = "";
		String cat = Util.calculateRiskCategory(new Float(risk));
		String desc = Util.getRiskDescription(cat);
		result = "</RISK><RISK_CATEGORY>" + cat + "</RISK_CATEGORY>" +
				"<DESCRIPTION>" + desc + "</DESCRIPTION></MR>";
		System.out.println(result);
		return result;
		
	}
	
	public void genericTestFemale(String id, String parms, String risk) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIFemale(parms));

		String result = service.accept(MediaType.TEXT_XML).get(String.class);

		String expected = head + risk + getTail(risk);
		assertEquals("x ", expected, result);

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

		String risk = "0.0155";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female1_48() {

		String parms =
				"occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=north";

		String risk = "0.0534";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female1_50() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=north";

		String risk = "0.0548";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female1_56() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=north";

		String risk = "0.0768";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female1_70() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=north";

		String risk = "0.1085";
		genericTestFemale("1-3", parms, risk);
		

	}

	@Test
	public void female2_30() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=midland";

		String risk = "0.0430";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female2_48() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=midland";

		String risk = "0.0755";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female2_50() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=midland";

		String risk = "0.0837";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female2_56() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=midland";

		String risk = "0.1020";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female2_70() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=midland";

		String risk = "0.1469";
		genericTestFemale("1-1", parms, risk);

	}
	
	@Test
	public void female3_30() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=central";

		String risk = "0.0239";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female3_48() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=central";

		String risk = "0.0472";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female3_50() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=central";

		String risk = "0.0481";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female3_56() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=central";

		String risk = "0.0677";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female3_70() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=central";

		String risk = "0.0968";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female4_30() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=south";

		String risk = "0.0272";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female4_48() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=48&area=south";

		String risk = "0.0675";
		genericTestFemale("", parms, risk);

	}

	// ////////////////////////

		 
	
	

	@Test
	public void female4_50() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=south";

		String risk = "0.0709";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female4_56() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=56&area=south";

		String risk = "0.0832";
		genericTestFemale("", parms, risk);

	}
	
 
	




	@Test
	public void female4_70() {

		String parms = "occ=in&hair=fair&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=south";

		String risk = "0.1330";
		genericTestFemale("", parms, risk);

	}
	// //////////////////

	 

 

	

	//
	//
	// MEDIUM HIGH RELATIVE RISK
	//
	//
	@Test
	public void female5_30() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=north";

		String risk = "0.0067";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female5_32() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=north";

		String risk = "0.0084";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female5_35() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=north";

		String risk = "0.0109";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female5_40() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=north";

		String risk = "0.0198";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female5_50() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=north";

		String risk = "0.0239";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female6_30() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=midland";

		String risk = "0.0187";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female6_32() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=midland";

		String risk = "0.0189";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female6_35() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=midland";

		String risk = "0.0193";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female6_40() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=midland";

		String risk = "0.0206";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female6_50() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=midland";

		String risk = "0.0369";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female7_30() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=central";

		String risk = "0.0103";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female7_32() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=central";

		String risk = "0.0105";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female7_35() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=central";

		String risk = "0.0108";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female7_40() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=central";

		String risk = "0.0165";
		genericTestFemale("", parms, risk);

	}

	 
	
	
	@Test
	public void female8_30() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=30&area=south";

		String risk = "0.0118";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female8_32() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=32&area=south";

		String risk = "0.0130";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female8_35() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=35&area=south";

		String risk = "0.0149";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female8_40() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=40&area=south";

		String risk = "0.0203";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female8_50() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=south";

		String risk = "0.0311";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female8_70() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=south";

		String risk = "0.0596";
		genericTestFemale("", parms, risk);

	}



	
	


	

	@Test
	public void female7_50() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=50&area=central";

		String risk = "0.0209";
		genericTestFemale("", parms, risk);

	}
	@Test
	public void female5_70() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=north";

		String risk = "0.0482";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female6_70() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=midland";

		String risk = "0.0661";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female7_70() {

		String parms = "occ=out&hair=black&freckles=many&moles=2&famhxmoles=yes&nmsc=yes&age=70&area=central";

		String risk = "0.0429";
		genericTestFemale("", parms, risk);

	}

	//
	//
	// MEDIUM LOW RISK
	//
	//
	String parms_9_12 = "occ=in&hair=black&freckles=few&moles=0&famhxmoles=yes&nmsc=no"; 
	@Test
	public void female9_30() {

		String parms = parms_9_12 + "&age=30&area=north";

		String risk = "0.0010";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female9_35() {

		String parms = parms_9_12 + "&age=35&area=north";

		String risk = "0.0016";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female9_48() {

		String parms = parms_9_12 + "&age=48&area=north";

		String risk = "0.0034";
		genericTestFemale("", parms, risk);

	}
	 
	@Test
	public void female10_30() {

		String parms = parms_9_12 +  "&age=30&area=midland";
        System.out.println(parms);
		String risk = "0.0027";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female10_35() {

		String parms = parms_9_12 +  "&age=35&area=midland";
        System.out.println(parms);
		String risk = "0.0028";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female10_48() {

		String parms = parms_9_12 +  "&age=48&area=midland";
        System.out.println(parms);
		String risk = "0.0049";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female11_30() {

		String parms = parms_9_12 + "&age=30&area=central";

		String risk = "0.0015";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female11_35() {

		String parms = parms_9_12 + "&age=35&area=central";

		String risk = "0.0016";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female11_48() {

		String parms = parms_9_12 + "&age=48&area=central";

		String risk = "0.0030";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female12_30() {

		String parms = parms_9_12 + "&age=30&area=south";

		String risk = "0.0017";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female12_35() {

		String parms = parms_9_12 + "&age=35&area=south";

		String risk = "0.0022";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female12_48() {

		String parms = parms_9_12 + "&age=48&area=south";

		String risk = "0.0043";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female9_50() {

		String parms = parms_9_12 + "&age=50&area=north";
        System.out.println(parms);
		String risk = "0.0035";
		genericTestFemale("", parms, risk);

	}
	 
	@Test
	public void female10_50() {

		String parms = parms_9_12 + "&age=50&area=midland";

		String risk = "0.0054";
		genericTestFemale("", parms, risk);

	}


	 

	
	@Test
	public void female11_50() {

		String parms = parms_9_12 + "&age=50&area=central";

		String risk = "0.0030";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female12_50() {

		String parms = parms_9_12 + "&age=50&area=south";

		String risk = "0.0045";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female9_70() {

		String parms = parms_9_12 + "&age=70&area=north";

		String risk = "0.0071";
		genericTestFemale("", parms, risk);

	}
	 
	@Test
	public void female10_70() {

		String parms = parms_9_12 + "&age=70&area=midland";

		String risk = "0.0098";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female11_70() {

		String parms = parms_9_12 + "&age=70&area=central";

		String risk = "0.0063";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female12_70() {

		String parms = parms_9_12 + "&age=70&area=south";

		String risk = "0.0088";
		genericTestFemale("", parms, risk);

	}

	//
	//
	// LOW RISK 
	//
	//
	
	@Test
	public void female13_30() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=north";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female13_32() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=north";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female13_56() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=north";

		String risk = "0.0003";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female14_30() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=midland";

		String risk = "0.0002";
		genericTestFemale("", parms, risk);

	}
	
	 


	@Test
	public void female14_32() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=midland";

		String risk = "0.0002";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female15_30() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=central";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female15_32() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=central";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female16_30() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=30&area=south";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}
	


	@Test
	public void female16_32() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=32&area=south";

		String risk = "0.0001";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female13_50() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=north";

		String risk = "0.0002";
		genericTestFemale("", parms, risk);

	}
	 
	@Test
	public void female14_50() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=midland";

		String risk = "0.0003";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female14_56() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=midland";

		String risk = "0.0004";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female15_50() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=central";

		String risk = "0.0002";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female15_56() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=central";

		String risk = "0.0003";
		genericTestFemale("", parms, risk);

	}


	@Test
	public void female16_50() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=50&area=south";

		String risk = "0.0003";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female16_56() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=56&area=south";

		String risk = "0.0003";
		genericTestFemale("", parms, risk);

	}

	
	@Test
	public void female13_70() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=north";

		String risk = "0.0004";
		genericTestFemale("", parms, risk);

	}
	 
	@Test
	public void female14_70() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=midland";

		String risk = "0.0006";
		genericTestFemale("", parms, risk);

	}
	
	@Test
	public void female15_70() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=central";

		String risk = "0.0004";
		genericTestFemale("", parms, risk);

	}
	

	@Test
	public void female16_70() {

		String parms = "occ=inout&hair=black&freckles=none&moles=0&famhxmoles=no&nmsc=no&age=70&area=south";

		String risk = "0.0005";
		genericTestFemale("", parms, risk);

	}
	
	
	private static URI getBaseURIFemale(String parms) {

		// infoscience deployment production.
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
		
		String url = host + "mcrest2/getmelcalcf?" + parms;
				return UriBuilder.fromUri(url).build();

	}

}
