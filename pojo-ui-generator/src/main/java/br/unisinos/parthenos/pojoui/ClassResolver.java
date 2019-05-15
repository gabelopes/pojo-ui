package br.unisinos.parthenos.pojoui;

import br.unisinos.parthenos.pojoui.annotation.Panel;
import br.unisinos.parthenos.pojoui.exception.ClassLoadException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public class ClassResolver {
  private ClassLoader classLoader;
  private Set<String> classNames;

  private Class<?> loadClass(String name) {
    try {
      return this.getClassLoader().loadClass(name);
    } catch (ClassNotFoundException ignored) {
      throw new ClassLoadException(name);
    }
  }

  private Set<Class<?>> resolveWithReflections() {
    final Reflections reflections = new Reflections(this.getClassLoader());
    return reflections.getTypesAnnotatedWith(Panel.class);
  }

  public Set<Class<?>> resolve() {
    final Set<String> classNames = this.getClassNames();

    if (classNames == null) {
      return this.resolveWithReflections();
    }

    return classNames
      .stream()
      .map(this::loadClass)
      .collect(Collectors.toSet());
  }
}
