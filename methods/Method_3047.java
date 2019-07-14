/** 
 * ?????????????0??????1??????
 * @param A
 * @param B
 * @return
 */
public static double similarity(String A,String B){
  long distance=distance(A,B);
  if (distance > dictionary.getMaxSynonymItemIdDistance())   return 0.0;
  return (dictionary.getMaxSynonymItemIdDistance() - distance) / (double)dictionary.getMaxSynonymItemIdDistance();
}
