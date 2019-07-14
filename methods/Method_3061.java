/** 
 * ????????
 * @param path ?????
 * @param customDicPath ??????
 * @return
 */
public static boolean loadDat(String path,String customDicPath[],DoubleArrayTrie<CoreDictionary.Attribute> dat){
  try {
    if (isDicNeedUpdate(path,customDicPath)) {
      return false;
    }
    ByteArray byteArray=ByteArray.createByteArray(path + Predefine.BIN_EXT);
    if (byteArray == null)     return false;
    int size=byteArray.nextInt();
    if (size < 0) {
      while (++size <= 0) {
        Nature.create(byteArray.nextString());
      }
      size=byteArray.nextInt();
    }
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
    if (!dat.load(byteArray,attributes))     return false;
  }
 catch (  Exception e) {
    logger.warning("??????????" + TextUtility.exceptionToString(e));
    return false;
  }
  return true;
}
