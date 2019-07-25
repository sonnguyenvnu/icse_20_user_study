private void set(String key,T value){
  AtomicReference<T> old=configMap.putIfAbsent(key,new AtomicReference<T>(value));
  if (old != null) {
    old.set(value);
  }
}
