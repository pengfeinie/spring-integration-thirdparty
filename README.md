

## 1. Configuration Metadata

Configuration metadata represents how you, as an application developer, tell the Spring Container to instantiate, configure, and assemble the objects in your application.

This configuration metadata is nothing but bean definitions. `BeanDefinitions` are provided in the form of an XML file, annotations, or a Java config class.

Forms of metadata with the Spring container are: **XML-based configuration, annotation-based configuration, and Java-based configuration.**

The following diagram shows a high-level view of how Spring works. Your application classes are combined with configuration metadata so that, after the `ApplicationContext` is created and initialized, you have a fully configured and executable system or application.

![image-20210915125903022](https://docs.spring.io/spring-framework/docs/5.2.x/spring-framework-reference/images/container-magic.png)

As the preceding diagram shows, the Spring IoC container consumes a form of configuration metadata. This configuration metadata represents how you, as an application developer, tell the Spring container to instantiate, configure, and assemble the objects in your application.

![https://pengfeinie.github.io/images/jg555.png](https://pengfeinie.github.io/images/jg555.png)

### 1.1 XML-based configuration

XML-based configuration metadata configures these beans as `<bean/>` elements inside a top-level `<beans/>` element.

![https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/images/eclipse-setup-2.png](https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/images/eclipse-setup-2.png)

### 1.2 Annotation-based configuration

Spring 2.5 introduced support for annotation-based configuration metadata. An alternative to XML setup is provided by annotation-based configuration, which relies on the bytecode metadata for wiring up components instead of angle-bracket declarations. Instead of using XML to describe a bean wiring, the developer moves the configuration into the component class itself by using annotations on the relevant class, method, or field declaration.

![https://pengfeinie.github.io/images/Slide1.JPG](https://pengfeinie.github.io/images/Slide1.JPG)

### 1.3 Java-based configuration

Java configuration typically uses `@Bean`-annotated methods within a `@Configuration` class. The central artifacts in Spring’s new Java-configuration support are `@Configuration`-annotated classes and `@Bean`-annotated methods.

The `@Bean` annotation is used to indicate that a method instantiates, configures, and initializes a new object to be managed by the Spring IoC container. For those familiar with Spring’s `<beans/>` XML configuration, the `@Bean` annotation plays the same role as the `<bean/>` element. You can use `@Bean`-annotated methods with any Spring `@Component`. However, they are most often used with `@Configuration` beans.

Annotating a class with `@Configuration` indicates that its primary purpose is as a source of bean definitions. Furthermore, `@Configuration` classes let inter-bean dependencies be defined by calling other `@Bean` methods in the same class. 

![image-20210915125617325](https://pengfeinie.github.io/images/image-20210915125617325.png)

**@Configuration Classes are Subclassed by CGLIB**

All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.

 CGLIB proxying is the means by which invoking methods or fields within @Bean methods in @Configuration classes creates bean metadata references to collaborating objects; such methods are not invoked with normal Java semantics but rather go through the container in order to provide the usual lifecycle management and proxying of Spring beans even when referring to other beans via programmatic calls to @Bean methods.

That's why all methods will return the same instance at multiple calls (if they are singleton scoped which is the default scope).

There has to be `@Configuration` annotation, otherwise this runtime manipulation won't be done.

## 2. Container extension points

### 2.1 Customizing beans using `BeanPostProcessor`

[Spring 1.1.x](https://docs.spring.io/spring-framework/docs/1.1.x/reference/beans.html#beans-factory-customizing) The `BeanPostProcessor` interface defines callback methods that you can implement to provide your own (or override the container’s default) instantiation logic, dependency resolution logic, and so forth. If you want to implement some custom logic after the Spring container finishes instantiating, configuring, and initializing a bean, you can plug in one or more custom `BeanPostProcessor` implementations.

You can configure multiple `BeanPostProcessor` instances, and you can control the order in which these `BeanPostProcessor` instances run by setting the `order` property. You can set this property only if the `BeanPostProcessor` implements the `Ordered` interface. If you write your own `BeanPostProcessor`, you should consider implementing the `Ordered` interface, too. For further details, see the javadoc of the [`BeanPostProcessor`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html) and [`Ordered`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/core/Ordered.html) interfaces. 

![https://pengfeinie.github.io/images/beanpostprocessor-overview.jpg](https://pengfeinie.github.io/images/beanpostprocessor-overview.jpg)

#### 2.1.1 How to create BeanPostProcessor

To create a bean post processor in spring: implement the `BeanPostProcessor` interface and implement the callback methods.

```
@Component
public class GreetingBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.printf("BeforeInitialization in %s for %s%n", getClass().getSimpleName(),beanName);
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.printf("AfterInitialization in %s for %s%n", getClass().getSimpleName(),beanName);
        }
        return bean;
    }
}
```

#### 2.1.2 How to register BeanPostProcessor

An *ApplicationContext* automatically detects any beans that are defined in the configuration metadata which implement the *BeanPostProcessor* interface. It registers these beans as post-processors so that they can be called later upon bean creation.

Then Spring will pass each bean instance to these two methods before and after calling the initialization callback method where you can process the bean instance the way you like.

### 2.2 Customizing configuration metadata with `BeanFactoryPostProcessor`

[Spring 1.1.x](https://docs.spring.io/spring-framework/docs/1.1.x/reference/beans.html#beans-factory-customizing)  The next extension point that we look at is the `org.springframework.beans.factory.config.BeanFactoryPostProcessor`. The semantics of this interface are similar to those of the `BeanPostProcessor`, with one major difference: `BeanFactoryPostProcessor` operates on the bean configuration metadata. That is, the Spring IoC container lets a `BeanFactoryPostProcessor` read the configuration metadata and potentially change it *before* the container instantiates any beans other than `BeanFactoryPostProcessor` instances.

You can configure multiple `BeanFactoryPostProcessor` instances, and you can control the order in which these `BeanFactoryPostProcessor` instances run by setting the `order` property. However, you can only set this property if the `BeanFactoryPostProcessor` implements the `Ordered` interface. If you write your own `BeanFactoryPostProcessor`, you should consider implementing the `Ordered` interface, too. See the javadoc of the [`BeanFactoryPostProcessor`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html) and [`Ordered`](https://docs.spring.io/spring-framework/docs/5.2.16.RELEASE/javadoc-api/org/springframework/core/Ordered.html) interfaces for more details.

### 2.3 Customizing instantiation logic using `FactoryBean`

[Spring 1.1.x](https://docs.spring.io/spring-framework/docs/1.1.x/reference/beans.html#beans-factory-customizing) 

### 2.4 @Configuration+@Bean

### 2.5 @Configuration+@Import+ImportSelector

### 2.6 @Configuration+@Import+ImportBeanDefinitionRegistrar

