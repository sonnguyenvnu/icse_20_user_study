@Override public MapMarshaller<K,V> copy(){
  return new MapMarshaller<>(copyIfNeeded(keyReader),copyIfNeeded(keyWriter),copyIfNeeded(valueReader),copyIfNeeded(valueWriter));
}
