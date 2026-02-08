package com.example.todo.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TodoController}.
 */
@Generated
public class TodoController__BeanDefinitions {
  /**
   * Get the bean definition for 'todoController'.
   */
  public static BeanDefinition getTodoControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TodoController.class);
    InstanceSupplier<TodoController> instanceSupplier = InstanceSupplier.using(TodoController::new);
    instanceSupplier = instanceSupplier.andThen(TodoController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
