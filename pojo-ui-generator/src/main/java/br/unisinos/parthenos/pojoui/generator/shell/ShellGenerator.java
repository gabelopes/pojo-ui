package br.unisinos.parthenos.pojoui.generator.shell;

import br.unisinos.parthenos.pojoui.composer.shell.ShellComposer;
import br.unisinos.parthenos.pojoui.elements.Shell;
import br.unisinos.parthenos.pojoui.exception.OutputFolderResolveException;
import br.unisinos.parthenos.pojoui.exception.ResourceWriteException;
import br.unisinos.parthenos.pojoui.generator.Generator;
import br.unisinos.parthenos.pojoui.io.Resource;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Getter
public class ShellGenerator extends Generator {
  private static final String TEMPLATE_FOLDER_PATH = "Template/";
  private static final String INDEX_HTML = "index.html";
  private static final String STYLE_CSS = "style.css";
  private static final String SHELL_JS = "shell.js";
  private static final String INDEX_HTML_PATH = TEMPLATE_FOLDER_PATH + INDEX_HTML;
  private static final String STYLE_CSS_PATH = TEMPLATE_FOLDER_PATH + STYLE_CSS;
  private static final String SHELL_JS_PATH = TEMPLATE_FOLDER_PATH + SHELL_JS;
  private static final String TITLE_PLACEHOLDER = "${title}";
  private static final String CONTENT_PLACEHOLDER = "${content}";

  private String title;

  public ShellGenerator(File outputFolder, String title, Set<Class<?>> classes) {
    super(outputFolder, classes);
    this.title = title;
  }

  private Path resolveOutput(String file) {
    return this.getOutputFolder().toPath().resolve(file);
  }

  private String prepareHTML(String html) {
    final String content = this.generateContent();

    return html
      .replace(TITLE_PLACEHOLDER, this.getTitle())
      .replace(CONTENT_PLACEHOLDER, content);
  }

  private void writeHTML(String html) {
    final Path htmlPath = this.resolveOutput(INDEX_HTML);

    try {
      Files.write(htmlPath, html.getBytes());
    } catch (IOException ignored) {
      throw new ResourceWriteException(htmlPath.toString());
    }
  }

  private String generateContent() {
    final ShellComposer shellComposer = new ShellComposer(this.getClasses());
    final Shell shell = shellComposer.compose();

    return shell.render();
  }

  private boolean createOutputFolder() {
    if (this.getOutputFolder().exists()) { return true; }

    return this.getOutputFolder().mkdir();
  }

  public void generate() {
    if (!this.createOutputFolder()) {
      throw new OutputFolderResolveException(this.getOutputFolder().toString());
    }

    final String html = Resource.read(INDEX_HTML_PATH);
    final String preparedHTML = this.prepareHTML(html);

    this.writeHTML(preparedHTML);

    Resource.copy(STYLE_CSS_PATH, this.resolveOutput(STYLE_CSS));
    Resource.copy(SHELL_JS_PATH, this.resolveOutput(SHELL_JS));
  }
}
