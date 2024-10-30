package com.willservice.service.controller;

import com.willservice.services.DocumentService;
import com.willservice.services.PdfService;
import com.willservice.will.data.WillData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;

/**
 * Generate simple will pdf document
 */
@RestController
public class Simple{

    @Autowired
    DocumentService documentService;

    /**
     * Endpoint to generate simple will document
     * Generate pdf document using xml template, updating will data, and writing pdf to HttpServletResponse
     * @param willData
     * @param response
     * @throws Exception
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/simple")
    @ResponseBody
    public void simple(@RequestBody WillData willData, HttpServletResponse response) {

        response.addHeader("Content Type","application/pdf");
        try {
            String templateName = "simple" + "-" + System.currentTimeMillis();
            byte[] pdf = documentService.getPdf(templateName,willData);
            response.getOutputStream().write(pdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}