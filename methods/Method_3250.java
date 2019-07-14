private static boolean loadDat(String path){
  ByteArray byteArray=ByteArray.createByteArray(path);
  if (byteArray == null)   return false;
  int size=byteArray.nextInt();
  String[] valueArray=new String[size];
  for (int i=0; i < valueArray.length; ++i) {
    valueArray[i]=byteArray.nextUTF();
  }
  return trie.load(byteArray,valueArray);
}
