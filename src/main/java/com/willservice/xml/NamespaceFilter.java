package com.willservice.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter extends XMLFilterImpl {

    private static final String NAMESPACE = "xmlns:fo=\"http://www.w3.org/1999/XSL/Format";

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(NAMESPACE, localName, qName);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) throws SAXException {
        super.startElement(NAMESPACE, localName, qName, atts);
    }
}
