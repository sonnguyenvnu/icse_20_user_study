/** 
 * Returns an iterator over the ids of the variables declared in this statement.
 */
@Override public Iterator<ASTVariableDeclaratorId> iterator(){
  return ASTVariableDeclarator.iterateIds(this);
}
