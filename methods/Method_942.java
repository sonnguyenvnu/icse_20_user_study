/** 
 * Check if reference is inside macro.
 * @param node node
 * @return true/false
 */
private boolean checkMacro(Node node){
  List<ASTDirective> directiveParents=node.getParentsOfType(ASTDirective.class);
  for (  ASTDirective directiveParent : directiveParents) {
    if (MACRO_NAME.equals(directiveParent.getDirectiveName())) {
      return true;
    }
  }
  return false;
}
