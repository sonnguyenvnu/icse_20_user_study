/** 
 * ???????????
 * @param wordNet ??
 * @param start   ????????
 * @param end     ?????????
 * @param value   ????
 */
private static void combineWords(String[] wordNet,int start,int end,CoreDictionary.Attribute[] attributeArray,CoreDictionary.Attribute value){
  if (start + 1 != end) {
    StringBuilder sbTerm=new StringBuilder();
    for (int j=start; j < end; ++j) {
      if (wordNet[j] == null)       continue;
      sbTerm.append(wordNet[j]);
      wordNet[j]=null;
    }
    wordNet[start]=sbTerm.toString();
  }
  attributeArray[start]=value;
}
