/** 
 * ????????????????????
 * @param pathArray
 * @return
 */
public static DictionaryMaker combineWhenNotInclude(String[] pathArray){
  DictionaryMaker dictionaryMaker=new DictionaryMaker();
  logger.info("???????" + pathArray[0]);
  dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(pathArray[0]));
  for (int i=1; i < pathArray.length; ++i) {
    logger.info("???????" + pathArray[i] + "?????????");
    dictionaryMaker.addAllNotCombine(DictionaryMaker.normalizeFrequency(DictionaryMaker.loadAsItemList(pathArray[i])));
  }
  return dictionaryMaker;
}
