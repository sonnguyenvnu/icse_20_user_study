@Override public int next(int key,int[] buffer){
  int consumed=0;
  ShortBuffer data=array.content;
  while (consumed < buffer.length && index < array.getCardinality()) {
    buffer[consumed++]=key + toIntUnsigned(data.get(index++));
  }
  return consumed;
}
