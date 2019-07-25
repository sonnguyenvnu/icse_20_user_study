@Override public void collect(@NotNull TwigFileVariableCollectorParameter parameter,@NotNull Map<String,Set<String>> variables){
  ASTNode macroStatement=TreeUtil.findParent(parameter.getElement().getNode(),TwigElementTypes.MACRO_STATEMENT);
  if (macroStatement == null) {
    return;
  }
  PsiElement psiElement=macroStatement.getPsi();
  if (psiElement == null) {
    return;
  }
  PsiElement marcoTag=psiElement.getFirstChild();
  if (marcoTag == null) {
    return;
  }
  Pair<String,String> pair=TwigUtil.getTwigMacroNameAndParameter(marcoTag);
  if (pair == null || pair.getSecond() == null) {
    return;
  }
  String args=StringUtils.stripStart(pair.getSecond(),"( ");
  args=StringUtils.stripEnd(args,") ");
  for (  String s : args.split("\\s*,\\s*")) {
    variables.put(s,Collections.emptySet());
  }
}
