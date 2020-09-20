package myplugin.generator;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.options.GeneratorOptions;
import myplugin.utils.TypeProvider;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class JspOverviewGenerator extends BasicGenerator {

    private static final String MODEL_SELECTOR = "model";

    public JspOverviewGenerator(GeneratorOptions generatorOptions) {
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
            Map<String, Object> context = new HashMap<>();
            List<FMProperty> classProperties = clazz.getProperties();

            String modelPackage = clazz.getTypePackage();
            String controllerPackage = replacePackageFragment(modelPackage, MODEL_SELECTOR, "controller");
            String servicePackage = replacePackageFragment(modelPackage, MODEL_SELECTOR, "service");

            try {
                Writer out = getWriter(uncapFirst(clazz.getName()), "webapp.WEB-INF.jsp");
                if (nonNull(out)) {
                    List<String> javaTypes = TypeProvider.getJavaTypes();
                    List<String> enumerationTypes = TypeProvider.getEnumerationTypes(FMModel.getInstance());

                    Map<String, FMProperty> entityRelations = new HashMap<>();
                    for (FMProperty property : classProperties) {
                        if (!javaTypes.contains(property.getType()) && !enumerationTypes.contains(property.getType())) {
                            entityRelations.put(property.getType(), property);
                        }
                    }

                    context.put("class", clazz);
                    context.put("class_package", controllerPackage);
                    context.put("service_package", servicePackage);

                    context.put("properties", classProperties);
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
