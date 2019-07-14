/** 
 * Compares method declarations: signature and return types.
 */
public static boolean compareDeclarations(final Method first,final Method second){
  if (first.getReturnType() != second.getReturnType()) {
    return false;
  }
  return compareSignatures(first,second);
}
