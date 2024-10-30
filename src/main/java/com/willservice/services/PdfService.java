package com.willservice.services;

import com.willservice.will.data.*;
import com.willservice.xml.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility methods to generate XML template, update template with will data
 * and generate pdf
 */
@Service
public class PdfService {

    /**
     * Generate xml template
     * @param templateName
     */
    public void generateTemplate(String templateName){
        Path path = Paths.get( templateName + ".fo");
        File xmlTemplate = null;
        if(path != null){
            xmlTemplate = path.toFile();
        }
        //return existing template or generate new
        if(path == null || xmlTemplate == null || !xmlTemplate.exists()) {

            try {
                Root root = new Root();

                LayoutMasterSet layoutMasterSet = new LayoutMasterSet();
                SimplePageMaster simplePageMaster = new SimplePageMaster();
                simplePageMaster.setMargin("2cm");
                simplePageMaster.setPageWidth("20cm");
                simplePageMaster.setPageHeight("30cm");
                simplePageMaster.setMasterName("willdocument");
                RegionBody regionBody = new RegionBody();
                regionBody.setMargin("2cm");
                simplePageMaster.setRegionBody(regionBody);
                layoutMasterSet.setSimplePageMaster(simplePageMaster);
                root.setLayoutMasterSet(layoutMasterSet);
                PageSequence pageSequence = new PageSequence();
                Flow flow = new Flow();
                flow.setFlowName("xsl-region-body");
                List<Block> blocks = new ArrayList<>();
                String fontFamily = "";
                String fontSize = "";
                XWPFDocument xwpfDocument = null;
                try {
                    xwpfDocument = new XWPFDocument(new FileInputStream(getClass().getClassLoader().getResource("template/" + templateName + ".docx").getFile()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (XWPFParagraph para : xwpfDocument.getParagraphs()) {

                    Block block = new Block();
                    block.setFontSize("12pt");
                    block.setSpaceAfter("6pt");
                    block.setTextAlign(getAlignment(para.getAlignment()));
                    Inline inline = new Inline();

                    for (XWPFRun run : para.getRuns()) {
                        String text = run.getText(0);
                        if (text != null) {
                            String font = run.getFontFamily() != null ? run.getFontFamily() : "Times Roman";
                            inline.setColor("#000000");
                            inline.setFontSize(run.getFontSize() > 0 ? run.getFontSize() + "pt" : "11pt");
                            inline.setFontFamily(font);
                            inline.setFontWeight(run.isBold() ? "bold" : "");
                            inline.setValue(text);

                            if (text.contains("<beneficiaries>")) {
                                if ("".equals(fontFamily)) {
                                    fontFamily = run.getFontFamily() != null ? run.getFontFamily() : "Times Roman";
                                }
                                if ("".equals(fontSize)) {
                                    fontSize = run.getFontSizeAsDouble() != null ? run.getFontSize() + "pt" : "11pt";
                                }
                            }
                        }
                    }
                    block.setInline(inline);
                    blocks.add(block);
                }
                pageSequence.setMasterReference("willdocument");
                flow.setBlock(blocks);
                pageSequence.setFlow(flow);
                root.setPageSequence(pageSequence);
                root.setLayoutMasterSet(layoutMasterSet);
                xwpfDocument.close();
                JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                File template = new File(templateName + ".fo");
                template.createNewFile();
                jaxbMarshaller.marshal(root, template);
                File style = new File(getClass().getClassLoader().getResource("template/" + templateName + ".properties").getFile());
                Properties prop = new Properties();
                InputStream in = new FileInputStream(style);
                prop.load(in);
                prop.setProperty("fontFamily", "Times New Roman");
                prop.setProperty("fontSize", "12pt");
                prop.store(new FileOutputStream(style), null);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Get alignment
     * @param alignment
     * @return
     */
    private String getAlignment(ParagraphAlignment alignment) {
        if (alignment == ParagraphAlignment.LEFT) {
            return "left";
        } else if (alignment == ParagraphAlignment.CENTER) {
            return "center";
        } else if (alignment == ParagraphAlignment.RIGHT) {
            return "right";
        } else if (alignment == ParagraphAlignment.BOTH) {
            return "justify";
        }
        return "left";
    }

    /**
     * Update template with will data
     * @param willData
     * @param templateName
     */
    public void setPdfData(String templateName, WillData willData) {
        String name = templateName.substring(0, templateName.indexOf("-"));
        Path path = Paths.get( name + ".fo");
        Charset charset = StandardCharsets.UTF_8;
        File style = new File(getClass().getClassLoader().getResource("template/" + name + ".properties").getFile());
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(style)) {
            prop.load(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fontFamily = prop.getProperty("fontFamily");
        String fontSize = prop.getProperty("fontSize");
        String content = null;
        try {
            content = new String(Files.readAllBytes(path), charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        content = content.replaceAll("&lt;testatorFullName&gt;", willData.getTestatorFullName());
        if(willData.getUser() == null || willData.getUser().getAddressData() == null) {
            content = content.replaceAll("&lt;testatorCity&gt;", "");
            content = content.replaceAll("&lt;testatorProvince&gt;", "");
        }else {
            content = content.replaceAll("&lt;testatorCity&gt;", willData.getUser().getAddressData().getCity() == null ? "" : willData.getUser().getAddressData().getCity());
            content = content.replaceAll("&lt;testatorProvince&gt;", willData.getUser().getAddressData().getProvince() == null ? "" : willData.getUser().getAddressData().getProvince());
        }
        content = this.setBeneficiaries(content,prop,willData.getBeneficiaries());

        if(templateName.startsWith("conditional")) {

            ConditionalWillData conditionalWillData = (ConditionalWillData) willData;
            List<WillCondition> conditions = conditionalWillData.getConditions();
            StringBuffer buffer = new StringBuffer();

            if(conditions != null && conditions.size() > 0) {
                buffer.append("</fo:inline></fo:block>");
                int i = 1;
                for (WillCondition condition : conditions) {
                    String inline = "<fo:block color=\"#000000\" space-after=\"6pt\" text-align=\"left\" >"
                            + "<fo:inline font-size=\"12pt\" font-family=\"" + fontFamily + "\" >"
                            + condition.getDescription();
                    if (i != conditions.size()) {
                        inline += "</fo:inline>"
                                + "</fo:block>";
                    }
                    i++;
                    buffer.append(inline);
                }
            }
            content = content.replaceAll("&lt;conditions&gt;",buffer.toString());

        }else if(templateName.startsWith("trust")) {
            TrustWillData trust = (TrustWillData)willData;
            content = content.replaceAll("&lt;trustee&gt;", trust.getTrustee());
            content = content.replaceAll("&lt;trustName&gt;", trust.getTrustName());
        }
        try {
            Files.write(Paths.get(("output/" + templateName + ".fo")), content.getBytes(charset));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String setBeneficiaries(String content, Properties prop, List<BeneficiaryData> beneficiaries){
        StringBuffer buffer = new StringBuffer();
        String fontFamily = prop.getProperty("fontFamily");
        String fontSize = prop.getProperty("fontSize");
        if(beneficiaries != null && beneficiaries.size() > 0) {
            buffer.append("</fo:inline></fo:block>");
            int i = 1;
            for (BeneficiaryData beneficiary : beneficiaries) {
                String inline = "<fo:block color=\"#000000\" space-after=\"6pt\" text-align=\"left\" >"
                        + "<fo:inline font-size=\"12pt\" font-family=\"" + fontFamily + "\" >"
                        + beneficiary.getBeneficiaryFullName();
                if (i != beneficiaries.size()) {
                    inline += "</fo:inline>"
                            + "</fo:block>";
                }
                i++;
                buffer.append(inline);
            }
        }
        return content.replaceAll("&lt;beneficiaries&gt;",buffer.toString());
    }

    /**
     * Generate pdf
     * @param name
     * @return
     */
    public byte[] generatePdf(String name) {
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdf);
            StreamSource foSource = new StreamSource(("output/" + name + ".fo"));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            SAXResult result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(foSource, result);
        }catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            try {
                pdf.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pdf.toByteArray();
    }

    private String escapeXML(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
