@Override public TypeValue apply(final Object value){
  return new TypeValue(null,null != value ? value.toString() : null);
}
