/** 
 * Returns an iterator over the ids of the fields declared in this statement.
 */
@Override public Iterator<ASTVariableDeclaratorId> iterator(){
  return ASTVariableDeclarator.iterateIds(this);
}
