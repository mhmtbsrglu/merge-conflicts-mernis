package com.mernis.wsdlclient.services.impl;

import com.mernis.wsdlclient.services.base.BaseMernis;
import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.KPSPublic;
import com.mernis.wsdlclient.tr.gov.nvi.tckimlik.ws.KPSPublicSoap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MernisImpl implements BaseMernis{
    @Override
    public Map<String, Object> validateMe(String tc, String ad, String soyAd, String dogumYili) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("project-files/conf/mernisconfig.xml");
        NodeList nodeList = doc.getElementsByTagName("endpointURL");
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        Element element = (Element)nodeList.item(0);
        URL url = null;
        try {
            Map<String,Object> response = new HashMap<>();
            url = new URL(element.getTextContent().trim());
            KPSPublic kpsPublic = new KPSPublic(url);
            KPSPublicSoap soap = kpsPublic.getKPSPublicSoap();
            boolean result = soap.tcKimlikNoDogrula(Long.parseLong(tc), ad, soyAd, Integer.parseInt(dogumYili));
            response.put("endpoint",element.getTextContent().trim());
            response.put("isRealPersonInTurkey",result);
            response.put("status", HttpStatus.OK);
            return response;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
