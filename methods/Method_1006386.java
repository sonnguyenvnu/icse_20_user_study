@Override public MappeableContainer inot(final int firstOfRange,final int lastOfRange){
  int startIndex=BufferUtil.unsignedBinarySearch(content,0,cardinality,(short)firstOfRange);
  if (startIndex < 0) {
    startIndex=-startIndex - 1;
  }
  int lastIndex=BufferUtil.unsignedBinarySearch(content,0,cardinality,(short)(lastOfRange - 1));
  if (lastIndex < 0) {
    lastIndex=-lastIndex - 1 - 1;
  }
  final int currentValuesInRange=lastIndex - startIndex + 1;
  final int spanToBeFlipped=lastOfRange - firstOfRange;
  final int newValuesInRange=spanToBeFlipped - currentValuesInRange;
  final ShortBuffer buffer=ShortBuffer.allocate(newValuesInRange);
  final int cardinalityChange=newValuesInRange - currentValuesInRange;
  final int newCardinality=cardinality + cardinalityChange;
  if (cardinalityChange > 0) {
    if (newCardinality > content.limit()) {
      if (newCardinality > DEFAULT_MAX_SIZE) {
        return toBitmapContainer().inot(firstOfRange,lastOfRange);
      }
      final ShortBuffer co=ShortBuffer.allocate(newCardinality);
      content.rewind();
      co.put(content);
      content=co;
    }
    for (int pos=cardinality - 1; pos > lastIndex; --pos) {
      content.put(pos + cardinalityChange,content.get(pos));
    }
    negateRange(buffer,startIndex,lastIndex,firstOfRange,lastOfRange);
  }
 else {
    negateRange(buffer,startIndex,lastIndex,firstOfRange,lastOfRange);
    if (cardinalityChange < 0) {
      for (int i=startIndex + newValuesInRange; i < newCardinality; ++i) {
        content.put(i,content.get(i - cardinalityChange));
      }
    }
  }
  cardinality=newCardinality;
  return this;
}
