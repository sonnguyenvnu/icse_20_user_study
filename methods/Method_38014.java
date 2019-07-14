/** 
 * Cuts the string from beginning to the first index of provided substring.
 */
public static String cutToIndexOf(String string,final String substring){
  int i=string.indexOf(substring);
  if (i != -1) {
    string=string.substring(0,i);
  }
  return string;
}
