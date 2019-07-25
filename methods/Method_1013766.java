@Override public boolean supportes(Class<?> clazz){
  return Long.class.isAssignableFrom(clazz) || Integer.class.isAssignableFrom(clazz);
}
