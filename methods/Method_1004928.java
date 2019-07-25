@Override public ElementId apply(final Object obj){
  if (null == obj) {
    return null;
  }
  return obj instanceof ElementId ? (ElementId)obj : new EntitySeed(obj);
}
