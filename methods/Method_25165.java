/** 
 * Returns the value for the given variable.  {@code element} must come from a call to {@link LocalVariableNode#getElement()} or {@link org.checkerframework.javacutil.TreeUtils#elementFromDeclaration} ({@link org.checkerframework.dataflow.cfg.node.VariableDeclarationNode#getTree()}).
 */
@Nullable private V getInformation(Element element){
  checkElementType(element);
  return contents.get(checkNotNull(element));
}
