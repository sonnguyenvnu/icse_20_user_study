@Override default IList<? extends IMap<K,V>> split(int parts){
  return keys().split(parts).stream().map(ks -> Maps.from(ks,k -> get(k,null))).collect(Lists.collector());
}
