static LookupElement createCompletionResult(PsiParameter parameter){
  return LookupElementBuilder.create(new StringJoiner(" ").add(parameter.getType().getPresentableText()).add(parameter.getName()).toString());
}
