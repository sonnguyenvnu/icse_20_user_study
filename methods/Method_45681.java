private static <K,V>Map<K,V> mapToMap(Map<K,V> src,Class<? extends Map> dstClazz){
  if (dstClazz.isInterface()) {
    dstClazz=HashMap.class;
  }
  Map des=ClassUtils.newInstance(dstClazz);
  for (  Map.Entry<K,V> entry : src.entrySet()) {
    des.put(deserialize(entry.getKey()),deserialize(entry.getValue()));
  }
  return des;
}
