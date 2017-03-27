import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PDFReportGenerator {

    public byte[] createPdf(String rptDesign) throws EngineException {
        try {
            IReportEngine engine = createReportEngine();
            InputStream rptFIS = new ByteArrayInputStream(rptDesign.getBytes("UTF-8"));
            IReportRunnable design = engine.openReportDesign(rptFIS);
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);

            task.validateParameters();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PDFRenderOption options = new PDFRenderOption();

            options.setOutputFormat("pdf");
            options.setOutputStream(byteArrayOutputStream);
            task.setRenderOption(options);

            task.run();
            task.close();
            engine.destroy();

            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            RegistryProviderFactory.releaseDefault();
            Platform.shutdown();
        }
    }

    private IReportEngine createReportEngine() throws BirtException {
        EngineConfig config = new EngineConfig();
        Platform.startup(config);
        IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        return factory.createReportEngine(config);
    }
}
