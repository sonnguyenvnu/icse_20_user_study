public Class findClass(String keyString){
  for (int i=0; i < buckets.length; i++) {
    Entry bucket=buckets[i];
    if (bucket == null) {
      continue;
    }
    for (Entry<K,V> entry=bucket; entry != null; entry=entry.next) {
      Object key=bucket.key;
      if (key instanceof Class) {
        Class clazz=((Class)key);
        String className=clazz.getName();
        if (className.equals(keyString)) {
          return clazz;
        }
      }
    }
  }
  return null;
}
