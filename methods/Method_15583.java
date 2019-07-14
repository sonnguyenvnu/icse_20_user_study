/** 
 * @param < K >
 * @param pair
 * @return
 */
public static <K,V>boolean isCorrect(Entry<K,V> pair){
  return pair != null && StringUtil.isNotEmpty(pair.getValue(),true);
}
