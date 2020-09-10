package myplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FMClass extends FMType {	
	
	private String visibility;
	

	//Class properties
	private List<FMProperty> FMProperties = new ArrayList<FMProperty>();
	private List<String> importedPackages = new ArrayList<String>();
	private List<FMLinkedProperty> FMLinkedProperty = new ArrayList<>();


	
	
	public FMClass(String name, String classPackage, String visibility) {
		super(name, classPackage);		
		this.visibility = visibility;
	}

	public List<myplugin.generator.fmmodel.FMLinkedProperty> getFMLinkedProperty() {
		return FMLinkedProperty;
	}

	public void setFMLinkedProperty(List<myplugin.generator.fmmodel.FMLinkedProperty> FMLinkedProperty) {
		this.FMLinkedProperty = FMLinkedProperty;
	}



	public List<FMProperty> getProperties(){
		return FMProperties;
	}
	
	public Iterator<FMProperty> getPropertyIterator(){
		return FMProperties.iterator();
	}
	
	public void addProperty(FMProperty property){
		FMProperties.add(property);		
	}

	public void addLinkedProperty (FMLinkedProperty property) {FMLinkedProperty.add(property);}
	public int getPropertyCount(){
		return FMProperties.size();
	}
	
	public List<String> getImportedPackages(){
		return importedPackages;
	}

	public Iterator<String> getImportedIterator(){
		return importedPackages.iterator();
	}
	
	public void addImportedPackage(String importedPackage){
		importedPackages.add(importedPackage);		
	}
	
	public int getImportedCount(){
		return FMProperties.size();
	}
	
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}	

	
	
}
