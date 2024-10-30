package com.willservice.will.data;

import java.util.ArrayList;
import java.util.List;

public class ConditionalWillData extends WillData{

    private List<WillCondition> conditions = new ArrayList<>();

    public List<WillCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<WillCondition> conditions) {
        this.conditions = conditions;
    }
}
