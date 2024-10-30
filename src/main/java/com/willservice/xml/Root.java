package com.willservice.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlElement(name="layout-master-set")
    private LayoutMasterSet layoutMasterSet;

    @XmlElement(name="page-sequence")
    private PageSequence pageSequence;

    @XmlTransient
    public void setLayoutMasterSet(LayoutMasterSet layoutMasterSet) {
        this.layoutMasterSet = layoutMasterSet;
    }

    @XmlTransient
    public void setPageSequence(PageSequence pageSequence) {
        this.pageSequence = pageSequence;
    }
}
