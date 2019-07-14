/** 
 * ????????
 * @param word
 * @return
 */
public static boolean isAllChineseNum(String word){
  String chineseNum="???????????????????????????????·???";
  String prefix="????";
  String surfix="??????";
  boolean round=false;
  if (word == null)   return false;
  char[] temp=word.toCharArray();
  for (int i=0; i < temp.length; i++) {
    if (word.startsWith("??",i)) {
      i+=1;
      continue;
    }
    char tchar=temp[i];
    if (i == 0 && prefix.indexOf(tchar) != -1) {
      round=true;
    }
 else     if (i == temp.length - 1 && !round && surfix.indexOf(tchar) != -1) {
      round=true;
    }
 else     if (chineseNum.indexOf(tchar) == -1)     return false;
  }
  return true;
}
