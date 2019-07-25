public PsiElement resolve(){
  ApplicationManager.getApplication().assertReadAccessAllowed();
  return new ModelAccessHelper(ProjectHelper.getModelAccess(getProject())).runReadAction(new Computable<PsiElement>(){
    @Override public PsiElement compute(){
      if (model != null && nodeId != null) {
        return MPSPsiProvider.getInstance(getProject()).getPsi(new SNodePointer(model,nodeId));
      }
 else {
        return null;
      }
    }
  }
);
}
