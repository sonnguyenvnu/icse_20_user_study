/** 
 * @param element candidate element.
 * @param state   current state of resolver.
 * @return false to stop processing.
 */
@Override public boolean execute(@NotNull PsiElement element,@NotNull ResolveState state){
  boolean keepProcessing=true;
  if (element instanceof Addition || element instanceof And) {
    keepProcessing=executeNonDeclaringScopeInfix((Infix)element,state);
  }
 else   if (element instanceof ElixirAccessExpression || element instanceof ElixirAssociations || element instanceof ElixirAssociationsBase || element instanceof ElixirBitString || element instanceof ElixirList || element instanceof ElixirMapConstructionArguments || element instanceof ElixirMultipleAliases || element instanceof ElixirNoParenthesesArguments || element instanceof ElixirNoParenthesesOneArgument || element instanceof ElixirParenthesesArguments || element instanceof ElixirParentheticalStab || element instanceof ElixirStab || element instanceof ElixirStabBody || element instanceof ElixirTuple) {
    keepProcessing=execute(element.getChildren(),state);
  }
 else   if (element instanceof ElixirContainerAssociationOperation) {
    keepProcessing=execute((ElixirContainerAssociationOperation)element,state);
  }
 else   if (element instanceof ElixirMapArguments) {
    keepProcessing=execute((ElixirMapArguments)element,state);
  }
 else   if (element instanceof ElixirMapOperation) {
    keepProcessing=execute((ElixirMapOperation)element,state);
  }
 else   if (element instanceof ElixirMatchedWhenOperation) {
    keepProcessing=execute((ElixirMatchedWhenOperation)element,state);
  }
 else   if (element instanceof ElixirStabOperation) {
    keepProcessing=execute((ElixirStabOperation)element,state);
  }
 else   if (element instanceof ElixirStabNoParenthesesSignature) {
    keepProcessing=execute((ElixirStabNoParenthesesSignature)element,state);
  }
 else   if (element instanceof ElixirStabParenthesesSignature) {
    keepProcessing=execute((ElixirStabParenthesesSignature)element,state);
  }
 else   if (element instanceof ElixirStructOperation) {
    keepProcessing=execute((ElixirStructOperation)element,state);
  }
 else   if (element instanceof ElixirVariable) {
    keepProcessing=executeOnVariable((PsiNamedElement)element,state);
  }
 else   if (element instanceof In) {
    keepProcessing=execute((In)element,state);
  }
 else   if (element instanceof InMatch) {
    keepProcessing=execute((InMatch)element,state);
  }
 else   if (element instanceof Match) {
    keepProcessing=execute((Match)element,state);
  }
 else   if (element instanceof And || element instanceof Pipe || element instanceof Two) {
    keepProcessing=execute((Infix)element,state);
  }
 else   if (element instanceof Type) {
    keepProcessing=execute((Type)element,state);
  }
 else   if (element instanceof UnaryNonNumericOperation) {
    keepProcessing=execute((UnaryNonNumericOperation)element,state);
  }
 else   if (element instanceof UnqualifiedNoArgumentsCall) {
    keepProcessing=executeOnMaybeVariable((UnqualifiedNoArgumentsCall)element,state);
  }
 else   if (element instanceof Call) {
    keepProcessing=execute((Call)element,state);
  }
 else   if (element instanceof QualifiedMultipleAliases) {
    keepProcessing=execute((QualifiedMultipleAliases)element,state);
  }
 else   if (element instanceof PsiFile) {
    keepProcessing=false;
  }
 else   if (element instanceof QuotableKeywordList) {
    keepProcessing=execute((QuotableKeywordList)element,state);
  }
 else {
    if (!(element instanceof AtNonNumericOperation || element instanceof AtUnqualifiedBracketOperation || element instanceof Heredoc || element instanceof BracketOperation || element instanceof ElixirAnonymousFunction || element instanceof ElixirAtom || element instanceof ElixirAtomKeyword || element instanceof ElixirCharToken || element instanceof ElixirDecimalFloat || element instanceof ElixirEmptyParentheses || element instanceof ElixirEndOfExpression || element instanceof ElixirNoParenthesesManyStrictNoParenthesesExpression || element instanceof LeafPsiElement || element instanceof Line || element instanceof PsiErrorElement || element instanceof PsiWhiteSpace || element instanceof QualifiableAlias || element instanceof QualifiedBracketOperation || element instanceof UnqualifiedBracketOperation || element instanceof WholeNumber || element.getNode().getElementType().equals(DUMMY_BLOCK))) {
      Logger.error(Callable.class,"Don't know how to resolve variable in match",element);
    }
  }
  return keepProcessing;
}
