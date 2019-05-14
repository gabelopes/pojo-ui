package br.unisinos.parthenos.pojoui.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Panel {
  String label() default "";
  boolean visible() default true;
  int position() default 0;
}
