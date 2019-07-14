private void registerBean(final AbstractDataSourceProperties dataSourceProperties,String dataSourceName){
  Map<String,Object> propertyMap=Arrays.stream(dataSourceProperties.getClass().getDeclaredFields()).collect(HashMap::new,(m,v) -> {
    try {
      v.setAccessible(true);
      m.put(v.getName(),v.get(dataSourceProperties));
    }
 catch (    IllegalAccessException e) {
      log.error("[Sentinel Starter] DataSource " + dataSourceName + " field: " + v.getName() + " invoke error");
      throw new RuntimeException("[Sentinel Starter] DataSource " + dataSourceName + " field: " + v.getName() + " invoke error",e);
    }
  }
,HashMap::putAll);
  propertyMap.put(CONVERTER_CLASS_FIELD,dataSourceProperties.getConverterClass());
  propertyMap.put(DATA_TYPE_FIELD,dataSourceProperties.getDataType());
  BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(dataSourceProperties.getFactoryBeanName());
  propertyMap.forEach((propertyName,propertyValue) -> {
    Field field=ReflectionUtils.findField(dataSourceProperties.getClass(),propertyName);
    if (null == field) {
      return;
    }
    if (DATA_TYPE_FIELD.equals(propertyName)) {
      String dataType=StringUtils.trimAllWhitespace(propertyValue.toString());
      if (CUSTOM_DATA_TYPE.equals(dataType)) {
        try {
          if (StringUtils.isEmpty(dataSourceProperties.getConverterClass())) {
            throw new RuntimeException("[Sentinel Starter] DataSource " + dataSourceName + "dataType is custom, please set converter-class " + "property");
          }
          String customConvertBeanName="sentinel-" + dataSourceProperties.getConverterClass();
          if (!this.beanFactory.containsBean(customConvertBeanName)) {
            this.beanFactory.registerBeanDefinition(customConvertBeanName,BeanDefinitionBuilder.genericBeanDefinition(Class.forName(dataSourceProperties.getConverterClass())).getBeanDefinition());
          }
          builder.addPropertyReference("converter",customConvertBeanName);
        }
 catch (        ClassNotFoundException e) {
          log.error("[Sentinel Starter] DataSource " + dataSourceName + " handle " + dataSourceProperties.getClass().getSimpleName() + " error, class name: " + dataSourceProperties.getConverterClass());
          throw new RuntimeException("[Sentinel Starter] DataSource " + dataSourceName + " handle " + dataSourceProperties.getClass().getSimpleName() + " error, class name: " + dataSourceProperties.getConverterClass(),e);
        }
      }
 else {
        if (!dataTypeList.contains(StringUtils.trimAllWhitespace(propertyValue.toString()))) {
          throw new RuntimeException("[Sentinel Starter] DataSource " + dataSourceName + " dataType: " + propertyValue + " is not support now. please using these types: " + dataTypeList.toString());
        }
        builder.addPropertyReference("converter","sentinel-" + propertyValue.toString() + "-" + dataSourceProperties.getRuleType().getName() + "-converter");
      }
    }
 else     if (CONVERTER_CLASS_FIELD.equals(propertyName)) {
      return;
    }
 else {
      Optional.ofNullable(propertyValue).ifPresent(v -> builder.addPropertyValue(propertyName,v));
    }
  }
);
  this.beanFactory.registerBeanDefinition(dataSourceName,builder.getBeanDefinition());
  AbstractDataSource newDataSource=(AbstractDataSource)this.beanFactory.getBean(dataSourceName);
  dataSourceProperties.postRegister(newDataSource);
}
