static boolean loadDat(String path){
  ByteArray byteArray=ByteArray.createByteArray(path + Predefine.BIN_EXT);
  if (byteArray == null)   return false;
  int size=byteArray.nextInt();
  Pinyin[][] valueArray=new Pinyin[size][];
  for (int i=0; i < valueArray.length; ++i) {
    int length=byteArray.nextInt();
    valueArray[i]=new Pinyin[length];
    for (int j=0; j < length; ++j) {
      valueArray[i][j]=pinyins[byteArray.nextInt()];
    }
  }
  if (!trie.load(byteArray,valueArray))   return false;
  return true;
}
