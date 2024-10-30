package com.willservice.will.data;

import java.util.List;

public class WillData {

    private UserData user;
    private List<BeneficiaryData> beneficiaries;

    public String getTestatorFullName(){
        String name = "";
        if(this.user != null && this.user.getFirstName() != null){
            name = user.getFirstName();
        }
        if(this.user != null && this.user.getLastName() != null){
            name += name.length() > 0 ? " " : "";
            name += user.getLastName();
        }
        return name;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public List<BeneficiaryData> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryData> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
