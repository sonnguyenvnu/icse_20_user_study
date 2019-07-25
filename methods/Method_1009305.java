/** 
 * @param element candidate element.
 * @param state   current state of resolver.
 * @return false to stop processing.
 */
@Override public boolean execute(@NotNull final PsiElement element,@NotNull final ResolveState state){
  boolean keepProcessing=true;
  if (element instanceof QualifiableAlias) {
    QualifiableAlias qualifiableAlias=(QualifiableAlias)element;
    String qualifiableAliasFullyQualifiedName=qualifiableAlias.fullyQualifiedName();
    if (qualifiableAlias.isModuleName() && qualifiableAliasFullyQualifiedName != null && qualifiableAliasFullyQualifiedName.equals(usage.fullyQualifiedName())) {
      declaration=qualifiableAlias;
      keepProcessing=false;
    }
  }
  return keepProcessing;
}
