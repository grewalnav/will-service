package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

public class PageSequence {

    @XmlElement(name="flow")
    private Flow flow;

    @XmlTransient
    public void setFlow(Flow flow){
        this.flow = flow;
    }
    @XmlAttribute(name="master-reference")
    private String masterReference;
    @XmlTransient
    public void setMasterReference(String masterReference) {
        this.masterReference = masterReference;
    }
}
