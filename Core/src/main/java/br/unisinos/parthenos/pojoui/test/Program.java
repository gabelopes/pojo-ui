package br.unisinos.parthenos.pojoui.test;

import br.unisinos.parthenos.pojoui.generator.Generator;

import java.io.File;

public class Program {
  public static void main(String[] args) {
    File outputFolder = new File("/Users/sap/Downloads/aaa");
    Generator generator = new Generator(outputFolder, Employee.class.getTypeName(), Person.class.getTypeName());
    generator.generate();
  }
}
