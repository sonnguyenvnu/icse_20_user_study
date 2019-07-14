/** 
 * Compares constructor signatures: names and parameters.
 */
public static boolean compareSignatures(final Constructor first,final Constructor second){
  if (!first.getName().equals(second.getName())) {
    return false;
  }
  return compareParameters(first.getParameterTypes(),second.getParameterTypes());
}
