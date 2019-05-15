package br.unisinos.parthenos.pojoui.composer.shell;

import br.unisinos.parthenos.pojoui.composer.Composer;
import br.unisinos.parthenos.pojoui.elements.Component;
import br.unisinos.parthenos.pojoui.exception.ComponentNotFoundException;
import br.unisinos.parthenos.pojoui.pool.DefaultComponentPool;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Getter
@AllArgsConstructor
public class ComponentComposer implements Composer<Component> {
  private Field field;

  private br.unisinos.parthenos.pojoui.annotation.Field getFieldAnnotation() {
    return this.getField().getAnnotation(br.unisinos.parthenos.pojoui.annotation.Field.class);
  }

  private Component instanceComponent() {
    final Class<? extends Component> componentClass = this.getComponent();

    try {
      return componentClass
        .getConstructor(Field.class)
        .newInstance(this.getField());
    } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
      throw new ComponentNotFoundException(field.getType());
    }
  }

  private Class<? extends Component> getDefaultComponent() {
    return DefaultComponentPool.forType(this.getField().getType());
  }

  private Class<? extends Component> getComponent() {
    final Class<? extends Component> componentClass = this.getFieldAnnotation().component();

    if (componentClass.equals(Component.class)) {
      return this.getDefaultComponent();
    }

    return componentClass;
  }

  private String getLabel() {
    final String label = this.getFieldAnnotation().label();

    if (label.isEmpty()) {
      return this.getField().getName();
    }

    return label;
  }

  @Override
  public Component compose() {
    final String label = this.getLabel();
    final boolean visible = this.getFieldAnnotation().visible();
    final int position = this.getFieldAnnotation().position();
    final Component component = this.instanceComponent();

    component.setLabel(label);
    component.setVisible(visible);
    component.setPosition(position);

    return component;
  }
}
