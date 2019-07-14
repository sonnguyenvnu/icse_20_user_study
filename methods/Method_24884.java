/** 
 * Tells whether an AST has any children or not.
 * @return true iff the AST has at least one child
 */
static private boolean hasChildren(final AST ast){
  return (ast.getFirstChild() != null);
}
