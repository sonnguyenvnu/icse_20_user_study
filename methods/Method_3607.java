/** 
 * ????
 * @param sentence
 * @return
 */
public List<String> segment(String sentence){
  return segment(sentence,CharTable.convert(sentence));
}
