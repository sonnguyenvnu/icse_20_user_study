@Override public boolean isPartitioned(){
  return getDefinition().getValue(TypeDefinitionCategory.PARTITIONED,Boolean.class);
}
