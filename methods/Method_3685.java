/** 
 * ?????????
 * @param text
 * @return
 */
public static boolean isAllLetterOrNum(String text){
  for (int i=0; i < text.length(); ++i) {
    char c=text.charAt(i);
    if ((((c < 'a' || c > 'z')) && ((c < 'A' || c > 'Z')) && ((c < '0' || c > '9')))) {
      return false;
    }
  }
  return true;
}
