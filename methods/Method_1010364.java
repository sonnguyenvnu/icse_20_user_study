public ConceptDescriptorBuilder2 prop(String name,long propertyId){
  addProperty(new BasePropertyDescriptor(MetaIdFactory.propId(myConceptId,propertyId),name,null,null));
  return this;
}
