private void checkUnaryDescendent(Node node,Object data){
  boolean match=false;
  if (node.jjtGetNumChildren() == 1) {
    Node child=node.jjtGetChild(0);
    if (child instanceof ASTUnaryExpression || child instanceof ASTUnaryExpressionNotPlusMinus) {
      match=true;
    }
 else     if (child instanceof ASTPrimaryExpression) {
      Node primaryExpression=child;
      while (true) {
        if (primaryExpression.jjtGetNumChildren() == 1 && primaryExpression.jjtGetChild(0) instanceof ASTPrimaryPrefix && primaryExpression.jjtGetChild(0).jjtGetNumChildren() == 1 && primaryExpression.jjtGetChild(0).jjtGetChild(0) instanceof ASTExpression && primaryExpression.jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 1) {
          Node candidate=primaryExpression.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
          if (candidate instanceof ASTUnaryExpression || candidate instanceof ASTUnaryExpressionNotPlusMinus) {
            match=true;
            break;
          }
 else           if (candidate instanceof ASTPrimaryExpression) {
            primaryExpression=candidate;
            continue;
          }
 else {
            break;
          }
        }
 else {
          break;
        }
      }
    }
  }
  if (match) {
    addViolation(data,node);
  }
}
