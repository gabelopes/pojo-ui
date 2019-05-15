package br.unisinos.parthenos.pojoui;

import picocli.CommandLine;

public class Program {
  public static void main(String[] args) {
    final CLI cli = CommandLine.populateCommand(new CLI(), args);
    final Orchestrator orchestrator = new Orchestrator(cli);

    orchestrator.execute();
  }
}
