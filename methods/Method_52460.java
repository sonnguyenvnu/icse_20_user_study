/** 
 * Sets the scope of a node and adjusts the scope stack accordingly. The scope on top of the stack is set as the parent of the given scope, which is then also stored on the scope stack.
 * @param newScope the scope for the node.
 * @param node the AST node for which the scope is to be set.
 * @throws java.util.EmptyStackException if the scope stack is empty.
 */
private void addScope(Scope newScope,JavaNode node){
  newScope.setParent(scopes.peek());
  scopes.push(newScope);
  node.setScope(newScope);
}
