/** 
 * Annotates the specified PSI element. It is guaranteed to be executed in non-reentrant fashion. I.e there will be no call of this method for this instance before previous call get completed. Multiple instances of the annotator might exist simultaneously, though.
 * @param element to annotate.
 * @param holder  the container which receives annotations created by the plugin.
 */
@Override public void annotate(@NotNull final PsiElement element,@NotNull final AnnotationHolder holder){
  if (!element.getContainingFile().getViewProvider().getLanguages().contains(org.elixir_lang.eex.Language.INSTANCE)) {
    element.accept(new PsiRecursiveElementVisitor(){
      @Override public void visitElement(      @NotNull final PsiElement element){
        if (element instanceof AtNonNumericOperation) {
          visitMaybeUsage((AtNonNumericOperation)element);
        }
 else         if (element instanceof AtUnqualifiedNoParenthesesCall) {
          visitDeclaration((AtUnqualifiedNoParenthesesCall)element);
        }
      }
      private void visitDeclaration(      @NotNull final AtUnqualifiedNoParenthesesCall atUnqualifiedNoParenthesesCall){
        ElixirAtIdentifier atIdentifier=atUnqualifiedNoParenthesesCall.getAtIdentifier();
        TextRange textRange=atIdentifier.getTextRange();
        String identifier=identifierName(atIdentifier);
        if (Companion.isCallbackName(identifier)) {
          highlight(textRange,holder,ElixirSyntaxHighlighter.MODULE_ATTRIBUTE);
          highlightCallback(atUnqualifiedNoParenthesesCall,holder);
        }
 else         if (Companion.isDocumentationName(identifier)) {
          highlight(textRange,holder,ElixirSyntaxHighlighter.DOCUMENTATION_MODULE_ATTRIBUTE);
          highlightDocumentationText(atUnqualifiedNoParenthesesCall,holder);
        }
 else         if (Companion.isTypeName(identifier)) {
          highlight(textRange,holder,ElixirSyntaxHighlighter.MODULE_ATTRIBUTE);
          highlightType(atUnqualifiedNoParenthesesCall,holder);
        }
 else         if (Companion.isSpecificationName(identifier)) {
          highlight(textRange,holder,ElixirSyntaxHighlighter.MODULE_ATTRIBUTE);
          highlightSpecification(atUnqualifiedNoParenthesesCall,holder);
        }
 else {
          highlight(textRange,holder,ElixirSyntaxHighlighter.MODULE_ATTRIBUTE);
        }
      }
      private void visitMaybeUsage(      @NotNull final AtNonNumericOperation element){
        PsiElement operand=element.operand();
        if (operand != null && !(operand instanceof ElixirAccessExpression)) {
          visitUsage(element);
        }
      }
      private void visitUsage(      @NotNull final AtNonNumericOperation atNonNumericOperation){
        highlight(atNonNumericOperation.getTextRange(),holder,ElixirSyntaxHighlighter.MODULE_ATTRIBUTE);
        if (!Companion.isNonReferencing(atNonNumericOperation)) {
          PsiReference reference=atNonNumericOperation.getReference();
          if (reference != null && reference.resolve() == null) {
            holder.createErrorAnnotation(atNonNumericOperation,"Unresolved module attribute");
          }
        }
      }
    }
);
  }
}
