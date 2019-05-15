package br.unisinos.parthenos.pojoui.representation;

public class Text {
  public static String beautifySnakeCase(String text) {
    return Text.capitalize(text.replace("_", " ").toLowerCase());
  }

  public static String capitalize(String text) {
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }
}
