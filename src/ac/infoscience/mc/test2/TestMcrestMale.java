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

public class TestMcrestMale {

	String head = "<?xml version=\"1.0\"?><MR><RISK>";

	String getTail(String risk) {
		String result = "";
		String cat = Util.calculateRiskCategory(new Float(risk));
		String desc = Util.getRiskDescription(cat);
		result = "</RISK><RISK_CATEGORY>" + cat + "</RISK_CATEGORY>" + "<DESCRIPTION>" + desc + "</DESCRIPTION></MR>";
		return result;

	}

	public void genericTestMale(String id, String parms, String risk) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIMale(parms));

		String result = service.accept(MediaType.TEXT_XML).get(String.class);

		String expected = head + risk + getTail(risk);
		assertEquals("x ", expected, result);

	}
	
	
	/*
	 * These next 2 methods are almost the same as the previous 2, except
	 * that we do not calculate the risk category, but we pass in the
	 * expected description.
	 */
	
		String getTailB(String catExpected, String descExpected) {
			String result = "";
			result = "</RISK><RISK_CATEGORY>" + catExpected + "</RISK_CATEGORY>" + "<DESCRIPTION>" + descExpected + "</DESCRIPTION></MR>";
			return result;

		}

		public void genericTestMaleB(String id, String parms, String risk,String catExpected, String descExpected) {

			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);

			WebResource service = client.resource(getBaseURIMale(parms));

			String result = service.accept(MediaType.TEXT_XML).get(String.class);

			String expected = head + risk + getTailB(catExpected, descExpected);
			assertEquals("x ", expected, result);

		}

	@Test
	public void MaleTes1_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0156";
		genericTestMale("1-1", parms, risk);
		

	}
	
	// test the risk category description
	@Test
	public void MaleTes1_30B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0156";
		genericTestMaleB("1-1", parms, risk,Util.HIGH_RISK,Util.HIGH_RISK_TEXT);
		

	}

	@Test
	public void MaleTes1_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=north";
		String risk = "0.0263";
		genericTestMale("1-1", parms, risk);
		

	}

	@Test
	public void MaleTes1_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=north";
		String risk = "0.0508";
		genericTestMale("1-1", parms, risk);
		

	}
	
	@Test
	public void MaleTes1_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0663";
		genericTestMale("1-1", parms, risk);

	}
	@Test
	public void MaleTes1_50B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0663";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

	}

	@Test
	public void MaleTes1_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.2377";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes1_70B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.2377";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

	}


	@Test
	public void MaleTes2_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=midland";
		String risk = "0.0431";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes2_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=midland";
		String risk = "0.0793";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes2_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.1051";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes2_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.2364";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes3_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0115";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes3_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=central";
		String risk = "0.0336";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes3_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=central";
		String risk = "0.0418";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes3_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0727";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes3_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.1725";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes4_30() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0240";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes4_30B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0240";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

	}

	@Test
	public void MaleTes4_37() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=south";
		String risk = "0.0339";
		genericTestMale("1-1", parms, risk);

	}@Test
	public void MaleTes4_45() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=45&area=south";
		String risk = "0.0534";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes4_50() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0774";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes4_70() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.1987";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes4_70B() {

		String parms = "moles=2&nmsc=yes&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.1987";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

	}

	//
	// MEDIUM HIGH
	//
	//
	@Test
	public void MaleTes5_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0050";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes5_30B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=north";
		String risk = "0.0050";
		genericTestMaleB("1-1", parms, risk,Util.MODERATE_RISK,Util.MODERATE_RISK_TEXT);

	}

	
	@Test
	public void MaleTes5_45() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=45&area=north";
		String risk = "0.0165";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes5_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0216";
		genericTestMale("1-1", parms, risk);

	}
	

	@Test
	public void MaleTes5_50B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0216";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

	}

	
	@Test
	public void MaleTes5_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=north";
		String risk = "0.0279";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes5_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=north";
		String risk = "0.0552";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes5_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.0830";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes6_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0112";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes6_30B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0112";
		genericTestMaleB("1-1", parms, risk,Util.HIGH_RISK,Util.HIGH_RISK_TEXT);

	}

	@Test
	public void MaleTes6_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.0347";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes6_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=midland";
		String risk = "0.0397";
		genericTestMale("1-1", parms, risk);

	}@Test
	public void MaleTes6_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=midland";
		String risk = "0.0552";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes6_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.0826";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes7_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0037";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes7_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0237";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes7_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=central";
		String risk = "0.0277";
		genericTestMale("1-1", parms, risk);

	}
	@Test
	public void MaleTes7_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=central";
		String risk = "0.0449";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes7_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.0586";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes8_30() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0077";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes8_50() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0253";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes8_53() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=53&area=south";
		String risk = "0.0314";
		genericTestMale("1-1", parms, risk);

	}@Test
	public void MaleTes8_61() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=61&area=south";
		String risk = "0.0414";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes8_70() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0683";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes8_70B() {

		String parms = "moles=1&nmsc=yes&occ=out&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0683";
		genericTestMaleB("1-1", parms, risk,Util.VERY_HIGH_RISK,Util.VERY_HIGH_RISK_TEXT);

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
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes9_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=north";
		String risk = "0.0021";
		genericTestMale("1-1", parms, risk);

	}
	@Test
	public void MaleTes9_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=north";
		String risk = "0.0054";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes9_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=north";
		String risk = "0.0070";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes9_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=north";
		String risk = "0.0214";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes10_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0028";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes10_30B() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=midland";
		String risk = "0.0028";
		genericTestMaleB("1-1", parms, risk,Util.MODERATE_RISK,Util.MODERATE_RISK_TEXT);

	}

	@Test
	public void MaleTes10_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=midland";
		String risk = "0.0035";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes10_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=midland";
		String risk = "0.0088";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes10_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=midland";
		String risk = "0.0101";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes10_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=midland";
		String risk = "0.0213";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes11_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=central";
		String risk = "0.0009";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes11_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=central";
		String risk = "0.0027";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes11_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=central";
		String risk = "0.0060";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes11_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=central";
		String risk = "0.0070";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes11_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=central";
		String risk = "0.0150";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes12_30() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0019";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes12_30B() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=30&area=south";
		String risk = "0.0019";
		genericTestMaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT);

	}
	

	@Test
	public void MaleTes12_37() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=37&area=south";
		String risk = "0.0027";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes12_50() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=50&area=south";
		String risk = "0.0064";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes12_53() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=53&area=south";
		String risk = "0.0079";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes12_70() {

		String parms = "moles=0&nmsc=no&occ=in&eye=grey&skin=fair&birthplace=nz&age=70&area=south";
		String risk = "0.0175";
		genericTestMale("1-1", parms, risk);

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
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes13_30B() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=north";
		String risk = "0.0000";
		genericTestMaleB("1-1", parms, risk,Util.LOW_RISK,Util.LOW_RISK_TEXT);

	}

	

	
	@Test
	public void MaleTes13_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=north";
		String risk = "0.0002";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes13_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=north";
		String risk = "0.0005";
		genericTestMale("1-1", parms, risk);

	}
	
	@Test
	public void MaleTes13_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=north";
		String risk = "0.0007";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes14_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=midland";
		String risk = "0.0001";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes14_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=midland";
		String risk = "0.0003";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes14_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=midland";
		String risk = "0.0005";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes14_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=midland";
		String risk = "0.0007";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes15_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=central";
		String risk = "0.0000";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes15_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=central";
		String risk = "0.0002";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes15_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=central";
		String risk = "0.0004";
		genericTestMale("1-1", parms, risk);

	}


	
	@Test
	public void MaleTes15_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=central";
		String risk = "0.0005";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes16_30() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=30&area=south";
		String risk = "0.0001";
		genericTestMale("1-1", parms, risk);

	}

	@Test
	public void MaleTes16_50() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=50&area=south";
		String risk = "0.0002";
		genericTestMale("1-1", parms, risk);

	}


	@Test
	public void MaleTes16_61() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=61&area=south";
		String risk = "0.0003";
		genericTestMale("1-1", parms, risk);

	}

	
	@Test
	public void MaleTes16_70() {

		String parms = "moles=0&nmsc=no&occ=inout&eye=brown&skin=olive&birthplace=not_nz&age=70&area=south";
		String risk = "0.0006";
		genericTestMale("1-1", parms, risk);

	}

	private static URI getBaseURIMale(String parms) {

		/// infoscience deployment production.
		// return
		// UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
		// String url =
		// "http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcf?" + parms;

		// local tomcat

		String host = "http://soblinux01.otago.ac.nz:8090/";

		boolean testLocal = false;
		if (testLocal) {
			host = "http://localhost:8080/";

		}

		String url = host + "mcrest2/getmelcalcm?" + parms;
		return UriBuilder.fromUri(url).build();

	}

}
