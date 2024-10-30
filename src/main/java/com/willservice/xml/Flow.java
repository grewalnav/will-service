package com.willservice.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;

public class Flow {


    @XmlAttribute(name="flow-name")
    private String flowName;

    @XmlTransient
    public void setFlowName(String flowName){
        this.flowName = flowName;
    }

    @XmlElement(name="block")
    private List<Block> blocks;

    @XmlTransient
    public void setBlock(List<Block> blocks) {
        this.blocks = blocks;
    }
}

