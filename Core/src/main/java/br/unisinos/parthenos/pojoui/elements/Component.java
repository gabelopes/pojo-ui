package br.unisinos.parthenos.pojoui.elements;

import br.unisinos.parthenos.pojoui.annotation.Accepts;
import br.unisinos.parthenos.pojoui.exception.ComponentNotAllowedException;
import j2html.tags.DomContent;
import lombok.Getter;

import java.lang.reflect.Field;

import static j2html.TagCreator.*;
import static j2html.TagCreator.label;

@Getter
public abstract class Component extends Element implements Composable {
  private static final String COMPONENT_WRAPPER_CLASS = "component-wrapper";
  private static final String COMPONENT_LABEL_CLASS = "component-label";
  private static final String COMPONENT_CONTENT_CLASS = "component-content";

  private String label;
  private boolean visible;
  private int position;
  private Field field;

  public Component(Field field) {
    this.field = field;
    this.checkCompatibility(field);
  }

  private void checkCompatibility(Field field) {
    final Accepts acceptsAnnotation = this.getClass().getAnnotation(Accepts.class);

    if (acceptsAnnotation != null) {
      final Class<?>[] acceptedClasses = acceptsAnnotation.value();
      final Class<?> elementType = field.getType();

      if (this.isCompatible(elementType, acceptedClasses)) {
        return;
      }
    }

    throw new ComponentNotAllowedException(this.getClass(), field.getType());
  }

  private boolean isCompatible(Class<?> elementClass, Class<?>[] acceptedClasses) {
    for (Class<?> acceptedClass : acceptedClasses) {
      if (acceptedClass.isAssignableFrom(elementClass)) {
        return true;
      }
    }

    return false;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  protected abstract DomContent composeContent();

  @Override
  public DomContent compose() {
    return

      div(
        div(this.getLabel()).withClass(COMPONENT_LABEL_CLASS),

        div(
          this.composeContent()
        ).withClass(COMPONENT_CONTENT_CLASS)
      ).withId(this.getId()).withClass(COMPONENT_WRAPPER_CLASS);
  }
}
