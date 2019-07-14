private int processNode(Node sn){
  int anticipatedLength=0;
  if (sn != null) {
    ASTPrimaryPrefix xn=sn.getFirstDescendantOfType(ASTPrimaryPrefix.class);
    if (xn != null) {
      if (xn.jjtGetNumChildren() != 0 && xn.jjtGetChild(0) instanceof ASTLiteral) {
        ASTLiteral literal=(ASTLiteral)xn.jjtGetChild(0);
        String str=xn.jjtGetChild(0).getImage();
        if (str != null) {
          if (literal.isStringLiteral()) {
            anticipatedLength+=str.length() - 2;
          }
 else           if (literal.isCharLiteral()) {
            anticipatedLength+=1;
          }
 else           if (literal.isIntLiteral()) {
            Node parentNode=literal.jjtGetParent().jjtGetParent().jjtGetParent();
            if (parentNode instanceof ASTCastExpression && ((ASTCastExpression)parentNode).getType() == char.class) {
              anticipatedLength+=1;
            }
 else {
              anticipatedLength+=String.valueOf(literal.getValueAsLong()).length();
            }
          }
 else {
            anticipatedLength+=str.length();
          }
        }
      }
    }
  }
  return anticipatedLength;
}
