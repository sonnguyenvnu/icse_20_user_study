public List<T> getResolvedObjects(){
  List<T> list=new ArrayList<>();
  List<Entry<K,T>> entries=getResolvedEntries();
  for (  Entry<K,T> entry : entries) {
    list.add(entry.object);
  }
  return list;
}
