@SuppressWarnings("unchecked") private <F,T>Castor<F,T> find(Mirror<F> from,Class<T> toType){
  String key=Castor.key(from.getType(),toType);
  if (map.containsKey(key)) {
    return (Castor<F,T>)map.get(key);
  }
  Mirror<T> to=Mirror.me(toType,extractor);
  Class<?>[] fets=from.extractTypes();
  Class<?>[] tets=to.extractTypes();
  for (  Class<?> ft : fets) {
    for (    Class<?> tt : tets) {
      if (map.containsKey(Castor.key(ft,tt))) {
        String key2=Castor.key(ft,tt);
        Castor<F,T> castor=(Castor<F,T>)map.get(key2);
        map.put(key,castor);
        return castor;
      }
    }
  }
  return null;
}
