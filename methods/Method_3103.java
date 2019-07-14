/** 
 * ??List<Pinyin> pinyinList?List<String>????String????????
 * @param pinyinList
 * @return
 */
public static List<String> convertPinyinList2TonePinyinList(List<Pinyin> pinyinList){
  List<String> tonePinyinList=new ArrayList<String>(pinyinList.size());
  for (  Pinyin pinyin : pinyinList) {
    tonePinyinList.add(pinyin.getPinyinWithToneMark());
  }
  return tonePinyinList;
}
