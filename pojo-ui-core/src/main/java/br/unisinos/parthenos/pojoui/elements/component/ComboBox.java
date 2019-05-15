package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.annotation.Accepts;
import j2html.tags.DomContent;

import java.lang.reflect.Field;

import static j2html.TagCreator.*;

@Accepts({ Boolean.class, boolean.class, Enum.class })
public class ComboBox extends ListingComponent {
  public ComboBox(Field field) {
    super(field);
  }

  @Override
  protected DomContent composeContent() {
    return

      select(
        each(this.getListing(), (item) -> option(item).withValue(item))
      );
  }
}
