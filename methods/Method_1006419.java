@Override public MappeableContainer xor(final MappeableArrayContainer value2){
  final MappeableBitmapContainer answer=clone();
  if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] bitArray=answer.bitmap.array();
  if (BufferUtil.isBackedBySimpleArray(value2.content)) {
    short[] v2=value2.content.array();
    int c=value2.cardinality;
    for (int k=0; k < c; ++k) {
      short vc=v2[k];
      long mask=1L << vc;
      final int index=toIntUnsigned(vc) >>> 6;
      long ba=bitArray[index];
      answer.cardinality+=1 - 2 * ((ba & mask) >>> vc);
      bitArray[index]=ba ^ mask;
    }
  }
 else {
    int c=value2.cardinality;
    for (int k=0; k < c; ++k) {
      short v2=value2.content.get(k);
      long mask=1L << v2;
      final int index=toIntUnsigned(v2) >>> 6;
      long ba=bitArray[index];
      answer.cardinality+=1 - 2 * ((ba & mask) >>> v2);
      bitArray[index]=ba ^ mask;
    }
  }
  if (answer.cardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return answer.toArrayContainer();
  }
  return answer;
}
