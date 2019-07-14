private void handleIf(final AbstractVmNode node,final Object data){
  final ASTBlock block=node.getFirstChildOfType(ASTBlock.class);
  if (block.jjtGetNumChildren() == 0) {
    addViolation(data,node);
  }
 else   if (block.jjtGetNumChildren() == 1 && block.jjtGetChild(0) instanceof ASTText && StringUtils.isBlank(((AbstractVmNode)block.jjtGetChild(0)).getFirstToken().toString())) {
    addViolation(data,node);
  }
}
