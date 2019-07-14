/** 
 * ?????????????
 * @param A
 * @param B
 * @return
 */
public static long distance(String A,String B){
  CommonSynonymDictionary.SynonymItem itemA=get(A);
  CommonSynonymDictionary.SynonymItem itemB=get(B);
  if (itemA == null || itemB == null)   return Long.MAX_VALUE;
  return distance(itemA,itemB);
}
