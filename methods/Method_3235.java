/** 
 * ????
 * @param from ?
 * @param to   ??
 * @param < T >  ??
 * @return ??
 */
public static <T>T[] shrink(T[] from,T[] to){
  assert to.length <= from.length;
  System.arraycopy(from,0,to,0,to.length);
  return to;
}
