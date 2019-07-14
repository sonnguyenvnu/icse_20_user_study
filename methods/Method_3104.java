/** 
 * ?????????
 * @param single
 * @return
 */
public static Pinyin convertSingle(String single){
  Pinyin pinyin=map.get(single);
  if (pinyin == null)   return Pinyin.none5;
  return pinyin;
}
