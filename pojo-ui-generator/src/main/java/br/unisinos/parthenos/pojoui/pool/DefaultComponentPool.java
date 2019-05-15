package br.unisinos.parthenos.pojoui.pool;

import br.unisinos.parthenos.pojoui.elements.Component;
import br.unisinos.parthenos.pojoui.elements.component.CheckBoxes;
import br.unisinos.parthenos.pojoui.elements.component.ComboBox;
import br.unisinos.parthenos.pojoui.elements.component.Input;
import br.unisinos.parthenos.pojoui.elements.component.RadioButtons;

import java.util.HashMap;
import java.util.Map;

public class DefaultComponentPool {
  private static final Map<Class<?>, Class<? extends Component>> defaultComponents;

  static {
    defaultComponents = new HashMap<>();

    defaultComponents.put(String.class, Input.class);
    defaultComponents.put(Character.class, Input.class);
    defaultComponents.put(char.class, Input.class);

    defaultComponents.put(Number.class, Input.class);
    defaultComponents.put(short.class, Input.class);
    defaultComponents.put(int.class, Input.class);
    defaultComponents.put(long.class, Input.class);
    defaultComponents.put(float.class, Input.class);
    defaultComponents.put(double.class, Input.class);
    defaultComponents.put(byte.class, Input.class);

    defaultComponents.put(boolean.class, RadioButtons.class);
    defaultComponents.put(Boolean.class, RadioButtons.class);

    defaultComponents.put(Enum.class, ComboBox.class);

    defaultComponents.put(Enum[].class, CheckBoxes.class);
  }

  private static Class<? extends Component> getDefaultComponent(Class<?> typeClass) {
    for (Map.Entry<Class<?>, Class<? extends Component>> entry : defaultComponents.entrySet()) {
      if (entry.getKey().isAssignableFrom(typeClass)) {
        return entry.getValue();
      }
    }

    return null;
  }

  public static Class<? extends Component> forType(Class<?> typeClass) {
    return getDefaultComponent(typeClass);
  }
}
