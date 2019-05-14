package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ComponentNotFoundException extends RuntimeException {
  private Class<?> typeClass;

  @Override
  public String getMessage() {
    return "Could not find a component for type '" + typeClass + "'";
  }
}
