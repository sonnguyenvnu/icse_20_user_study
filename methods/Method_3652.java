/** 
 * ???????
 * @param text ??
 * @return ????
 */
public static List<List<Term>> seg2sentence(String text){
  List<List<Term>> resultList=new LinkedList<List<Term>>();
{
    for (    String sentence : SentencesUtil.toSentenceList(text)) {
      resultList.add(segment(sentence));
    }
  }
  return resultList;
}
