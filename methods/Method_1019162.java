/** 
 * check the key, whether the text contains
 * @param text
 * @return
 */
private static boolean contains(String text){
  if (text.length() < 3 || TextUtils.equals(text,"[^]")) {
    return true;
  }
  char[] array=text.toCharArray();
  final int length=array.length;
  char[] findArray=new char[]{'[','^',']'};
  int findPosition=0;
  for (int i=0; i < length; i++) {
    if (TextHelper.getChar(array,i) != 0 && TextHelper.getChar(array,i) == TextHelper.getChar(findArray,findPosition)) {
      if (findPosition == 0) {
        if (TextHelper.getChar(array,++i) == 0 || TextHelper.getChar(findArray,++findPosition) == 0) {
          return false;
        }
        if (TextHelper.getChar(array,++i) != TextHelper.getChar(findArray,++findPosition)) {
          findPosition--;
        }
 else {
          findPosition++;
        }
      }
 else {
        findPosition++;
      }
      if (findPosition == findArray.length - 1) {
        return true;
      }
    }
  }
  return false;
}
