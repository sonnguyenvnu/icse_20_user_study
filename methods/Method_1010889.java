@Nullable @Override public PsiElement resolve(){
  ApplicationManager.getApplication().assertReadAccessAllowed();
  return new ModelAccessHelper(ProjectHelper.getModelAccess(myParent.getProject())).runReadAction(new Computable<PsiElement>(){
    @Override public PsiElement compute(){
      return MPSPsiProvider.getInstance(myParent.getProject()).getPsi(myTarget);
    }
  }
);
}
