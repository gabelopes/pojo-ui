package br.unisinos.parthenos.pojoui.stereotype;

import br.unisinos.parthenos.pojoui.annotation.Accept;
import br.unisinos.parthenos.pojoui.exception.ComponentNotAllowedException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public abstract class Component<T> {
  private T element;

  public Component(T element) {
    this.element = element;
    this.checkCompatibility();
  }

  private void checkCompatibility() {
    final Accept acceptAnnotation = this.getClass().getAnnotation(Accept.class);

    if (acceptAnnotation != null) {
      final Class<?>[] acceptedClasses = acceptAnnotation.value();
      final Class<?> elementType = this.getElement().getClass();

      if (Arrays.asList(acceptedClasses).contains(elementType)) { return; }
    }

    throw new ComponentNotAllowedException(this.getClass(), element.getClass());
  }

  public abstract String render();
}
