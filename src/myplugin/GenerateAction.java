package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.AppPropertiesGenerator;
import myplugin.generator.EJBGenerator;
import myplugin.generator.PomGenerator;
import myplugin.generator.RepoGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction{
	
	
	public GenerateAction(String name) {			
		super("", name, null, null);		
	}

	public void actionPerformed(ActionEvent evt) {
		
		if (Application.getInstance().getProject() == null) return;
		Package root =  Application.getInstance().getProject().getModel();
		if (root == null) return;
	
		ModelAnalyzer analyzer = new ModelAnalyzer(root, "ejb");
		
		try {

			analyzer.prepareModel();
			GeneratorOptions go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EJBGenerator");			
			EJBGenerator generator = new EJBGenerator(go);
			generator.generate();
			JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: " + go.getOutputPath() +
					                         ", package: " + go.getFilePackage());
			exportToXml();

			//generate repository
			generateRepository(analyzer, root, go);
			generatePom(analyzer, root, go);
			generateAppProperties(analyzer,root,go);

		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 			
	}
	
	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == 
			JOptionPane.OK_OPTION)
		{	
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
					
				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());				
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());				
				}		             
			}
		}	
	}

	private void generateModel(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelGenerator");
	//	ModelLayerGenerator ejbGenerator = new ModelLayerGenerator(generatorOptions);
	//	ejbGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}


	private void generateRepository(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root,"uns.ftn.mbrs.repository");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepoGenerator");
		RepoGenerator repositoryGenerator = new RepoGenerator(generatorOptions);
		repositoryGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generatePom(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root,"uns.ftn.mbrs.pom");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("PomGenerator");
		PomGenerator pomGenerator = new PomGenerator(generatorOptions);
		pomGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateAppProperties(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root,"");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("AppPropertiesGenerator");
		AppPropertiesGenerator repositoryGenerator = new AppPropertiesGenerator(generatorOptions);
		repositoryGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

}