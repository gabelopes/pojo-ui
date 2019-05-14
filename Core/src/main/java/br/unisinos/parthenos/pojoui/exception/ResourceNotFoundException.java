package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
  private String resource;

  @Override
  public String getMessage() {
    return "Resource denoted by '" + this.getResource() + "' was not found";
  }
}
