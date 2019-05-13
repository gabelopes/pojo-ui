package br.unisinos.parthenos.pojoui.annotation;

import br.unisinos.parthenos.pojoui.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
  String label();
  boolean show();
  int position();
  Class<? extends Component> component();
}
