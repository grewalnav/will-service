package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

public class Block {
    @XmlAttribute(name="font-size")
    private String fontSize;

    @XmlTransient
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }
    @XmlTransient
    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }
    @XmlTransient
    public void setSpaceAfter(String spaceAfter) {
        this.spaceAfter = spaceAfter;
    }

    @XmlAttribute(name="text-align")
    private String textAlign;

    @XmlAttribute(name="space-after")
    private String spaceAfter;

    @XmlElement(name="inline")
    private Inline inline;

    @XmlTransient
    public void setInline(Inline inline){
        this.inline = inline;
    }

}
