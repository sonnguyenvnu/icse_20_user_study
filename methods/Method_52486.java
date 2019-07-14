/** 
 * Note that an array of primitive types (int[]) is a reference type.
 */
public boolean isReferenceType(){
  return !isTypeInferred() && getAccessNodeParent().getFirstChildOfType(ASTType.class).jjtGetChild(0) instanceof ASTReferenceType;
}
