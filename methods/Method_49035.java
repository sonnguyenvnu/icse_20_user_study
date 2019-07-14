public Iterable<PropertyKeyDefinition> getPropertyKeys(){
  return Iterables.filter(relationTypes.values(),PropertyKeyDefinition.class);
}
