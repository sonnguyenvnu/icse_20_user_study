@Override public void accept(MapEntry<K,V> e){
  entryBuffer.add(read(e));
}
