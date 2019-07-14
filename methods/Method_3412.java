/** 
 * ????
 * @param text
 * @param output
 */
public void segment(String text,List<String> output){
  String normalized=CharTable.convert(text);
  segment(text,normalized,output);
}
