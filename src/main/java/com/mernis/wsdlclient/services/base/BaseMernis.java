package com.mernis.wsdlclient.services.base;

import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.KPSPublic;
import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.KPSPublicSoap;

import java.util.Map;

public interface BaseMernis {
    public Map<String,Object> validateMe(String tc,String ad, String soyAd,String dogumYili);
}
