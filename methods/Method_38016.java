/** 
 * Cuts the string from the first index of provided substring to the end.
 */
public static String cutFromIndexOf(String string,final String substring){
  int i=string.indexOf(substring);
  if (i != -1) {
    string=string.substring(i);
  }
  return string;
}
