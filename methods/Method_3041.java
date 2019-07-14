/** 
 * ????????
 * @param path
 * @return
 */
static boolean loadDat(String path){
  try {
    ByteArray byteArray=ByteArray.createByteArray(path + Predefine.BIN_EXT);
    if (byteArray == null)     return false;
    int size=byteArray.nextInt();
    CoreDictionary.Attribute[] attributes=new CoreDictionary.Attribute[size];
    final Nature[] natureIndexArray=Nature.values();
    for (int i=0; i < size; ++i) {
      int currentTotalFrequency=byteArray.nextInt();
      int length=byteArray.nextInt();
      attributes[i]=new CoreDictionary.Attribute(length);
      attributes[i].totalFrequency=currentTotalFrequency;
      for (int j=0; j < length; ++j) {
        attributes[i].nature[j]=natureIndexArray[byteArray.nextInt()];
        attributes[i].frequency[j]=byteArray.nextInt();
      }
    }
    if (!trie.load(byteArray,attributes) || byteArray.hasMore())     return false;
  }
 catch (  Exception e) {
    logger.warning("??????????" + e);
    return false;
  }
  return true;
}
