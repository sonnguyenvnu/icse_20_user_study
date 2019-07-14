private static String getMethodAndArgument(Tree origArg,VisitorState state){
  String argSource=state.getSourceForNode(origArg);
  Tree arg=ASTHelpers.stripParentheses(origArg);
  if (arg.getKind() != Tree.Kind.STRING_LITERAL) {
    return String.format("onPattern(%s)",argSource);
  }
  String constValue=ASTHelpers.constValue(arg,String.class);
  if (constValue == null) {
    return String.format("onPattern(%s)",argSource);
  }
  Optional<String> regexAsLiteral=convertRegexToLiteral(constValue);
  if (!regexAsLiteral.isPresent()) {
    return String.format("onPattern(%s)",argSource);
  }
  String escaped=SourceCodeEscapers.javaCharEscaper().escape(regexAsLiteral.get());
  if (regexAsLiteral.get().length() == 1) {
    return String.format("on('%s')",escaped);
  }
  return String.format("on(\"%s\")",escaped);
}
