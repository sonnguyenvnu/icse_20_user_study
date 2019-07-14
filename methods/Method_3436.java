@Override public boolean load(ByteArray byteArray){
  idStringMap.clear();
  stringIdMap.clear();
  int size=byteArray.nextInt();
  for (int i=0; i < size; i++) {
    String tag=byteArray.nextUTF();
    idStringMap.add(tag);
    stringIdMap.put(tag,i);
  }
  lock();
  return true;
}
