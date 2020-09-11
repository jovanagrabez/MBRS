package myplugin.generator;

import com.nomagic.magicdraw.core.Application;
import freemarker.template.TemplateException;
import myplugin.generator.options.GeneratorOptions;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class AppPropertiesGenerator extends BasicGenerator{

    public AppPropertiesGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    public void generate() {

        String applicationproperties = "application.properties";
        String typePackappprop = "";

        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Writer out;
        Map<String, Object> context = new HashMap<String, Object>();
        try {
            out = getWriter(applicationproperties, typePackappprop);
                context.clear();
                context.put("app_name", Application.getInstance().getProject().getName());
                getTemplate().process(context, out);
                out.flush();

        } catch (TemplateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
