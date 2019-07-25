@Override public BytesReference slice(int from,int length){
  if (from < 0 || (from + length) > length()) {
    throw new IllegalArgumentException("can't slice a buffer with length [" + length() + "], with slice parameters from [" + from + "], length [" + length + "]");
  }
  return new PagedBytesReference(bigarrays,byteArray,offset + from,length);
}
