public FieldMap build(ClassMap classMap,BeanContainer beanContainer,DestBeanCreator destBeanCreator,PropertyDescriptorFactory propertyDescriptorFactory){
  DozerField aField=a.convert();
  DozerField bField=b.convert();
  FieldMap fieldMap=new ExcludeFieldMap(classMap,beanContainer,destBeanCreator,propertyDescriptorFactory);
  fieldMap.setSrcField(aField);
  fieldMap.setDestField(bField);
  if (type != null) {
    fieldMap.setType(MappingDirection.valueOf(type.value()));
  }
  return fieldMap;
}
