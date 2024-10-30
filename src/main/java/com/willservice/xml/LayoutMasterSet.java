package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

public class LayoutMasterSet {

    @XmlTransient
    public void setSimplePageMaster(SimplePageMaster simplePageMaster) {
        this.simplePageMaster = simplePageMaster;
    }

    @XmlElement(name="simple-page-master")
    SimplePageMaster simplePageMaster;

    @XmlElement(name="page-sequence")
    PageSequence pageSequence;
}
