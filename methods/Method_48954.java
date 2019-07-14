protected final RelationType getSingleType(){
  Preconditions.checkArgument(hasSingleType());
  return schemaInspector.getRelationType(types[0]);
}
