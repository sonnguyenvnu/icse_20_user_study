@Override public void getrange(String key,long startOffset,long endOffset){
  getrange(SafeEncoder.encode(key),startOffset,endOffset);
}
