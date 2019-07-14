private String debugHiddenBefore(AST ast){
  if (!(ast instanceof antlr.CommonASTWithHiddenTokens)) {
    return "";
  }
  antlr.CommonHiddenStreamToken parent=((antlr.CommonASTWithHiddenTokens)ast).getHiddenBefore();
  if (parent == null) {
    return "";
  }
  antlr.CommonHiddenStreamToken child=null;
  do {
    child=parent;
    parent=child.getHiddenBefore();
  }
 while (parent != null);
  return debugHiddenTokens(child);
}
