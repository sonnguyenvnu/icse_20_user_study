public AtomicReference<T> get(String key,T defaultValue){
  AtomicReference<T> tmp=new AtomicReference<>(defaultValue);
  AtomicReference<T> old=configMap.putIfAbsent(key,tmp);
  return old != null ? old : tmp;
}
