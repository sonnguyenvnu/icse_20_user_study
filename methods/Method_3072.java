static boolean loadDat(){
  ByteArray byteArray=ByteArray.createByteArray(path + Predefine.VALUE_EXT);
  if (byteArray == null)   return false;
  int size=byteArray.nextInt();
  Character[] valueArray=new Character[size];
  for (int i=0; i < valueArray.length; ++i) {
    valueArray[i]=byteArray.nextChar();
  }
  return trie.load(path + Predefine.TRIE_EXT,valueArray);
}
