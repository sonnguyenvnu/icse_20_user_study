@Override public Object apply(final EntityId e){
  if (null == e) {
    return null;
  }
  return e.getVertex();
}
