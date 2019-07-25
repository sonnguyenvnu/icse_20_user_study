@Override public TypeSubTypeValue apply(final Object value){
  return new TypeSubTypeValue(null,null,null != value ? value.toString() : null);
}
