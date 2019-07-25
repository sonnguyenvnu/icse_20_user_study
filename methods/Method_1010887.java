private boolean interesting(PsiElement elem){
  if (elem instanceof PsiClass || elem instanceof PsiMethod || elem instanceof PsiField || elem instanceof PsiParameterList || elem instanceof PsiParameter || elem instanceof PsiReferenceList || elem instanceof PsiModifierList || elem instanceof PsiModifier || elem instanceof PsiTypeParameterList || elem instanceof PsiTypeParameter) {
    return true;
  }
  return false;
}
