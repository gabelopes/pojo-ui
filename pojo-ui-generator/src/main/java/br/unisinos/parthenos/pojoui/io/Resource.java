package br.unisinos.parthenos.pojoui.io;

import br.unisinos.parthenos.pojoui.exception.ResourceReadException;
import br.unisinos.parthenos.pojoui.exception.ResourceWriteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resource {
  private static URL getResourceURL(String path) {
    return Resource.class.getClassLoader().getResource(path);
  }

  public static void copy(String resourcePath, Path toPath) {
    final URL resourceURL = Resource.getResourceURL(resourcePath);

    Resource.copy(resourceURL, toPath);
  }

  public static void copy(URL resourceURL, Path toPath) {
    try {
      FileUtils.copyURLToFile(resourceURL, toPath.toFile());
    } catch (IOException e) {
      throw new ResourceWriteException(resourceURL.getFile());
    }
  }

  public static String read(String resourcePath) {
    try {
      return IOUtils.resourceToString(resourcePath, Charset.defaultCharset(), Resource.class.getClassLoader());
    } catch(IOException ignored) {
      throw new ResourceReadException(resourcePath);
    }
  }
}
