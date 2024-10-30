package com.willservice.services;

import com.willservice.will.data.WillData;

/**
 * Document generation process
 */
public abstract class DocumentProcess {

    /**
     * Generate xml template from blank Microsoft Word will document
     * @param templateName
     */
    public abstract void generateTemplate(String templateName);

    /**
     * Populate template with will data
     * @param templateName
     * @param willData
     */
    public abstract void setWillData(String templateName, WillData willData);

    /**
     * Generate pdf from template and populate with will data
     * @param templateName
     * @return
     */
    public abstract byte[] generatePdf(String templateName);

    /**
     * Get Will pdf document
     * @param templateName
     * @param willData
     * @return
     */
    public byte[] getPdf(String templateName, WillData willData){
        generateTemplate(templateName);
        setWillData(templateName,willData);
        return generatePdf(templateName);
    }
}
