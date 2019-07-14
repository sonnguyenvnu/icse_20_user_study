private boolean isNoMethodName(Node name){
  if (name instanceof ASTName && (name.jjtGetParent() instanceof ASTPrimaryPrefix || name.jjtGetParent() instanceof ASTPrimarySuffix)) {
    Node prefixOrSuffix=name.jjtGetParent();
    if (prefixOrSuffix.jjtGetParent().jjtGetNumChildren() > 1 + prefixOrSuffix.jjtGetChildIndex()) {
      Node next=prefixOrSuffix.jjtGetParent().jjtGetChild(prefixOrSuffix.jjtGetChildIndex() + 1);
      if (next instanceof ASTPrimarySuffix) {
        return !((ASTPrimarySuffix)next).isArguments();
      }
    }
  }
  return true;
}
