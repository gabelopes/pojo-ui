package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@Getter
@AllArgsConstructor
public class ResourceReadException extends RuntimeException {
  private String resourcePath;

  @Override
  public String getMessage() {
    return "Resource '" + this.getResourcePath() + "' could not be read";
  }
}
