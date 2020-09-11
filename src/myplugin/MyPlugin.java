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

    String pluginDir = null;
    public static final String GEN_DIR = "c:/mbrs/src/main/java";
    public static  final  String DirName="c:/mbrs/src/main/resources";

    public void init() {
        JOptionPane.showMessageDialog(null, "My Plugin init");

        pluginDir = getDescriptor().getPluginDirectory().getPath();

        // Creating submenu in the MagicDraw main menu
        ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
        manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));

        modelOptions();
        controllerOptions();
        serviceOptions();
        serviceImplOptions();
        mainOptions();
        repositoryOptions();
		pomOptions();
		appPropOptions();
    }

    private void modelOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "ejbclass", "templates", "{0}.java", true, "uns.ftn.mbrs.model");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void controllerOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "controller", "templates", "{0}Controller.java", true, "uns.ftn.mbrs.controller");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ControllerGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void serviceOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "service", "templates", "{0}Service.java", true, "uns.ftn.mbrs.service");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }


    private void serviceImplOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "serviceImpl", "templates", "{0}ServiceImpl.java", true, "uns.ftn.mbrs.service.impl");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceImplGenerator", generatorOptions);
        generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
    }

    private void mainOptions() {
        GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "main", "templates", "{0}.java", true, "uns.ftn.mbrs");
        ProjectOptions.getProjectOptions().getGeneratorOptions().put("MainGenerator", generatorOptions);
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
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/temp", "repository", "templates", "{0}Repository.java", true, "uns.ftn.mbrs.repository");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepoGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private  void  pomOptions(){
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/temp", "pom", "templates", "pom.xml", true, "uns.ftn.mbrs.pom");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("PomGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private  void  appPropOptions(){
		GeneratorOptions generatorOptions = new GeneratorOptions(DirName, "applicationproperties", "templates", "application.properties.xml", true, "");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("AppPropertiesGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}




}


