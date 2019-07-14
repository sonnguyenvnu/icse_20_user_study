/** 
 * Returns true if this formal parameter is of an array type. This includes varargs parameters.
 */
@Override @Deprecated public boolean isArray(){
  return isVarargs() || getTypeNode() != null && getTypeNode().isArray() || getVariableDeclaratorId().isArray();
}
