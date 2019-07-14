/** 
 * Cuts the string from beginning to the first index of provided char.
 */
public static String cutToIndexOf(String string,final char c){
  int i=string.indexOf(c);
  if (i != -1) {
    string=string.substring(0,i);
  }
  return string;
}
