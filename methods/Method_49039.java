@Override public PropertyKeyDefinition getPropertyKey(String name){
  RelationTypeDefinition def=getRelationType(name);
  if (def != null && !(def instanceof PropertyKeyDefinition))   throw new IllegalArgumentException("Not a property key but edge label: " + name);
  return (PropertyKeyDefinition)def;
}
