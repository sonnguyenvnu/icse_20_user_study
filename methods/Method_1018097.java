@Override public Boolean accepts(Object value){
  return (value instanceof byte[]) || (value instanceof String);
}
