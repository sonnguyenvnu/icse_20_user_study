/** 
 * Returns true if this declarator id declares a resource in a try-with-resources statement.
 */
public boolean isResourceDeclaration(){
  return jjtGetParent() instanceof ASTResource;
}
