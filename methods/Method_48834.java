<V>JanusGraphVertexProperty<V> constructProperty(String key,V value){
  assert key != null && value != null;
  return new FulgoraVertexProperty<>(this,vertex,key,value);
}
