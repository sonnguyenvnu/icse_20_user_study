/** 
 * ???????
 * @param x ????
 * @return sign(wx)
 */
public int decode(Collection<Integer> x){
  float y=0;
  for (  Integer f : x)   y+=parameter[f];
  return y < 0 ? -1 : 1;
}
