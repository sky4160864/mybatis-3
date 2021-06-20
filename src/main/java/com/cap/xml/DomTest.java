package com.cap.xml;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author C.H
 * @date 2021-06-12 16:47
 *
 * @see org.apache.ibatis.parsing.XPathParser
 * @see org.apache.ibatis.parsing /test包中有测试
 */
public class DomTest {

    @Test
    public void test01() throws Exception {
        //DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        String resource = "resources/dom_test.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Document doc = documentBuilder.parse(inputStream);

        XPath xPath = XPathFactory.newInstance().newXPath();

        System.out.println("匹配employee的first_name, 查height");
        XPathExpression compile = xPath.compile("//employee[first_name='Jim2']/height/text()");
        NodeList list = (NodeList)compile.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            System.out.println(item.getNodeValue());
        }
        System.out.println();

        System.out.println("匹配birth_date的year, 查month");
        XPathExpression compile1 = xPath.compile("//birth_date[year>1971]/month/text()");
        NodeList list2 = (NodeList)compile1.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < list2.getLength(); i++) {
            Node item = list2.item(i);
            System.out.println(item.getNodeValue());
        }
        System.out.println();

        System.out.println("匹配employee的height, 查ID");
        XPathExpression compile3 = xPath.compile("//employee[height>5.811]/@*");
        NodeList list3 = (NodeList)compile3.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < list3.getLength(); i++) {
            Node item = list3.item(i);
            System.out.println(item.getNodeValue());
        }
        System.out.println();

        System.out.println("匹配employee的height, 查ID和height");
        compile3 = xPath.compile("//employee[height>5.811]/@*|//employee[height>5.811]/height/@*");
        list3 = (NodeList)compile3.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < list3.getLength(); i++) {
            Node item = list3.item(i);
            System.out.println(item.getNodeValue());
        }
        System.out.println();
    }

    /**
     *
     */
    @Test
    public void test(){

    }
}
