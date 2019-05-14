package br.unisinos.parthenos.pojoui.io;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Resource {
  private static Path getResourcePath(String path) {
    final URL resourceURL = Resource.class.getClassLoader().getResource(path);

    if (resourceURL == null) { return null; }

    return Paths.get(resourceURL.getFile());
  }

  public static boolean copy(String resource, Path toPath) {
    final Path resourcePath = Resource.getResourcePath(resource);
    return Resource.copy(resourcePath, toPath);
  }

  public static boolean copy(Path resourcePath, Path toPath) {
    try {
      Files.copy(resourcePath, toPath);

      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public static String asString(String path) {
    final Path resourcePath = Resource.getResourcePath(path);

    try {
      final List<String> resourceLines = Files.readAllLines(resourcePath);

      return String.join(System.lineSeparator(), resourceLines);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
