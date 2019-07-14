/** 
 * ??????
 * @return
 */
public static long[] getLexemeArray(List<CommonSynonymDictionary.SynonymItem> synonymItemList){
  long[] array=new long[synonymItemList.size()];
  int i=0;
  for (  CommonSynonymDictionary.SynonymItem item : synonymItemList) {
    array[i++]=item.entry.id;
  }
  return array;
}
