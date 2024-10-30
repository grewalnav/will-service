package com.willassitant.doc;

import com.willservice.services.DocumentService;
import com.willservice.services.PdfService;
import com.willservice.will.data.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class DocumentUtils {

    @Test
    public void simple() throws Exception {

        WillData willData = new WillData();
        UserData userData = new UserData();
        userData.setFirstName("Firstname");
        userData.setLastName("Lastname");
        AddressData addressData = new AddressData();
        addressData.setAddress("10010 address rd");
        addressData.setCity("Calgary");
        addressData.setProvince("AB");
        userData.setAddressData(addressData);
        willData.setUser(userData);
        BeneficiaryData beneficiaryData = new BeneficiaryData();
        beneficiaryData.setFirstName("Beneficiaryfirstname");
        beneficiaryData.setLastName("Beneficiarylastname");
        AddressData beneficiaryAddress = new AddressData();
        addressData.setAddress("10012 address rd");
        addressData.setCity("Halifax");
        addressData.setProvince("NS");
        beneficiaryData.setAddress(beneficiaryAddress);
        willData.setUser(userData);

        PdfService pdfService = new PdfService();
        String templateName = "simple" + "-" + System.currentTimeMillis();
        DocumentService documentService = new DocumentService(pdfService);
        byte[] pdf = documentService.getPdf(templateName,willData);

        Assert.assertEquals(pdf != null, true);
    }

    @Test
    public void conditional() throws Exception {

        ConditionalWillData willData = new ConditionalWillData();
        UserData userData = new UserData();
        userData.setFirstName("Firstname");
        userData.setLastName("Lastname");
        AddressData addressData = new AddressData();
        addressData.setAddress("10010 address rd");
        addressData.setCity("Calgary");
        addressData.setProvince("AB");
        userData.setAddressData(addressData);
        willData.setUser(userData);
        BeneficiaryData beneficiaryData = new BeneficiaryData();
        beneficiaryData.setFirstName("Beneficiaryfirstname");
        beneficiaryData.setLastName("Beneficiarylastname");
        AddressData beneficiaryAddress = new AddressData();
        addressData.setAddress("10012 address rd");
        addressData.setCity("Halifax");
        addressData.setProvince("NS");
        beneficiaryData.setAddress(beneficiaryAddress);
        willData.setUser(userData);

        List<WillCondition> conditions = new ArrayList<>();
        for(int i=0;i<5;i++) {
            WillCondition condition = new WillCondition();
            condition.setDescription("Test condition" + i);
            conditions.add(condition);
        }
        willData.setConditions(conditions);

        PdfService pdfService = new PdfService();
        String templateName = "conditional" + "-" + System.currentTimeMillis();
        DocumentService documentService = new DocumentService(pdfService);
        byte[] pdf = documentService.getPdf(templateName,willData);

        Assert.assertEquals(pdf != null, true);
    }

    @Test
    public void testTrust() throws Exception {

        TrustWillData willData = new TrustWillData();
        willData.setTrustee("Trustee name");
        willData.setTrustName("Trust Name");
        UserData userData = new UserData();
        userData.setFirstName("Firstname");
        userData.setLastName("Lastname");
        AddressData addressData = new AddressData();
        addressData.setAddress("10010 address rd");
        addressData.setCity("Calgary");
        addressData.setProvince("AB");
        userData.setAddressData(addressData);
        willData.setUser(userData);
        BeneficiaryData beneficiaryData = new BeneficiaryData();
        beneficiaryData.setFirstName("Beneficiaryfirstname");
        beneficiaryData.setLastName("Beneficiarylastname");
        AddressData beneficiaryAddress = new AddressData();
        addressData.setAddress("10012 address rd");
        addressData.setCity("Halifax");
        addressData.setProvince("NS");
        beneficiaryData.setAddress(beneficiaryAddress);
        willData.setUser(userData);

        PdfService pdfService = new PdfService();
        String templateName = "trust" + "-" + System.currentTimeMillis();
        DocumentService documentService = new DocumentService(pdfService);
        byte[] pdf = documentService.getPdf(templateName,willData);

        Assert.assertEquals(pdf != null, true);
    }
}
