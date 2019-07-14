/** 
 * ???????????
 * @param wordNet ??
 * @param start ????????
 * @param end ?????????
 * @param value ????
 */
private static void combineWords(Vertex[] wordNet,int start,int end,CoreDictionary.Attribute value){
  if (start + 1 == end) {
    wordNet[start].attribute=value;
  }
 else {
    StringBuilder sbTerm=new StringBuilder();
    for (int j=start; j < end; ++j) {
      if (wordNet[j] == null)       continue;
      String realWord=wordNet[j].realWord;
      sbTerm.append(realWord);
      wordNet[j]=null;
    }
    String realWord=sbTerm.toString();
    wordNet[start]=new Vertex(realWord,realWord,value);
  }
}
