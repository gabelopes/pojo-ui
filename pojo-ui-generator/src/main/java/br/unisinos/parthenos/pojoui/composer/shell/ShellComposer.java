package br.unisinos.parthenos.pojoui.composer.shell;

import br.unisinos.parthenos.pojoui.composer.Composer;
import br.unisinos.parthenos.pojoui.elements.Panel;
import br.unisinos.parthenos.pojoui.elements.Shell;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class ShellComposer implements Composer<Shell> {
  private Set<Class<?>> classes;

  public ShellComposer(Set<Class<?>> classes) {
    this.classes = classes;
  }

  private boolean isPanel(Class<?> clazz) {
    return clazz.getAnnotation(br.unisinos.parthenos.pojoui.annotation.Panel.class) != null;
  }

  private List<Panel> createPanels() {
    return this.getClasses()
      .stream()
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
