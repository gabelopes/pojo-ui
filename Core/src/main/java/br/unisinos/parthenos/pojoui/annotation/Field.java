package br.unisinos.parthenos.pojoui.annotation;

import br.unisinos.parthenos.pojoui.elements.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
  String label() default "";
  boolean visible() default true;
  int position() default 0;
  Class<? extends Component> component() default Component.class;
}
