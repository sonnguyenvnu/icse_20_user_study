@Override public void invoke(@NotNull final Project project,@NotNull PsiElement[] elements,final DataContext dataContext){
  final MPSProject mpsProject=project.getComponent(MPSProject.class);
  final SRepository repository=mpsProject.getRepository();
  ModelAccess modelAccess=repository.getModelAccess();
  final SNode node=MPSCommonDataKeys.NODE.getData(dataContext);
  modelAccess.runReadInEDT(new Runnable(){
    @Override public void run(){
      if (!SNodeUtil.isAccessible(node,repository)) {
        return;
      }
      for (      RenameRefactoringContributorEP ep : RenameRefactoringContributorEP.EP_NAME.getExtensions()) {
        RenameRefactoringContributor contributor=ep.getContribitor();
        if (contributor.isAvailableFor(node)) {
          contributor.invoke(project,node);
          return;
        }
      }
    }
  }
);
}
