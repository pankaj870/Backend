package com.example.todo.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TodoController}.
 */
@Generated
public class TodoController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TodoController apply(RegisteredBean registeredBean, TodoController instance) {
    AutowiredFieldValueResolver.forRequiredField("todoRepository").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
