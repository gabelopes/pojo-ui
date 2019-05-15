package br.unisinos.parthenos.pojoui.elements;

import br.unisinos.parthenos.pojoui.factory.IdentifierFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Element {
  default String getId() {
    return IdentifierFactory.createIdentifier(this);
  }
}
