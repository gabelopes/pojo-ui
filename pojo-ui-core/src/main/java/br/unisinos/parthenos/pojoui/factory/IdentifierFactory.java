package br.unisinos.parthenos.pojoui.factory;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentifierFactory {
  private static final String ID_PREFIX = "id";

  private static Map<Object, String> identifiers = new IdentityHashMap<>();

  private static String generateIdentifier(Object object) {
    final int nextNumber = identifiers.size();
    final String nextIdentifier = ID_PREFIX + nextNumber;

    identifiers.put(object, nextIdentifier);

    return nextIdentifier;
  }

  public static String createIdentifier(Object object) {
    final String identifier = identifiers.get(object);

    if (identifier == null) {
      return generateIdentifier(object);
    }

    return identifier;
  }
}
