@Override public List<Map<K,V>> split(int parts){
  List<Map<K,V>> list=new List<Map<K,V>>().linear();
  MapNodes.split(new Object(),root,(int)Math.ceil(size() / (float)parts)).stream().map(n -> new Map<K,V>(n,hashFn,equalsFn,false)).forEach(list::addLast);
  return list.forked();
}
