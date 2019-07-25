public FieldMap build(ClassMap classMap,BeanContainer beanContainer,DestBeanCreator destBeanCreator,PropertyDescriptorFactory propertyDescriptorFactory){
  if (a == null || b == null) {
    throw new MappingException("Field name can not be empty");
  }
  DozerField aField=a.convert();
  DozerField bField=b.convert();
  FieldMap fieldMap=resolveFieldMapType(classMap,aField,bField,beanContainer,destBeanCreator,propertyDescriptorFactory);
  fieldMap.setSrcField(aField);
  fieldMap.setDestField(bField);
  if (type != null) {
    fieldMap.setType(MappingDirection.valueOf(type.value()));
  }
  if (relationshipType != null) {
    fieldMap.setRelationshipType(RelationshipType.valueOf(relationshipType.value()));
  }
  fieldMap.setRemoveOrphans(removeOrphans == null ? false : removeOrphans);
  HintContainer aHintContainer=getHintContainer(aHint,beanContainer);
  if (aHintContainer != null) {
    fieldMap.setSrcHintContainer(aHintContainer);
  }
  HintContainer bHintContainer=getHintContainer(bHint,beanContainer);
  if (bHintContainer != null) {
    fieldMap.setDestHintContainer(bHintContainer);
  }
  HintContainer aDeepHintContainer=getHintContainer(aDeepIndexHint,beanContainer);
  if (aDeepHintContainer != null) {
    fieldMap.setSrcDeepIndexHintContainer(aDeepHintContainer);
  }
  HintContainer bDeepHintContainer=getHintContainer(bDeepIndexHint,beanContainer);
  if (bDeepHintContainer != null) {
    fieldMap.setDestDeepIndexHintContainer(bDeepHintContainer);
  }
  if (copyByReference != null) {
    fieldMap.setCopyByReference(copyByReference);
  }
  fieldMap.setMapId(mapId);
  fieldMap.setCustomConverter(customConverter);
  fieldMap.setCustomConverterId(customConverterId);
  fieldMap.setCustomConverterParam(customConverterParam);
  return fieldMap;
}
