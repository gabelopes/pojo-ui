package br.unisinos.parthenos.pojoui;

import br.unisinos.parthenos.pojoui.exception.MalformedExecutableException;
import br.unisinos.parthenos.pojoui.generator.Generator;
import br.unisinos.parthenos.pojoui.generator.shell.ShellGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public class Orchestrator {
  private CLI cli;

  private File getOutputFolder() {
    final File outputFolder = cli.getOutputFolder();

    if (outputFolder == null) {
      return Environment.getExecutionFolder();
    }

    return outputFolder;
  }

  private URL getURL(URI uri) {
    try {
      return uri.toURL();
    } catch (MalformedURLException ignored) {
      throw new MalformedExecutableException(uri.toString());
    }
  }

  private ClassLoader createClassLoader() {
    final URL[] executables = cli.getJars()
      .stream()
      .map(File::toURI)
      .map(this::getURL)
      .toArray(URL[]::new);

    return new URLClassLoader(executables, this.getClass().getClassLoader());
  }

  public void execute() {
    final ClassLoader classLoader = this.createClassLoader();
    final ClassResolver classResolver = new ClassResolver(classLoader, cli.getClasses());
    final Set<Class<?>> classes = classResolver.resolve();
    final Generator generator = new ShellGenerator(this.getOutputFolder(), cli.getTitle(), classes);

    generator.generate();
  }
}
