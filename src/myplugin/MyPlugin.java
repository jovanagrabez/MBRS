package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null;
	public static final String GEN_DIR = "c:/temp/mbrs/src/main/java";
	public static  final  String DirName="c:/mbrs/src/main/resources";
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */

		//modelOptions();
		//repositoryOptions();
		//for test purpose only:
		GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass", "templates", "{0}.java", true, "ejb");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);

		ejbOptions.setTemplateDir(pluginDir + File.separator + ejbOptions.getTemplateDir()); //apsolutna putanja
		repositoryOptions();
		pomOptions();
		appPropOptions();
	}

	private void modelOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions(GEN_DIR, "ejbclass", "templates", "{0}.java", true, "uns.ftn.mbrs.model");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ModelGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
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



	private NMAction[] getSubmenuActions()
	{
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
}


