/** 
 * Annotates the specified PSI element. It is guaranteed to be executed in non-reentrant fashion. I.e there will be no call of this method for this instance before previous call get completed. Multiple instances of the annotator might exist simultaneously, though.
 * @param element to annotate.
 * @param holder  the container which receives annotations created by the plugin.
 */
@Override public void annotate(@NotNull final PsiElement element,@NotNull final AnnotationHolder holder){
  element.accept(new PsiRecursiveElementVisitor(){
    @Override public void visitElement(    PsiElement element){
      if (element instanceof ElixirKeywordKey) {
        visitKeywordKey((ElixirKeywordKey)element);
      }
    }
    private void visitKeywordKey(    ElixirKeywordKey keywordKey){
      PsiElement child=keywordKey.getFirstChild();
      if (child instanceof LeafPsiElement) {
        TextRange keywordKeyTextRange=keywordKey.getTextRange();
        TextRange atomTextRange=new TextRange(keywordKeyTextRange.getStartOffset(),keywordKeyTextRange.getEndOffset() + 1);
        highlight(atomTextRange,holder,ElixirSyntaxHighlighter.ATOM);
      }
    }
  }
);
}
