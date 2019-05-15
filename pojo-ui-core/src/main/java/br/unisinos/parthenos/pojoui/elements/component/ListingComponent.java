package br.unisinos.parthenos.pojoui.elements.component;

import br.unisinos.parthenos.pojoui.elements.Component;
import br.unisinos.parthenos.pojoui.representation.Text;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ListingComponent extends Component {
  private static final String YES = "Yes";
  private static final String NO = "No";

  public ListingComponent(Field field) {
    super(field);
  }

  private List<String> getBooleanListing() {
    return Arrays.asList(YES, NO);
  }

  private List<String> getEnumListing() {
    final Class<?> typeClass = this.getField().getType();
    final Object[] enumConstants;

    if (typeClass.isArray()) {
      enumConstants = typeClass.getComponentType().getEnumConstants();
    } else {
      enumConstants = typeClass.getEnumConstants();
    }

    return Stream.of(enumConstants)
      .map(Object::toString)
      .map(Text::beautifySnakeCase)
      .collect(Collectors.toList());
  }

  protected List<String> getListing() {
    final Class<?> typeClass = this.getField().getType();

    if (Enum.class.isAssignableFrom(typeClass) || Enum[].class.isAssignableFrom(typeClass)) {
      return this.getEnumListing();
    }

    return this.getBooleanListing();
  }
}
