/** 
 * Method counts ONLY public methods.
 */
@Override public Object visit(ASTMethodDeclarator node,Object data){
  return this.getTallyOnAccessType((AccessNode)node.jjtGetParent());
}
