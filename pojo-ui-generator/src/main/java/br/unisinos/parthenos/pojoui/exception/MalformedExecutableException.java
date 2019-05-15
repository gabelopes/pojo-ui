package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MalformedExecutableException extends RuntimeException {
  private String jar;

  @Override
  public String getMessage() {
    return "JAR file denoted by '" + this.getJar() + "' is malformed";
  }
}
