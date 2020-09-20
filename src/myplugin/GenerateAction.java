package myplugin;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.*;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Action that activate code generation
 */
@SuppressWarnings("serial")
class GenerateAction extends MDAction {
    private static final String ROOT_FILE_PACKAGE = "uns.ftn.mbrs";

    GenerateAction(String name) {
        super("", name, null, null);
    }

    public void actionPerformed(ActionEvent evt) {

        if (Application.getInstance().getProject() == null) {
            return;
        }
        Package root = Application.getInstance().getProject().getModel();
        if (root == null) {
            return;
        }

        try {
            generateModel(root);
            generateController(root);
            generateService(root);
            generateGrpcService(root);
            generateServiceImpl(root);
            generateMain(root);
            generateJspDetails(root);
            generateJspNavbar(root);
            generateJspForm(root);
            generateJspOverview(root);
            generateRepository(root);
            generatePom(root);
            generateAppProperties(root);

            generateProto(root);

            JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: c:/mbrs");
            exportToXml();
        } catch (AnalyzeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void exportToXml() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == JOptionPane.OK_OPTION) {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String fileName = jfc.getSelectedFile().getAbsolutePath();

                XStream xstream = new XStream(new DomDriver());
                BufferedWriter out;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(fileName), "UTF8"));
                    xstream.toXML(FMModel.getInstance().getClasses(), out);
                    xstream.toXML(FMModel.getInstance().getEnumerations(), out);

                } catch (UnsupportedEncodingException | FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }


    private void generateModel(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.model");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EJBGenerator");
        EJBGenerator ejbGenerator = new EJBGenerator(generatorOptions);
        ejbGenerator.generate();
    }

    private void generateController(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.controller");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ControllerGenerator");
        ControllerGenerator controllerGenerator = new ControllerGenerator(generatorOptions);
        controllerGenerator.generate();
    }

    private void generateProto(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.controller");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ProtoGenerator");
        ProtoGenerator controllerGenerator = new ProtoGenerator(generatorOptions);
        controllerGenerator.generate();
    }

    private void generateService(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.service");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceGenerator");
        ServiceGenerator serviceGenerator = new ServiceGenerator(generatorOptions);
        serviceGenerator.generate();

    }

    private void generateGrpcService(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.grpc.service");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("GrpcServiceGenerator");
        GrpcServiceGenerator serviceGenerator = new GrpcServiceGenerator(generatorOptions);
        serviceGenerator.generate();

    }

    private void generateServiceImpl(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.service.impl");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceImplGenerator");
        ServiceImplGenerator serviceGenerator = new ServiceImplGenerator(generatorOptions);
        serviceGenerator.generate();

    }

    private void generateJspNavbar(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("JspNavbarGenerator");
        JspNavbarGenerator jspNavbarGenerator = new JspNavbarGenerator(generatorOptions);
        jspNavbarGenerator.generate();
    }

    private void generateJspOverview(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, ROOT_FILE_PACKAGE);
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("JspOverviewGenerator");
        JspOverviewGenerator jspOverviewGenerator = new JspOverviewGenerator(generatorOptions);
        jspOverviewGenerator.generate();
    }

    private void generateJspDetails(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, ROOT_FILE_PACKAGE);
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("JspDetailsGenerator");
        JspDetailsGenerator jspDetailsGenerator = new JspDetailsGenerator(generatorOptions);
        jspDetailsGenerator.generate();
    }

    private void generateJspForm(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, ROOT_FILE_PACKAGE);
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("JspFormGenerator");
        JspFormGenerator jspFormGenerator = new JspFormGenerator(generatorOptions);
        jspFormGenerator.generate();
    }


    private void generateMain(Package root)
            throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, ROOT_FILE_PACKAGE);
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("MainGenerator");
        MainGenerator mainGenerator = new MainGenerator(generatorOptions);
        mainGenerator.generate();

    }


    private void generateRepository(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.repository");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepoGenerator");
        RepoGenerator repositoryGenerator = new RepoGenerator(generatorOptions);
        repositoryGenerator.generate();

    }

    private void generatePom(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("PomGenerator");
        PomGenerator pomGenerator = new PomGenerator(generatorOptions);
        pomGenerator.generate();

    }

    private void generateAppProperties(Package root) throws AnalyzeException {
        ModelAnalyzer analyzer = new ModelAnalyzer(root, "");
        analyzer.prepareModel();
        GeneratorOptions generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("AppPropertiesGenerator");
        AppPropertiesGenerator repositoryGenerator = new AppPropertiesGenerator(generatorOptions);
        repositoryGenerator.generate();

    }

}