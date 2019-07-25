public PropertyInfo find(@NotNull SProperty property){
  SPropertyId id=MetaIdHelper.getProperty(property);
  return myRegistry.get(id.getConceptId()).find(id);
}
