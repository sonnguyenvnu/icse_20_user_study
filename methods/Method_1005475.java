public ClassMap build(Configuration configuration,BeanContainer beanContainer,DestBeanCreator destBeanCreator,PropertyDescriptorFactory propertyDescriptorFactory){
  separateFieldOrFieldExclude();
  ClassMap current=new ClassMap(configuration);
  current.setSrcClass(classA.build(beanContainer));
  current.setDestClass(classB.build(beanContainer));
  current.setType(MappingDirection.valueOf(type == null ? Type.BI_DIRECTIONAL.value() : type.value()));
  current.setDateFormat(dateFormat);
  current.setBeanFactory(beanFactory);
  if (mapNull != null) {
    current.setMapNull(mapNull);
  }
  if (mapEmptyString != null) {
    current.setMapEmptyString(mapEmptyString);
  }
  current.setWildcard(wildcard);
  current.setWildcardCaseInsensitive(wildcardCaseInsensitive);
  current.setStopOnErrors(stopOnErrors);
  current.setTrimStrings(trimStrings);
  current.setMapId(mapId);
  current.setRelationshipType(RelationshipType.valueOf(relationshipType == null ? Relationship.CUMULATIVE.value() : relationshipType.value()));
  current.setFieldMaps(convertFieldMap(current,beanContainer,destBeanCreator,propertyDescriptorFactory));
  return current;
}
