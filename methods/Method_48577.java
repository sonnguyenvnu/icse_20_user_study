@Override public Parameter[] getParametersFor(PropertyKey key){
  if (index.isCompositeIndex())   return new Parameter[0];
  return ((MixedIndexType)index).getField(key).getParameters();
}
