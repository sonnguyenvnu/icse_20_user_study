/** 
 * Compares classes, usually method or ctor parameters.
 */
public static boolean compareParameters(final Class[] first,final Class[] second){
  if (first.length != second.length) {
    return false;
  }
  for (int i=0; i < first.length; i++) {
    if (first[i] != second[i]) {
      return false;
    }
  }
  return true;
}
