/** 
 * ??????
 * @param wordArray
 * @param posArray
 * @return
 */
public String[] namedEntityRecognize(String[] wordArray,String[] posArray){
  if (neRecognizer == null) {
    throw new IllegalStateException("???????????");
  }
  return recognize(wordArray,posArray);
}
