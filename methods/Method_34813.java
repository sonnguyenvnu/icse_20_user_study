/** 
 * @ExcludeFromJavadoc
 */
static HystrixDynamicProperties createArchaiusDynamicProperties(){
  if (isArchaiusV1Available()) {
    loadCascadedPropertiesFromResources("hystrix-plugins");
    try {
      Class<?> defaultProperties=Class.forName("com.netflix.hystrix.strategy.properties.archaius" + ".HystrixDynamicPropertiesArchaius");
      return (HystrixDynamicProperties)defaultProperties.newInstance();
    }
 catch (    ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
catch (    InstantiationException e) {
      throw new RuntimeException(e);
    }
catch (    IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  return null;
}
