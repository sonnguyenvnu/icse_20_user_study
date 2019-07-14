@Override public boolean isUnidirected(Direction dir){
  return getDefinition().getValue(TypeDefinitionCategory.UNIDIRECTIONAL,Direction.class) == dir;
}
