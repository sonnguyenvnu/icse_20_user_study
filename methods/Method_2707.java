/** 
 * ??????
 * @param pathArray
 * @return
 */
public static DictionaryMaker combine(String... pathArray){
  DictionaryMaker dictionaryMaker=new DictionaryMaker();
  for (  String path : pathArray) {
    logger.warning("????" + path);
    dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(path));
  }
  return dictionaryMaker;
}
