/** 
 * Fetches line number of the node in its CompilationUnit.
 * @param node
 * @return
 */
public static int getLineNumber(ASTNode node){
  return ((CompilationUnit)node.getRoot()).getLineNumber(node.getStartPosition());
}
