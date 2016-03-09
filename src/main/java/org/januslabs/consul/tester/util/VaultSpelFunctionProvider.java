package org.januslabs.consul.tester.util;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ReflectionUtils;


public class VaultSpelFunctionProvider implements BeanFactoryPostProcessor {

  private final Class<?>[] functionHolders;

  public VaultSpelFunctionProvider(Class<?>... functionHolders) {
    this.functionHolders = functionHolders;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver() {

      @Override
      protected void customizeEvaluationContext(StandardEvaluationContext evalContext) {

        Arrays.stream(functionHolders).forEach(cls -> ReflectionUtils.doWithMethods(cls, m -> {
          ReflectionUtils.makeAccessible(m);
          evalContext.registerFunction(m.getName(), m);
        } , m -> isPublic(m.getModifiers()) && isStatic(m.getModifiers())));
      }

    });

  }

 
}
