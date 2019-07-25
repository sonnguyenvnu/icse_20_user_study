@Override public boolean supportes(Class<?> clazz){
  return RedisError.class.isAssignableFrom(clazz);
}
