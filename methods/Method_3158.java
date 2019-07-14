/** 
 * ?????
 * @param text       ??
 * @param separator  ???
 * @param remainNone ???????????????????????true?none???false???????
 * @return ???????[??][???][??]??
 */
public static String convertToPinyinString(String text,String separator,boolean remainNone){
  List<Pinyin> pinyinList=PinyinDictionary.convertToPinyin(text,true);
  int length=pinyinList.size();
  StringBuilder sb=new StringBuilder(length * (5 + separator.length()));
  int i=1;
  for (  Pinyin pinyin : pinyinList) {
    if (pinyin == Pinyin.none5 && !remainNone) {
      sb.append(text.charAt(i - 1));
    }
 else     sb.append(pinyin.getPinyinWithoutTone());
    if (i < length) {
      sb.append(separator);
    }
    ++i;
  }
  return sb.toString();
}
