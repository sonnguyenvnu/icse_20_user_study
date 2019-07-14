/** 
 * Inlines the syntax tree representing the specified type. 
 */
public JCExpression inlineAsTree(Type type){
  return INLINE_AS_TREE.visit(type,this);
}
