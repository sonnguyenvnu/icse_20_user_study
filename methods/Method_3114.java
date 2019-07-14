/** 
 * ??????
 * @param from
 * @param to
 * @return
 */
public int getFrequency(E from,E to){
  return matrix[from.ordinal()][to.ordinal()];
}
