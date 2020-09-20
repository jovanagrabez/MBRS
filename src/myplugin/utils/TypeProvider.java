package myplugin.utils;

import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.ProjectOptions;
import myplugin.generator.options.TypeMapping;

import java.util.ArrayList;
import java.util.List;

public final class TypeProvider {
    private TypeProvider() {

    }

    public static List<String> getJavaTypes() {
        List<String> javaTypes = new ArrayList<>();
        List<TypeMapping> typeMappings = ProjectOptions.getProjectOptions().getTypeMappings();

        for (TypeMapping typeMapping : typeMappings) {
            javaTypes.add(typeMapping.getDestType());
        }

        return javaTypes;
    }

    public static List<String> getEnumerationTypes(FMModel model) {
        List<String> enumerationTypes = new ArrayList<>();
        List<FMEnumeration> enums = model.getEnumerations();

        for (FMEnumeration enumeration : enums) {
            enumerationTypes.add(enumeration.getName());
        }

        return enumerationTypes;
    }
}
