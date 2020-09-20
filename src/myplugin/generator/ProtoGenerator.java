package myplugin.generator;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtoGenerator extends BasicGenerator {
    public static final String PACKAGE_NAME = "uns.ftn.mbrs";

    private static final Map<String, String> grpcType = new HashMap<>();
    static {
        grpcType.put("string", "string");
        grpcType.put("String", "string");
        grpcType.put("long", "int64");
        grpcType.put("Long", "int64");
        grpcType.put("double", "double");
        grpcType.put("Double", "double");
        grpcType.put("int", "int32");
        grpcType.put("Int", "int32");
        grpcType.put("integer", "int32");
        grpcType.put("Integer", "int32");
    }

    public ProtoGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> context = new HashMap<>();
        List<FMClass> classes = FMModel.getInstance().getClasses();

        Writer out = createWriter();

        context.put("classes", classes);
        context.put("package", PACKAGE_NAME);
        context.put("grpcType", grpcType);

        write(context, out);
    }

    private void write(Map<String, Object> context, Writer out) {
        try {
            getTemplate().process(context, out);
            out.flush();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    private Writer createWriter() {
        Writer out = null;
        try {
            out = getWriter("default", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

}
