package br.unisinos.parthenos.pojoui.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Accept {
  Class<?>[] value();
}
