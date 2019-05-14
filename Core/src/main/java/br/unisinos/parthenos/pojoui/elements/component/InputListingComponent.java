package br.unisinos.parthenos.pojoui.elements.component;

import j2html.tags.DomContent;

import java.lang.reflect.Field;

import static j2html.TagCreator.*;
import static j2html.TagCreator.label;

public abstract class InputListingComponent extends ListingComponent {
  private static final String GROUP_INFIX = "group";

  public InputListingComponent(Field field) {
    super(field);
  }

  public abstract String getInputType();

  private String getGroupName() {
    return this.getField().getDeclaringClass().getSimpleName()
         + this.getField().getName()
         + this.getInputType()
         + GROUP_INFIX
         + this.getPosition();
  }

  @Override
  public DomContent composeContent() {
    final String groupName = this.getGroupName();

    return

      each(this.getListing(), (item) ->
        div(
          input()
            .withName(groupName)
            .withValue(item)
            .withType(this.getInputType()),

          label(item)
        )
      );
  }
}
