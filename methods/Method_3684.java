/** 
 * ??????
 * @param text
 * @return
 */
public static boolean isAllLetter(String text){
  for (int i=0; i < text.length(); ++i) {
    char c=text.charAt(i);
    if ((((c < 'a' || c > 'z')) && ((c < 'A' || c > 'Z')))) {
      return false;
    }
  }
  return true;
}
