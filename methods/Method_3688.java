/** 
 * ??????????????????
 * @param charSet
 * @param word
 * @return
 */
public static int getCharCount(String charSet,String word){
  int nCount=0;
  if (word != null) {
    String temp=word + " ";
    for (int i=0; i < word.length(); i++) {
      String s=temp.substring(i,i + 1);
      if (charSet.indexOf(s) != -1)       nCount++;
    }
  }
  return nCount;
}
