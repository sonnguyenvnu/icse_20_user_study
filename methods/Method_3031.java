/** 
 * ????
 * @param a
 * @param b
 * @return
 */
public long distance(String a,String b){
  SynonymItem itemA=get(a);
  if (itemA == null)   return Long.MAX_VALUE / 3;
  SynonymItem itemB=get(b);
  if (itemB == null)   return Long.MAX_VALUE / 3;
  return itemA.distance(itemB);
}
