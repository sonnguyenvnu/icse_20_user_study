/** 
 * ??????????
 * @param text       ??
 * @param separator  ???
 * @param remainNone ?????????????????????none???
 * @return ???????[???][???][???]??
 */
public static String convertToPinyinFirstCharString(String text,String separator,boolean remainNone){
  List<Pinyin> pinyinList=PinyinDictionary.convertToPinyin(text,remainNone);
  int length=pinyinList.size();
  StringBuilder sb=new StringBuilder(length * (1 + separator.length()));
  int i=1;
  for (  Pinyin pinyin : pinyinList) {
    sb.append(pinyin.getFirstChar());
    if (i < length) {
      sb.append(separator);
    }
    ++i;
  }
  return sb.toString();
}
