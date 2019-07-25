@Override public JsonSchema convert(Object source,TypeDescriptor sourceType,TypeDescriptor targetType){
  final PersistentEntity<?,?> persistentEntity=entities.getRequiredPersistentEntity((Class<?>)source);
  final ResourceMetadata metadata=associations.getMappings().getMetadataFor(persistentEntity.getType());
  Definitions definitions=new Definitions();
  List<AbstractJsonSchemaProperty<?>> propertiesFor=getPropertiesFor(persistentEntity.getType(),metadata,definitions);
  String title=resolver.resolveWithDefault(new ResolvableType(persistentEntity.getType()));
  return new JsonSchema(title,resolver.resolve(metadata.getItemResourceDescription()),propertiesFor,definitions);
}
