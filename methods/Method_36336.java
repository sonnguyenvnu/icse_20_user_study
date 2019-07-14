public List<Entry<K,T>> getMissingRequirements(){
  List<Entry<K,T>> result=new ArrayList<>();
  for (  Map.Entry<K,Entry<K,T>> entry : registry.entrySet()) {
    Entry<K,T> val=entry.getValue();
    if (!val.isRegistered()) {
      result.add(val);
    }
  }
  return result;
}
