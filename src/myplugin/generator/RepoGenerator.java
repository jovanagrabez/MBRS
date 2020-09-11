package myplugin.generator;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.utils.GeneratorHandler;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepoGenerator extends GeneratorHandler {

    public RepoGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    public void generate() {
        super.generate();
    }
}
