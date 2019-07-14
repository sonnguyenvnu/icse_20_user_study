@Override public SchemaStatus getIndexStatus(PropertyKey key){
  Preconditions.checkArgument(Sets.newHashSet(getFieldKeys()).contains(key),"Provided key is not part of this index: %s",key);
  if (index.isCompositeIndex())   return ((CompositeIndexType)index).getStatus();
 else   return ((MixedIndexType)index).getField(key).getStatus();
}
