@Override public TraversableOnce<Tuple2<Key,Value>> apply(final Element element){
  final ArrayBuffer<Tuple2<Key,Value>> buf=new ArrayBuffer<>();
  Pair<Key,Key> keys=new Pair<>();
  Value value=null;
  try {
    keys=converterBroadcast.value().getKeysFromElement(element);
    value=converterBroadcast.value().getValueFromElement(element);
  }
 catch (  final AccumuloElementConversionException e) {
    LOGGER.error(e.getMessage(),e);
  }
  final Key first=keys.getFirst();
  if (null != first) {
    buf.$plus$eq(new Tuple2<>(first,value));
  }
  final Key second=keys.getSecond();
  if (null != second) {
    buf.$plus$eq(new Tuple2<>(second,value));
  }
  return buf;
}
