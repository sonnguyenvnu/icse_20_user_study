/** 
 * Removes last CamelWord
 */
public static String stripLastCamelWord(String name){
  int ndx=name.length() - 1;
  while (ndx >= 0) {
    if (CharUtil.isUppercaseAlpha(name.charAt(ndx))) {
      break;
    }
    ndx--;
  }
  if (ndx >= 0) {
    name=name.substring(0,ndx);
  }
  return name;
}
