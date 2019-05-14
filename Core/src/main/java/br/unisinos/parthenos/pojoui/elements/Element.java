package br.unisinos.parthenos.pojoui.elements;

import br.unisinos.parthenos.pojoui.factory.IdentifierFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Element {
  private String id;

  public Element() {
    this.id = IdentifierFactory.createIdentifier(this);
  }
}
