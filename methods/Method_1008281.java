@Override public BytesReference slice(int from,int length){
  if (from < 0 || (from + length) > this.length) {
    throw new IllegalArgumentException("can't slice a buffer with length [" + this.length + "], with slice parameters from [" + from + "], length [" + length + "]");
  }
  return new BytesArray(bytes,offset + from,length);
}
