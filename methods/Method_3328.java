@Override public boolean load(ByteArray byteArray){
  boolean result=super.load(byteArray);
  if (result) {
    initTagSet();
  }
  return result;
}
