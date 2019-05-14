package br.unisinos.parthenos.pojoui.test;

import br.unisinos.parthenos.pojoui.annotation.Field;
import br.unisinos.parthenos.pojoui.annotation.Panel;
import br.unisinos.parthenos.pojoui.elements.component.ComboBox;
import br.unisinos.parthenos.pojoui.elements.component.TextArea;
import lombok.Getter;
import lombok.Setter;

@Panel(label = "Employee", position = 1)
@Getter
@Setter
public class Employee extends Person {
  @Field(label = "Is intern?", component = ComboBox.class)
  private boolean intern;

  @Field(label = "Describe", component = TextArea.class)
  private String observations;
}
