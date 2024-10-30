package com.willservice.will.data;
public class TrustWillData extends WillData{


    private String trustee;
    private String trustName;

    public String getTrustee() {
        return trustee;
    }

    public void setTrustee(String trustee) {
        this.trustee = trustee;
    }

    public String getTrustName() {
        return trustName;
    }

    public void setTrustName(String trustName) {
        this.trustName = trustName;
    }
}
