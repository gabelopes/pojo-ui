package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.annotation.Accepts;
import br.unisinos.parthenos.pojoui.elements.Component;
import j2html.tags.DomContent;

import java.lang.reflect.Field;

import static j2html.TagCreator.textarea;

@Accepts({ String.class })
public class TextArea extends Component {
  public TextArea(Field field) {
    super(field);
  }

  @Override
  protected DomContent composeContent() {
    return
      textarea();
  }
}
