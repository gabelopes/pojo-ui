package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.annotation.Accepts;

import java.lang.reflect.Field;

@Accepts({ Enum[].class })
public class CheckBoxes extends InputListingComponent {
  public CheckBoxes(Field field) {
    super(field);
  }

  @Override
  public String getInputType() {
    return "checkbox";
  }
}
