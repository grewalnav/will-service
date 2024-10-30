package com.willservice.services;

import com.willservice.will.data.WillData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Document processor that uses PdfService to generate template with will data and then
 * generate pdf
 */
@Service
public class DocumentService extends DocumentProcess {

    public PdfService pdfService;

    @Autowired
    public DocumentService(PdfService pdfService){
        this.pdfService = pdfService;
    }
    /**
     * Generate or use existing unpopulated XML template
     */
    @Override
    public void generateTemplate(String templateName) {
        pdfService.generateTemplate(templateName.substring(0, templateName.indexOf("-")));
    }

    /**
     * Populate template with will data
     * @param templateName
     * @param willData
     */
    @Override
    public void setWillData(String templateName, WillData willData) {
        pdfService.setPdfData(templateName, willData);
    }

    /**
     * Generate pdf
     * @param templateName
     * @return
     */
    @Override
    public byte[] generatePdf(String templateName) {
        return pdfService.generatePdf(templateName);
    }
}
