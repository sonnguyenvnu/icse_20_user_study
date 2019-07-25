@Override public Iterable<SNode> values(){
  List<SNode> res=new ArrayList<>();
  res.addAll(myOtherMap.values());
  res.addAll(myStringBasedIds.values());
  return res;
}
