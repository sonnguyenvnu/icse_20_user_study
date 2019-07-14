private String debugHiddenAfter(AST ast){
  return (ast instanceof antlr.CommonASTWithHiddenTokens) ? debugHiddenTokens(((antlr.CommonASTWithHiddenTokens)ast).getHiddenAfter()) : "";
}
