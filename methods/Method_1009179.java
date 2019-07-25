private void highlight(@NotNull final ElixirMapArguments mapArguments,@NotNull AnnotationHolder annotationHolder,@NotNull final TextAttributesKey textAttributesKey){
  ASTNode[] braces=mapArguments.getNode().getChildren(BRACES_TOKEN_SET);
  for (  ASTNode brace : braces) {
    highlight(brace.getTextRange(),annotationHolder,textAttributesKey);
  }
}
