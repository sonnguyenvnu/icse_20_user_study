@Override final protected EnumItem<E>[] loadValueArray(ByteArray byteArray){
  if (byteArray == null) {
    return null;
  }
  E[] nrArray=values();
  int size=byteArray.nextInt();
  EnumItem<E>[] valueArray=new EnumItem[size];
  for (int i=0; i < size; ++i) {
    int currentSize=byteArray.nextInt();
    EnumItem<E> item=newItem();
    for (int j=0; j < currentSize; ++j) {
      E nr=nrArray[byteArray.nextInt()];
      int frequency=byteArray.nextInt();
      item.labelMap.put(nr,frequency);
    }
    valueArray[i]=item;
  }
  return valueArray;
}
