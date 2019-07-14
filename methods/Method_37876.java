/** 
 * Compares method signatures: names and parameters.
 */
public static boolean compareSignatures(final Method first,final Method second){
  if (!first.getName().equals(second.getName())) {
    return false;
  }
  return compareParameters(first.getParameterTypes(),second.getParameterTypes());
}
