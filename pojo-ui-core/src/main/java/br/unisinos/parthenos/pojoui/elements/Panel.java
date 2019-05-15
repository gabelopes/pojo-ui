package br.unisinos.parthenos.pojoui.elements;

import j2html.tags.DomContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static j2html.TagCreator.div;
import static j2html.TagCreator.each;

@Getter
@Setter
public class Panel implements Element, Composable {
  private static final String PANEL_CONTENT_CLASS = "panel-content";
  private static final String COMPONENT_AREA_CLASS = "component-area";
  private static final String COMPONENT_PREFIX = "component-";

  private String label;
  private boolean visible;
  private int position;
  private List<Component> components;

  public Panel(String label, boolean visible, int position, List<Component> components) {
    this.label = label;
    this.visible = visible;
    this.position = position;
    this.components = components;
  }

  private List<Component> getRenderableComponents() {
    return this.getComponents()
      .stream()
      .filter(Component::isVisible)
      .sorted(Comparator.comparing(Component::getPosition))
      .collect(Collectors.toList());
  }

  private String getComposedComponentId(Component component) {
    return this.getId() + "-" + component.getId();
  }

  private DomContent renderComponentArea(Component component) {
    return div(component.compose())
      .withId(this.getComposedComponentId(component))
      .withClass(COMPONENT_AREA_CLASS);
  }

  private void setOrderedPositions(List<Component> components) {
    for (int i = 0; i < components.size(); i++) {
      final Component component = components.get(i);
      component.setPosition(i);
    }
  }

  @Override
  public DomContent compose() {
    final List<Component> renderableComponents = this.getRenderableComponents();

    this.setOrderedPositions(renderableComponents);

    return

      div(
        each(renderableComponents, this::renderComponentArea)
      ).withId(this.getId()).withClass(PANEL_CONTENT_CLASS);
  }
}
