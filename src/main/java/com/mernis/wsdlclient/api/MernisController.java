package com.mernis.wsdlclient.api;

import com.mernis.wsdlclient.annotation.ApiV1;
import com.mernis.wsdlclient.services.base.BaseMernis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


@RestController
@ApiV1
public class MernisController {

	@Autowired
	BaseMernis baseMernis;
	
	@GetMapping(value="/validate",produces="application/json")
	public Map<String,Object> mernisDogrula(@RequestParam("tc") String tc,
											@RequestParam("ad") String ad,
											@RequestParam("soyAd") String soyAd,
											@RequestParam("dogumYili") String dogumYili
											) throws IOException, ParserConfigurationException, SAXException {

		return baseMernis.validateMe(tc,ad,soyAd,dogumYili);
	}

	@GetMapping(value = "/validate/xml", produces = {"application/xml"}, consumes = {"application/xml"})
	public Map<String,Object> mernisDogrulaXML(@RequestParam("tc") String tc,
											@RequestParam("ad") String ad,
											@RequestParam("soyAd") String soyAd,
											@RequestParam("dogumYili") String dogumYili
	) throws IOException, ParserConfigurationException, SAXException {
		Map<String,Object> response = baseMernis.validateMe(tc,ad,soyAd,dogumYili);
		return response;
	}


}
