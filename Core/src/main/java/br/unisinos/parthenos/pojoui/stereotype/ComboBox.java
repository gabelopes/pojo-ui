package br.unisinos.parthenos.pojoui.stereotype;

import br.unisinos.parthenos.pojoui.annotation.Accept;

@Accept({ Boolean.class, boolean.class, Iterable.class })
public class ComboBox<T> extends Component<T> {
  public ComboBox(T element) {
    super(element);
  }

  @Override
  public String render() {
    return null;
  }
}
