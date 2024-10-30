package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;


public class SimplePageMaster {

    @XmlTransient
    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    @XmlTransient
    public void setPageHeight(String pageHeight) {
        this.pageHeight = pageHeight;
    }
    @XmlTransient
    public void setPageWidth(String pageWidth) {
        this.pageWidth = pageWidth;
    }

    @XmlTransient
    public void setMargin(String margin){
        this.margin = margin;
    }

    @XmlTransient
    public void setRegionBody(RegionBody regionBody) {
        this.regionBody = regionBody;
    }

    @XmlAttribute
    private String margin;

    @XmlAttribute(name="master-name")
    private String masterName;
    @XmlAttribute(name="page-height")
    private String pageHeight;
    @XmlAttribute(name="page-width")
    private String pageWidth;

    @XmlElement(name="region-body")
    RegionBody regionBody;
}
