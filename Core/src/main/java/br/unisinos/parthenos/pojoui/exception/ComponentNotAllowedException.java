package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ComponentNotAllowedException extends RuntimeException {
  private Class<?> componentClass;
  private Class<?> elementClass;

  @Override
  public String getMessage() {
    return "Component '" + this.getComponentClass() + "' not allowed for element type '" + this.getElementClass() + "'";
  }
}
