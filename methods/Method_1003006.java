@Override public PsiElement copy(){
  final PsiElement myPsiMethod=getOrCreateMyPsiMethod();
  return null == myPsiMethod ? null : myPsiMethod.copy();
}
