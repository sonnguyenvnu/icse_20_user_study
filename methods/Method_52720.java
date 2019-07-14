private void dump(EcmascriptNode<?> node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  image=StringUtil.escapeWhitespace(image);
  List<String> extras=new ArrayList<>();
  if (node instanceof DestructuringNode) {
    if (((DestructuringNode)node).isDestructuring()) {
      extras.add("destructuring");
    }
  }
  if (node instanceof ASTArrayComprehension) {
    if (((ASTArrayComprehension)node).hasFilter()) {
      extras.add("has filter");
    }
  }
 else   if (node instanceof ASTBreakStatement) {
    if (((ASTBreakStatement)node).hasLabel()) {
      extras.add("has label");
    }
  }
 else   if (node instanceof ASTCatchClause) {
    if (((ASTCatchClause)node).isIf()) {
      extras.add("if");
    }
  }
 else   if (node instanceof ASTContinueStatement) {
    if (((ASTContinueStatement)node).hasLabel()) {
      extras.add("has label");
    }
  }
 else   if (node instanceof ASTExpressionStatement) {
    if (((ASTExpressionStatement)node).hasResult()) {
      extras.add("has result");
    }
  }
 else   if (node instanceof ASTForInLoop) {
    if (((ASTForInLoop)node).isForEach()) {
      extras.add("for each");
    }
  }
 else   if (node instanceof ASTFunctionCall) {
    if (((ASTFunctionCall)node).hasArguments()) {
      extras.add("has arguments");
    }
  }
 else   if (node instanceof ASTFunctionNode) {
    if (((ASTFunctionNode)node).isClosure()) {
      extras.add("closure");
    }
    if (((ASTFunctionNode)node).isGetter()) {
      extras.add("getter");
    }
    if (((ASTFunctionNode)node).isSetter()) {
      extras.add("setter");
    }
  }
 else   if (node instanceof ASTIfStatement) {
    if (((ASTIfStatement)node).hasElse()) {
      extras.add("has else");
    }
  }
 else   if (node instanceof ASTKeywordLiteral) {
    if (((ASTKeywordLiteral)node).isBoolean()) {
      extras.add("boolean");
    }
  }
 else   if (node instanceof ASTLetNode) {
    if (((ASTLetNode)node).hasBody()) {
      extras.add("has body");
    }
  }
 else   if (node instanceof ASTName) {
    if (((ASTName)node).isLocalName()) {
      extras.add("local");
    }
    if (((ASTName)node).isGlobalName()) {
      extras.add("global");
    }
  }
 else   if (node instanceof ASTNewExpression) {
    if (((ASTNewExpression)node).hasArguments()) {
      extras.add("has arguments");
    }
    if (((ASTNewExpression)node).hasInitializer()) {
      extras.add("has initializer");
    }
  }
 else   if (node instanceof ASTNumberLiteral) {
    extras.add("Number=" + ((ASTNumberLiteral)node).getNumber());
    extras.add("NormalizedImage=" + ((ASTNumberLiteral)node).getNormalizedImage());
  }
 else   if (node instanceof ASTObjectProperty) {
    if (((ASTObjectProperty)node).isGetter()) {
      extras.add("getter");
    }
    if (((ASTObjectProperty)node).isSetter()) {
      extras.add("setter");
    }
  }
 else   if (node instanceof ASTRegExpLiteral) {
    extras.add("Flags=" + ((ASTRegExpLiteral)node).getFlags());
  }
 else   if (node instanceof ASTReturnStatement) {
    if (((ASTReturnStatement)node).hasResult()) {
      extras.add("has result");
    }
  }
 else   if (node instanceof ASTStringLiteral) {
    if (((ASTStringLiteral)node).isSingleQuoted()) {
      extras.add("single quoted");
    }
    if (((ASTStringLiteral)node).isDoubleQuoted()) {
      extras.add("double quoted");
    }
  }
 else   if (node instanceof ASTSwitchCase) {
    if (((ASTSwitchCase)node).isDefault()) {
      extras.add("default");
    }
  }
 else   if (node instanceof ASTTryStatement) {
    if (((ASTTryStatement)node).hasCatch()) {
      extras.add("catch");
    }
    if (((ASTTryStatement)node).hasFinally()) {
      extras.add("finally");
    }
  }
 else   if (node instanceof ASTUnaryExpression) {
    if (((ASTUnaryExpression)node).isPrefix()) {
      extras.add("prefix");
    }
    if (((ASTUnaryExpression)node).isPostfix()) {
      extras.add("postfix");
    }
  }
  if (node.hasSideEffects()) {
    extras.add("has side effects");
  }
  if (image != null || !extras.isEmpty()) {
    writer.print(':');
    if (image != null) {
      writer.print(image);
    }
    for (    String extra : extras) {
      writer.print('(');
      writer.print(extra);
      writer.print(')');
    }
  }
  writer.println();
}
