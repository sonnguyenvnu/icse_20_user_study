/** 
 * Annotates the specified PSI element. It is guaranteed to be executed in non-reentrant fashion. I.e there will be no call of this method for this instance before previous call get completed. Multiple instances of the annotator might exist simultaneously, though.
 * @param element to annotate.
 * @param holder  the container which receives annotations created by the plugin.
 */
@Override public void annotate(@NotNull final PsiElement element,@NotNull final AnnotationHolder holder){
  element.accept(new PsiRecursiveElementVisitor(){
    @Override public void visitElement(    PsiElement element){
      if (element instanceof org.elixir_lang.psi.EscapeSequence) {
        visitEscapeSequence((org.elixir_lang.psi.EscapeSequence)element);
      }
    }
    private void visitEscapeSequence(    org.elixir_lang.psi.EscapeSequence escapeSequence){
      PsiElement parent=escapeSequence.getParent();
      if (!(parent instanceof org.elixir_lang.psi.EscapeSequence)) {
        highlight(escapeSequence,holder,ElixirSyntaxHighlighter.VALID_ESCAPE_SEQUENCE);
      }
    }
  }
);
}
