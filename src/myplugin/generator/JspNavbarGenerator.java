package myplugin.generator;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.globals.ApplicationGlobals;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;
import static myplugin.globals.WebGlobals.*;

public class JspNavbarGenerator extends BasicGenerator {
    public JspNavbarGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    public void generate() {
        try {
            super.generate();
            List<FMClass> classes = FMModel.getInstance().getClasses();
            Writer out = getWriter("", "webapp.WEB-INF.jsp");
            Map<String, Object> context = new HashMap<>();

            if (nonNull(out)) {
                fillContext(context);
                context.put("entities", classes);

                getTemplate().process(context, out);
                out.flush();
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private void fillContext(Map<String, Object> context) {
        context.put(BOOTSTRAP_CSS_KEY_NAME, BOOTSTRAP_CSS);
        context.put(BOOTSTRAP_JS_KEY_NAME, BOOTSTRAP_JS);
        context.put(JQUERY_KEY_NAME, JQUERY);
        context.put(DATATABLES_CSS_KEY_NAME, DATATABLES_CSS);
        context.put(DATATABLES_JS_KEY_NAME, DATATABLES_JS);
        context.put("app_name", ApplicationGlobals.APPLICATION_NAME);
    }
}
