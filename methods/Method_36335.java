public List<T> getPendingObjects(){
  List<T> list=new ArrayList<>();
  List<Entry<K,T>> entries=getPendingEntries();
  for (  Entry<K,T> entry : entries) {
    list.add(entry.object);
  }
  return list;
}
