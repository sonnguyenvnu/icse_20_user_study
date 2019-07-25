@Override protected void process(List<ClassMember> classMembers){
  for (  ClassMember classMember : classMembers) {
    final PsiElementClassMember elementClassMember=(PsiElementClassMember)classMember;
    PsiField psiField=(PsiField)elementClassMember.getPsiElement();
    PsiMethod psiMethod=PropertyUtil.findPropertyGetter(psiField.getContainingClass(),psiField.getName(),false,false);
    if (null != psiMethod) {
      PsiModifierList modifierList=psiField.getModifierList();
      if (null != modifierList) {
        PsiAnnotation psiAnnotation=modifierList.addAnnotation(Getter.class.getName());
        psiMethod.delete();
      }
    }
  }
}
