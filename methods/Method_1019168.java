/** 
 * check the key, whether the text contains hyper link keys
 * @param text
 * @return
 */
private static boolean contains(String text){
  if (text.length() < 4 || TextUtils.equals(text,SyntaxKey.KEY_HYPER_LINK_EMPTY)) {
    return true;
  }
  char[] array=text.toCharArray();
  final int length=array.length;
  char[] findArray=new char[]{SyntaxKey.KEY_HYPER_LINK_LEFT_CHAR,SyntaxKey.KEY_HYPER_LINK_MIDDLE_LEFT_CHAR,SyntaxKey.KEY_HYPER_LINK_MIDDLE_RIGHT_CHAR,SyntaxKey.KEY_HYPER_LINK_RIGHT_CHAR};
  int findPosition=0;
  for (int i=0; i < length; i++) {
    if (TextHelper.getChar(array,i) != 0 && TextHelper.getChar(array,i) == TextHelper.getChar(findArray,findPosition)) {
      if (findPosition == 1) {
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
