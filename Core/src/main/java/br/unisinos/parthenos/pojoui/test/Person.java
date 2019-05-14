package br.unisinos.parthenos.pojoui.test;

import br.unisinos.parthenos.pojoui.annotation.Field;
import br.unisinos.parthenos.pojoui.annotation.Panel;
import lombok.Getter;
import lombok.Setter;

@Panel
@Getter
@Setter
public class Person {
  @Field(label = "Name")
  private String name;

  @Field(position = 1)
  private int age;

  @Field(label = "Gender", position = 2)
  private Gender gender;

  @Field(label = "What are her/his tastes?", position = 3)
  private Taste[] tastes;
}
