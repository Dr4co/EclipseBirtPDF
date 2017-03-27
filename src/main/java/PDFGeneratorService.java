import org.apache.velocity.VelocityContext;
import org.eclipse.birt.report.engine.api.EngineException;

import java.io.StringWriter;

/**
 * Created by Dr4co on 2017-03-27.
 */
public class PDFGeneratorService {

    private TemplateFactory templateFactory;
    private PDFReportGenerator PDFReportGenerator;

    public byte[] generatePdfFromTemplate() throws EngineException {
        VelocityContext context = new VelocityContext();

        // Eclipse BIRT Template File
        String rptDesignTemplate = "birt-template.rptdesign.vm";
        String rptDesign = renderTemplate(rptDesignTemplate, context);

        // Necessary since we are not using Spring!
        PDFReportGenerator = new PDFReportGenerator();
        byte[] createdPdf = PDFReportGenerator.createPdf(rptDesign);
        return createdPdf;
    }

    public String renderTemplate(final String templateName,
                                 VelocityContext velocityContext) {
        try {

            StringWriter writer = new StringWriter();

            templateFactory = new TemplateFactory();
            templateFactory.get().mergeTemplate(templateName, "utf-8", velocityContext, writer);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
