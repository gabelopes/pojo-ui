package br.unisinos.parthenos.pojoui.elements;

import j2html.tags.DomContent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static j2html.TagCreator.div;
import static j2html.TagCreator.each;

@Getter
public class Shell implements Element, Composable, Renderable {
  private static final String SHELL_CLASS = "shell";
  private static final String TAB_CONTAINER_CLASS = "tab-container";
  private static final String SHELL_CONTENT_CLASS = "shell-content";
  private static final String TAB_BUTTON_CLASS = "tab-button";
  private static final String TAB_CONTENT_CLASS = "tab-content";
  private static final String TAB_PREFIX = "tab-";
  private static final String CONTENT_PREFIX = "content-";
  private static final String PANEL_PREFIX = "panel";

  private String title;
  private List<Panel> panels;

  public Shell(String title, List<Panel> panels) {
    this.title = title;
    this.panels = panels;
  }

  private DomContent renderTab(Panel panel) {
    return

      div(panel.getLabel())
        .withId(TAB_PREFIX + panel.getId())
        .withClass(TAB_BUTTON_CLASS);
  }

  private DomContent renderContent(Panel panel) {
    return

      div(panel.compose())
        .withId(CONTENT_PREFIX + panel.getId())
        .withClass(TAB_CONTENT_CLASS);
  }

  private List<Panel> getRenderablePanels() {
    return this.getPanels()
      .stream()
      .filter(Panel::isVisible)
      .sorted(Comparator.comparing(Panel::getPosition))
      .collect(Collectors.toList());
  }

  private void setOrderedPositions(List<Panel> panels) {
    for (int i = 0; i < panels.size(); i++) {
      final Panel panel = panels.get(i);
      panel.setPosition(i);
    }
  }

  @Override
  public DomContent compose() {
    final List<Panel> renderablePanels = this.getRenderablePanels();

    this.setOrderedPositions(renderablePanels);

    return

      div(
        div(each(renderablePanels, this::renderTab)).withClass(TAB_CONTAINER_CLASS),
        div(each(renderablePanels, this::renderContent)).withClass(SHELL_CONTENT_CLASS)
      ).withId(this.getId()).withClass(SHELL_CLASS);
  }

  @Override
  public String render() {
    return this.compose().render();
  }
}
