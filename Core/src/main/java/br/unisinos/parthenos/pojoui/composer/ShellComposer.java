package br.unisinos.parthenos.pojoui.composer;

import br.unisinos.parthenos.pojoui.elements.Panel;
import br.unisinos.parthenos.pojoui.elements.Shell;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class ShellComposer implements Composer<Shell> {
  private Class<?>[] classes;

  public ShellComposer(Class<?>... classes) {
    this.classes = classes;
  }

  private boolean isPanel(Class<?> clazz) {
    return clazz.getAnnotation(br.unisinos.parthenos.pojoui.annotation.Panel.class) != null;
  }

  private List<Panel> createPanels() {
    return Stream.of(this.getClasses())
      .filter(this::isPanel)
      .map(PanelComposer::new)
      .map(PanelComposer::compose)
      .collect(Collectors.toList());
  }

  @Override
  public Shell compose() {
    return new Shell("Panels", createPanels());
  }
}
