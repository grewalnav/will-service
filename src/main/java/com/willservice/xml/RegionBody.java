package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class RegionBody {

    @XmlAttribute
    private String margin;

    @XmlTransient
    public void setMargin(String margin) {
        this.margin = margin;
    }
}
