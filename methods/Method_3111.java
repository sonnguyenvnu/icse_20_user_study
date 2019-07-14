/** 
 * ??????????
 * @param coreStopWordDictionaryPath ????
 * @param loadCacheIfPossible ??????????????
 */
public static void load(String coreStopWordDictionaryPath,boolean loadCacheIfPossible){
  ByteArray byteArray=loadCacheIfPossible ? ByteArray.createByteArray(coreStopWordDictionaryPath + Predefine.BIN_EXT) : null;
  if (byteArray == null) {
    try {
      dictionary=new StopWordDictionary(HanLP.Config.CoreStopWordDictionaryPath);
      DataOutputStream out=new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(HanLP.Config.CoreStopWordDictionaryPath + Predefine.BIN_EXT)));
      dictionary.save(out);
      out.close();
    }
 catch (    Exception e) {
      logger.severe("???????" + HanLP.Config.CoreStopWordDictionaryPath + "??" + TextUtility.exceptionToString(e));
      throw new RuntimeException("???????" + HanLP.Config.CoreStopWordDictionaryPath + "??");
    }
  }
 else {
    dictionary=new StopWordDictionary();
    dictionary.load(byteArray);
  }
}
