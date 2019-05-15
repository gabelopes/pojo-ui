package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassLoadException extends RuntimeException {
  private String className;

  @Override
  public String getMessage() {
    return "Class for name '" + this.getClassName() + "' could not be loaded";
  }
}
