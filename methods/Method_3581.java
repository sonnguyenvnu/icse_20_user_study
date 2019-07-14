/** 
 * ?????
 * @return
 */
public int size(){
  int length=0;
  for (  Pinyin pinyin : pinyinArray) {
    if (pinyin != Pinyin.none5)     ++length;
  }
  return length;
}
