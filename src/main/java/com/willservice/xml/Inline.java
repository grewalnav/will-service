package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlValue;

public class Inline {

    @XmlAttribute(name="font-family")
    private String fontFamily;

    @XmlAttribute(name="font-size")
    private String fontSize;

    @XmlAttribute(name="color")
    private String color;

    @XmlAttribute(name="font-weight")
    private String fontWeight;

    @XmlValue
    private String value;

    @XmlTransient
    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
    @XmlTransient
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }
    @XmlTransient
    public void setColor(String color) {
        this.color = color;
    }
    @XmlTransient
    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }
}
