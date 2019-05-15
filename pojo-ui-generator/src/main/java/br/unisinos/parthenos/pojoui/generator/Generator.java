package br.unisinos.parthenos.pojoui.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public abstract class Generator {
  private File outputFolder;
  private Set<Class<?>> classes;

  public abstract void generate();
}
