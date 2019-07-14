private static List<Modifier> extractModifiersInternal(PsiModifierList modifierList){
  List<Modifier> modifiers=new ArrayList<>();
  PsiElement[] children=modifierList.getChildren();
  for (  PsiElement child : children) {
    if (child instanceof PsiModifier || child instanceof PsiKeyword) {
      modifiers.add(psiModifierToModifier(child));
    }
  }
  return modifiers;
}
