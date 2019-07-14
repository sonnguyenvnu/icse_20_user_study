@Override public boolean isTargetMethod(JavaNameOccurrence occ){
  if (occ.getNameForWhichThisIsAQualifier() != null && occ.getNameForWhichThisIsAQualifier().getImage().indexOf("trim") != -1) {
    Node pExpression=occ.getLocation().jjtGetParent().jjtGetParent();
    if (pExpression.jjtGetNumChildren() > 2 && "length".equals(pExpression.jjtGetChild(2).getImage())) {
      return true;
    }
  }
  return false;
}
