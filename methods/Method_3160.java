/** 
 * ??
 * @param text ??
 * @return ??????
 */
public static List<Term> segment(String text){
  return StandardTokenizer.segment(text.toCharArray());
}
