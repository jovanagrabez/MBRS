package myplugin.generator;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.options.GeneratorOptions;
import myplugin.utils.TypeProvider;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class JspFormGenerator extends BasicGenerator {
    public JspFormGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        List<FMClass> classes = FMModel.getInstance().getClasses();
        for (FMClass clazz : classes) {
            Writer out;
            Map<String, Object> context = new HashMap<>();
            try {
                String modelPackage = clazz.getTypePackage();
                String controllerPackage = replacePackageFragment(modelPackage, "model", "controller");
                String servicePackage = replacePackageFragment(modelPackage, "model", "service");

                out = getWriter(uncapFirst(clazz.getName()), "webapp.WEB-INF.jsp");
                if (nonNull(out)) {
                    List<String> javaTypes = TypeProvider.getJavaTypes();
                    List<String> enumerationTypes = new ArrayList<>();
                    Map<String, List<String>> enumerationValues = new HashMap<>();

                    List<FMEnumeration> enumerations = FMModel.getInstance().getEnumerations();
                    for (FMEnumeration enumVal : enumerations) {
                        enumerationTypes.add(enumVal.getName());
                        List<String> enumValues = new ArrayList<>();
                        enumVal.getValueIterator().forEachRemaining(enumValues::add);
                        enumerationValues.put(enumVal.getName(), enumValues);
                    }

                    Map<String, Object> entityRelations = new HashMap<>();
                    for (FMProperty p : clazz.getProperties()) {
                        if (!javaTypes.contains(p.getType()) && !enumerationTypes.contains(p.getType())) {
                            entityRelations.put(p.getType(), p);
                        }
                    }

                    context.put("class", clazz);
                    context.put("class_package", controllerPackage);
                    context.put("service_package", servicePackage);

                    context.put("enum_types", enumerationTypes);
                    context.put("enum_values", enumerationValues);

                    context.put("properties", clazz.getProperties());
                    context.put("entity_properties", entityRelations);
                    context.put("importedPackages", clazz.getImportedPackages());
                    getTemplate().process(context, out);
                    out.flush();
                }
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}

