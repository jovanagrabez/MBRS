package myplugin.generator;

import com.nomagic.magicdraw.core.Application;
import freemarker.template.TemplateException;
import myplugin.generator.options.GeneratorOptions;
import myplugin.globals.ApplicationGlobals;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LandingPageGenerator extends BasicGenerator {
    public static final String PACKAGE_NAME = "webapp.WEB-INF.jsp";

    public LandingPageGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Map<String, Object> context = new HashMap<>();
        Writer out;
        try {
            out = getWriter(Application.getInstance().getProject().getName(), PACKAGE_NAME);
            if (out != null) {
                context.put("appName", ApplicationGlobals.APPLICATION_NAME);
                getTemplate().process(context, out);
                out.flush();
            }
        } catch (TemplateException | IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
