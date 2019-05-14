package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.annotation.Accepts;

import java.lang.reflect.Field;

@Accepts({ Boolean.class, boolean.class, Enum.class })
public class RadioButtons extends InputListingComponent {
  public RadioButtons(Field field) {
    super(field);
  }

  @Override
  public String getInputType() {
    return "radio";
  }
}
