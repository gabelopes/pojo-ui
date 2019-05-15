package br.unisinos.parthenos.pojoui;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CLI {
  @Option(names = { "-o", "--output" })
  private File outputFolder;

  @Option(names = { "-c", "--classes" }, arity = "0..n")
  private Set<String> classes;

  @Option(names = { "-t", "--title"}, defaultValue = "")
  private String title;

  @Parameters
  private Set<File> jars;
}
