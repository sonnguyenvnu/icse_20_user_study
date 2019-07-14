/** 
 * Returns whether the name occurrence is one of the method calls we are interested in.
 * @param occurrence NameOccurrence
 * @return boolean
 */
private boolean isNotedMethod(NameOccurrence occurrence){
  if (occurrence == null) {
    return false;
  }
  String methodCall=occurrence.getImage();
  String[] methodNames=methodNames();
  for (  String element : methodNames) {
    if (methodCall.indexOf(element) != -1) {
      return true;
    }
  }
  return false;
}
