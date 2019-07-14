/** 
 * Returns true if the declared variable has an array type.
 */
public boolean hasArrayType(){
  return arrayDepth > 0 || !isTypeInferred() && getTypeNode().isArrayType();
}
