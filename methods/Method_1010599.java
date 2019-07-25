@Override public Iterable<SNode> values(){
  ArrayList<SNode> rv=new ArrayList<>(myRegularMap.size() + myStringBasedIdMap.size() + myOtherMap.size());
  rv.addAll((Collection)Arrays.asList(myRegularMap.getValues()));
  rv.addAll(myStringBasedIdMap.values());
  rv.addAll(myOtherMap.values());
  return rv;
}
