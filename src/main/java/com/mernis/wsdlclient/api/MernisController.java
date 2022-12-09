package com.mernis.wsdlclient.api;

import com.mernis.wsdlclient.services.base.BaseMernis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


@RestController
public class MernisController {

	@Autowired
	BaseMernis baseMernis;
	
	@GetMapping
	public Map<String,Object> mernisDogrula(@RequestParam("tc") String tc,
											@RequestParam("ad") String ad,
											@RequestParam("soyAd") String soyAd,
											@RequestParam("dogumYili") String dogumYili
											) {

		return baseMernis.validateMe(tc,ad,soyAd,dogumYili);
	}


}
