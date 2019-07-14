@Override protected String asString(Class value){
  return value == null ? "" : value.getName();
}
