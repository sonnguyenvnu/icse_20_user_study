public void contents(BiConsumer<K,V> consumer){
  for (  Map.Entry<K,V> entry : _map.entrySet()) {
    consumer.accept(entry.getKey(),entry.getValue());
  }
}
