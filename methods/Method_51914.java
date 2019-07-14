/** 
 * Returns true if this node declares a field.
 */
public boolean isField(){
  return getNthParent(2) instanceof ASTFieldDeclaration;
}
