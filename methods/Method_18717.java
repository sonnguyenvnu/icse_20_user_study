@VisibleForTesting static boolean hasAnnotation(PsiModifierListOwner modifierListOwner,Predicate<String> nameFilter){
  return Optional.of(modifierListOwner).map(PsiModifierListOwner::getAnnotations).map(annotations -> Stream.of(annotations).map(PsiAnnotation::getQualifiedName).filter(Objects::nonNull).anyMatch(nameFilter)).orElse(false);
}
