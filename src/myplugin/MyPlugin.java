package myplugin;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

import javax.swing.*;
import java.io.File;

/**
 * MagicDraw plugin that performes code generation
 */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {

    private String pluginDir = null;
    private static final String TEMPLATE_DIRECTORY_NAME = "templates";
    private static final String SOURCE_MAIN = "c:/mbrs/src/main";
    private static final String GEN_DIR = SOURCE_MAIN + "/java";
    private static final String RESOURCE_DIR = SOURCE_MAIN + "/resources";
    private static final String WEBAPP_DIR = SOURCE_MAIN;
    public static final String PROTO_DIR = "c:/mbrs/src/main/proto";
    public static final String DirName = "c:/mbrs/src/main/resources";

    public void init() {
        JOptionPane.showMessageDialog(null, "My Plugin init");

        pluginDir = getDescriptor().getPluginDirectory().getPath();

        // Creating submenu in the MagicDraw main menu
        ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
        manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));

        modelOptions();
        controllerOptions();
        serviceOptions();
        grpcServiceOptions();
        serviceImplOptions();
        mainOptions();
        repositoryOptions();
        pomOptions();
        appPropOptions();
        jspNavbarOptions();
        jspDetailsOptions();
        jspFormOptions();
        landingPageOptions();
        landingPageControllerOptions();
        protoOptions();
        jspOverviewOptions();
    }

    private void modelOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "ejbclass", TEMPLATE_DIRECTORY_NAME, "{0}.java", true, "uns.ftn.mbrs.model");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void controllerOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "controller", TEMPLATE_DIRECTORY_NAME, "{0}Controller.java", true, "uns.ftn.mbrs.controller");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ControllerGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void landingPageControllerOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "landingPageController", TEMPLATE_DIRECTORY_NAME, "LandingPageController.java", true, "uns.ftn.mbrs.controller");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("LandingPageControllerGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void protoOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(PROTO_DIR, "proto", "templates", "default.proto", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ProtoGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void serviceOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "service", TEMPLATE_DIRECTORY_NAME, "{0}Service.java", true, "uns.ftn.mbrs.service");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void grpcServiceOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "grpcService", "templates", "{0}GrpcService.java", true, "uns.ftn.mbrs.grpc.service");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("GrpcServiceGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void serviceImplOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "serviceImpl", TEMPLATE_DIRECTORY_NAME, "{0}ServiceImpl.java", true, "uns.ftn.mbrs.service.impl");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceImplGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void mainOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "main", TEMPLATE_DIRECTORY_NAME, "{0}.java", true, "uns.ftn.mbrs");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("MainGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void jspNavbarOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(WEBAPP_DIR, "navbar", TEMPLATE_DIRECTORY_NAME, "navbar.jsp", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("JspNavbarGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void jspOverviewOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(WEBAPP_DIR, "entityOverview", TEMPLATE_DIRECTORY_NAME, "{0}Overview.jsp", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("JspOverviewGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void landingPageOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(WEBAPP_DIR, "landingPage", TEMPLATE_DIRECTORY_NAME, "landingPage.jsp", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("LandingPageGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void jspDetailsOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(WEBAPP_DIR, "entity_details", TEMPLATE_DIRECTORY_NAME, "{0}Details.jsp", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("JspDetailsGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void jspFormOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(WEBAPP_DIR, "entity_form", TEMPLATE_DIRECTORY_NAME, "{0}Form.jsp", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("JspFormGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }


    private NMAction[] getSubmenuActions() {
        return new NMAction[]{
                new GenerateAction("Generate"),
        };
    }

    public boolean close() {
        return true;
    }

    public boolean isSupported() {
        return true;
    }

    private void repositoryOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "repository", "templates", "{0}Repository.java", true, "uns.ftn.mbrs.repository");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepoGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void pomOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions("c:/mbrs", "pom", "templates", "pom.xml", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("PomGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void appPropOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(DirName, "applicationproperties", "templates", "application.yml", true, "");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("AppPropertiesGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }


}


