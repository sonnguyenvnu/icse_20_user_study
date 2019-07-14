@Override public IndexType asIndexType(){
  Preconditions.checkArgument(getDefinition().containsKey(TypeDefinitionCategory.INTERNAL_INDEX),"Schema vertex is not a type vertex: [%s,%s]",longId(),name());
  if (getDefinition().<Boolean>getValue(TypeDefinitionCategory.INTERNAL_INDEX)) {
    return new CompositeIndexTypeWrapper(this);
  }
 else {
    return new MixedIndexTypeWrapper(this);
  }
}
