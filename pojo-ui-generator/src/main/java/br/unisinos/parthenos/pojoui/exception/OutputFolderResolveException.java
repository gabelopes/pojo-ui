package br.unisinos.parthenos.pojoui.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OutputFolderResolveException extends RuntimeException {
  private String outputFolder;

  @Override
  public String getMessage() {
    return "Output folder denoted by '" + this.getOutputFolder() + "' could not be found or created";
  }
}
