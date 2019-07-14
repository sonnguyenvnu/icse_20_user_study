/** 
 * Returns whether the variable is mentioned within the statement or not.
 */
private static boolean hasReferencesIn(ASTBlockStatement block,String varName){
  for (  ASTName name : block.findDescendantsOfType(ASTName.class,true)) {
    if (isReference(varName,name.getImage())) {
      return true;
    }
  }
  return false;
}
