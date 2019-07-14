/** 
 * Print the children of the given AST
 * @param ast The AST to print
 * @returns true iff anything was printed
 */
private boolean printChildren(final AST ast) throws SketchException {
  boolean ret=false;
  AST child=ast.getFirstChild();
  while (child != null) {
    ret=true;
    print(child);
    child=child.getNextSibling();
  }
  return ret;
}
