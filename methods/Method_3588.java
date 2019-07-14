/** 
 * ?????
 * @param document ????
 * @param size     ?????????
 * @return ????
 */
public static List<String> getKeywordList(String document,int size){
  TextRankKeyword textRankKeyword=new TextRankKeyword();
  return textRankKeyword.getKeywords(document,size);
}
