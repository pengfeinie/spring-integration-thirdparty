

## 1. Configuration Metadata

![https://pengfeinie.github.io/images/jg555.png](https://pengfeinie.github.io/images/jg555.png)

### 1.1 XML-based configuration

XML-based configuration metadata configures these beans as `<bean/>` elements inside a top-level `<beans/>` element.

### 1.2 Annotation-based configuration

Spring 2.5 introduced support for annotation-based configuration metadata.

### 1.3 Java-based configuration

Java configuration typically uses `@Bean`-annotated methods within a `@Configuration` class.

## 2. Container extension points

https://docs.spring.io/spring-framework/docs/2.0.x/reference/beans.html#beans-factory-extension

### 2.1 Customizing beans using `BeanPostProcessors`

The `BeanPostProcessor` interface defines callback methods that you can implement to provide your own (or override the container’s default) instantiation logic, dependency resolution logic, and so forth. If you want to implement some custom logic after the Spring container finishes instantiating, configuring, and initializing a bean, you can plug in one or more custom `BeanPostProcessor` implementations.

You can configure multiple `BeanPostProcessor` instances, and you can control the order in which these `BeanPostProcessor` instances run by setting the `order` property. You can set this property only if the `BeanPostProcessor` implements the `Ordered` interface. If you write your own `BeanPostProcessor`, you should consider implementing the `Ordered` interface, too. For further details, see the javadoc of the [`BeanPostProcessor`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html) and [`Ordered`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/core/Ordered.html) interfaces. 

### 2.2 Customizing configuration metadata with `BeanFactoryPostProcessors`

The next extension point that we look at is the `org.springframework.beans.factory.config.BeanFactoryPostProcessor`. The semantics of this interface are similar to those of the `BeanPostProcessor`, with one major difference: `BeanFactoryPostProcessor` operates on the bean configuration metadata. That is, the Spring IoC container lets a `BeanFactoryPostProcessor` read the configuration metadata and potentially change it *before* the container instantiates any beans other than `BeanFactoryPostProcessor` instances.

You can configure multiple `BeanFactoryPostProcessor` instances, and you can control the order in which these `BeanFactoryPostProcessor` instances run by setting the `order` property. However, you can only set this property if the `BeanFactoryPostProcessor` implements the `Ordered` interface. If you write your own `BeanFactoryPostProcessor`, you should consider implementing the `Ordered` interface, too. See the javadoc of the [`BeanFactoryPostProcessor`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html) and [`Ordered`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/core/Ordered.html) interfaces for more details.

### 2.3 Customizing instantiation logic using `FactoryBean`

### 2.4 @Bean-based

### 2.5 ImportSelector

### 2.6 ImportBeanDefinitionRegistrar

