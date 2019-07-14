protected final boolean hasSingleType(){
  return types.length == 1 && schemaInspector.getRelationType(types[0]) != null;
}
