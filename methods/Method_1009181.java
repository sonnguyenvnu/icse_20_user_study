@Override public void annotate(@NotNull PsiElement psiElement,@NotNull com.intellij.lang.annotation.AnnotationHolder annotationHolder){
  if (psiElement instanceof ErlangAtom) {
    ErlangAtom erlangAtom=(ErlangAtom)psiElement;
    String name=erlangAtom.getName();
    if (name.startsWith(ELIXIR_ALIAS_PREFIX)) {
      Project project=psiElement.getProject();
      Collection<NamedElement> namedElementCollection=StubIndex.getElements(ModularName.KEY,name,project,GlobalSearchScope.allScope(project),NamedElement.class);
      if (namedElementCollection.size() > 0) {
        TextRange textRange=psiElement.getTextRange();
        String unprefixedName=name.substring(ELIXIR_ALIAS_PREFIX.length(),name.length());
        Annotation annotation=annotationHolder.createInfoAnnotation(textRange,"Resolves to Elixir Module " + unprefixedName);
        annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
      }
 else {
        TextRange textRange=psiElement.getTextRange();
        annotationHolder.createErrorAnnotation(textRange,"Unresolved Elixir Module");
      }
    }
  }
}
