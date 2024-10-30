package com.willservice.service.controller;

import com.willservice.services.DocumentService;
import com.willservice.will.data.TrustWillData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Generate mutual will pdf document
 */
@RestController
public class Trust {

    @Autowired
    DocumentService documentService;
    /**
     * Endpoint to generate mutual will document
     * @param willData
     * @param response
     * @throws Exception
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/trust")
    @ResponseBody
    public void simple(@RequestBody TrustWillData willData, HttpServletResponse response) {

        response.addHeader("Content Type","application/pdf");
        try {
            String templateName = "trust" + "-" + System.currentTimeMillis();
            byte[] pdf = documentService.getPdf(templateName,willData);
            response.getOutputStream().write(pdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
