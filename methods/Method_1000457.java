@SuppressWarnings("unchecked") public <T>T attr(Class<T> classOfT,String name){
  Object obj=attr(name);
  if (null == obj)   return null;
  return (T)obj;
}
