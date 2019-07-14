static Modifier[] extractModifiers(PsiField psiField){
  PsiModifierList modifierList=psiField.getModifierList();
  return modifierList == null ? EMPTY : extractModifiersInternal(modifierList).toArray(EMPTY);
}
