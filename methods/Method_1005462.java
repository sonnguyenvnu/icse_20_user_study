public CustomConverterDescription build(BeanContainer beanContainer){
  CustomConverterDescription converterDescription=new CustomConverterDescription();
  converterDescription.setType(MappingUtils.loadClass(type,beanContainer));
  converterDescription.setClassA(MappingUtils.loadClass(classA.build(beanContainer).getName(),beanContainer));
  converterDescription.setClassB(MappingUtils.loadClass(classB.build(beanContainer).getName(),beanContainer));
  return converterDescription;
}
