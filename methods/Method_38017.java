/** 
 * Cuts the string from the first index of provided char to the end.
 */
public static String cutFromIndexOf(String string,final char c){
  int i=string.indexOf(c);
  if (i != -1) {
    string=string.substring(i);
  }
  return string;
}
