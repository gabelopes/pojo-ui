package br.unisinos.parthenos.pojoui;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Environment {
  public static File getExecutionFolder() {
    return Environment.getExecutionPath().toFile();
  }

  public static Path getExecutionPath() {
    final URL executableLocation = Environment.class.getProtectionDomain().getCodeSource().getLocation();
    final Path executablePath = Paths.get(executableLocation.getFile());

    return executablePath.getParent();
  }
}
