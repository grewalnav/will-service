package com.willservice.service.controller;

import com.willservice.services.DocumentService;
import com.willservice.will.data.ConditionalWillData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * Generate conditional will pdf document
 */
@RestController
public class Conditional {

    @Autowired
    DocumentService documentService;
    /**
     * Endpoint to generate conditional will
     *
     * @param willData
     * @param response
     * @throws Exception
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/conditional")
    @ResponseBody
    public void conditional(@RequestBody ConditionalWillData willData, HttpServletResponse response) {
        response.addHeader("Content Type","application/pdf");
        try {
            String templateName = "conditional" + "-" + System.currentTimeMillis();
            byte[] pdf = documentService.getPdf(templateName,willData);
            response.getOutputStream().write(pdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
