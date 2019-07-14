private boolean matchName(ASTPrimaryExpression ape,String name){
  if (ape.jjtGetNumChildren() == 1 && ape.jjtGetChild(0) instanceof ASTPrimaryPrefix) {
    ASTPrimaryPrefix pp=(ASTPrimaryPrefix)ape.jjtGetChild(0);
    String name2=getNameFromPrimaryPrefix(pp);
    if (name2 != null && name2.equals(name)) {
      return true;
    }
  }
  return false;
}
