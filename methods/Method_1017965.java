@Override public <T>T get(Invocation.Builder builder,Class<T> clazz,boolean logError){
  return catchConnectionExceptions(super::get,builder,clazz,logError);
}
