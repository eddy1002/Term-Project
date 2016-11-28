package com.example.cho.pocketmongocho;

import android.os.AsyncTask;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GetXMLTask extends AsyncTask<String, Void, Document> {

    Document weatherDoc = null;

    MainValues mv;

    @Override
    protected Document doInBackground(String... urls) {
        URL url;
        try {
            url = new URL(urls[0]);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
            weatherDoc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
            weatherDoc.getDocumentElement().normalize();

        } catch (Exception e) {
        }
        return weatherDoc;
    }

    @Override
    protected void onPostExecute(Document doc) {
        String weatherString = "";
        NodeList weatherNodeList = doc.getElementsByTagName("data");

        Node weatherNode = weatherNodeList.item(0);
        Element weatherElement = (Element) weatherNode;
        NodeList websiteList = weatherElement.getElementsByTagName("wfKor");

        weatherString += websiteList.item(0).getChildNodes().item(0).getNodeValue() + "";

        mv = MainValues.getInstance();
        mv.setWeather(weatherString);

        super.onPostExecute(doc);
    }
}
