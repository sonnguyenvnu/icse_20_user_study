@Override public Iterator<Tuple2<Key,Value>> call(final Element e) throws Exception {
  final List<Tuple2<Key,Value>> tuples=new ArrayList<>(2);
  final Pair<Key,Key> keys=converterBroadcast.value().getKeysFromElement(e);
  final Value value=converterBroadcast.value().getValueFromElement(e);
  tuples.add(new Tuple2<>(keys.getFirst(),value));
  final Key second=keys.getSecond();
  if (null != second) {
    tuples.add(new Tuple2<>(second,value));
  }
  return tuples.listIterator();
}
