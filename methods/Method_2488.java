/** 
 * ????????????????????????
 * @param text
 * @return
 */
public static String[] extractKeywords(String text){
  List<Term> termList=NotionalTokenizer.segment(text);
  String[] wordArray=new String[termList.size()];
  Iterator<Term> iterator=termList.iterator();
  for (int i=0; i < wordArray.length; i++) {
    wordArray[i]=iterator.next().word;
  }
  return wordArray;
}
