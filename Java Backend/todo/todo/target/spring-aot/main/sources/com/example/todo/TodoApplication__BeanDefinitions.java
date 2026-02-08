package com.example.todo;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TodoApplication}.
 */
@Generated
public class TodoApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'todoApplication'.
   */
  public static BeanDefinition getTodoApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TodoApplication.class);
    beanDefinition.setInstanceSupplier(TodoApplication::new);
    return beanDefinition;
  }
}
