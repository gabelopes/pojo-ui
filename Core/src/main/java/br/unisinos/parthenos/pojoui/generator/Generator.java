package br.unisinos.parthenos.pojoui.generator;

import br.unisinos.parthenos.pojoui.composer.ShellComposer;
import br.unisinos.parthenos.pojoui.elements.Shell;
import br.unisinos.parthenos.pojoui.exception.ResourceNotFoundException;
import br.unisinos.parthenos.pojoui.io.Resource;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Generator {
  private static final String TEMPLATE_FOLDER_PATH = "Template/";
  private static final String SHELL_HTML = "shell.html";
  private static final String SHELL_CSS = "shell.css";
  private static final String SHELL_JS = "shell.js";
  private static final String SHELL_HTML_PATH = TEMPLATE_FOLDER_PATH + SHELL_HTML;
  private static final String SHELL_CSS_PATH = TEMPLATE_FOLDER_PATH + SHELL_CSS;
  private static final String SHELL_JS_PATH = TEMPLATE_FOLDER_PATH + SHELL_JS;
  private static final String CONTENT_PLACEHOLDER = "${content}";

  private File outputFolder;
  private String[] classesNames;

  public Generator(File outputFolder, String... classesNames) {
    this.outputFolder = outputFolder;
    this.classesNames = classesNames;
  }

  private Path resolveOutputPathTo(String file) {
    return this.getOutputFolder().toPath().resolve(file);
  }

  private void writeShellHtml(String shellHtml) {
    final Path shellHtmlPath = this.resolveOutputPathTo(SHELL_HTML);

    try {
      Files.write(shellHtmlPath, shellHtml.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean createOutputFolder() {
    if (this.getOutputFolder().exists()) {
      return false;
    }

    return this.getOutputFolder().mkdir();
  }

  private void writeFiles() {
    this.createOutputFolder();
  }

  private Class<?>[] resolveClasses() {
    final List<Class<?>> classes = new ArrayList<>(this.getClassesNames().length);

    for (String className : this.getClassesNames()) {
      try {
        classes.add(Class.forName(className));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    return classes.toArray(new Class[0]);
  }

  private ShellComposer createShellComposer() {
    return new ShellComposer(this.resolveClasses());
  }

  private String generateShellContent() {
    final ShellComposer shellComposer = this.createShellComposer();
    final Shell shell = shellComposer.compose();

    return shell.render();
  }



  public void generate() {
    this.createOutputFolder();
    final String shellContent = this.generateShellContent();
    final String shellHtml = Resource.asString(SHELL_HTML_PATH);

    if (shellHtml == null) {
      throw new ResourceNotFoundException(SHELL_HTML_PATH);
    }

    final String replacedShellHtml = shellHtml.replace(CONTENT_PLACEHOLDER, shellContent);

    this.writeShellHtml(replacedShellHtml);

    Resource.copy(SHELL_CSS_PATH, this.resolveOutputPathTo(SHELL_CSS));
    Resource.copy(SHELL_JS_PATH, this.resolveOutputPathTo(SHELL_JS));
  }
}
