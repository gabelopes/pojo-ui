package br.unisinos.parthenos.pojoui.composer;

import br.unisinos.parthenos.pojoui.elements.Component;
import br.unisinos.parthenos.pojoui.elements.Panel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PanelComposer implements Composer<Panel> {
  private Class<?> panelClass;

  private br.unisinos.parthenos.pojoui.annotation.Panel getPanelAnnotation() {
    return this.getPanelClass().getAnnotation(br.unisinos.parthenos.pojoui.annotation.Panel.class);
  }

  private boolean isField(Field field) {
    return field.getAnnotation(br.unisinos.parthenos.pojoui.annotation.Field.class) != null;
  }

  private Set<Field> getFields() {
    return ReflectionUtils.getAllFields(this.getPanelClass());
  }

  private List<Component> createComponents() {
    return this.getFields()
      .stream()
      .filter(this::isField)
      .map(ComponentComposer::new)
      .map(ComponentComposer::compose)
      .collect(Collectors.toList());
  }

  private String getLabel() {
    final String label = this.getPanelAnnotation().label();

    if (label.isEmpty()) {
      return this.getPanelClass().getSimpleName();
    }

    return label;
  }

  @Override
  public Panel compose() {
    final String label = this.getLabel();
    final boolean visible = this.getPanelAnnotation().visible();
    final int position = this.getPanelAnnotation().position();
    final List<Component> components = this.createComponents();

    return new Panel(label, visible, position, components);
  }
}
