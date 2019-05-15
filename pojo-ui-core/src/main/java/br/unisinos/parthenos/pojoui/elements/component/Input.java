package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.annotation.Accepts;
import br.unisinos.parthenos.pojoui.elements.Component;
import j2html.tags.DomContent;

import java.lang.reflect.Field;

import static j2html.TagCreator.input;

@Accepts({ String.class, Number.class, short.class, int.class, long.class, float.class, double.class, byte.class })
public class Input extends Component {
  public Input(Field field) {
    super(field);
  }

  private String getType() {
    if (this.getField().getType().equals(String.class)) {
      return "text";
    }

    return "number";
  }

  @Override
  protected DomContent composeContent() {
    return

      input()
        .withType(this.getType());
  }
}
