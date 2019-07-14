/** 
 * @return Stream of resolved elements from the given element or an empty stream if nothing found.
 */
private static Stream<PsiElement> resolve(PsiElement sourceElement){
  return Optional.of(sourceElement).filter(PsiIdentifier.class::isInstance).map(element -> PsiTreeUtil.getParentOfType(element,PsiJavaCodeReferenceElement.class)).map(PsiElement::getReferences).map(psiReferences -> Stream.of(psiReferences).map(PsiReference::resolve).filter(Objects::nonNull)).orElse(Stream.empty());
}
