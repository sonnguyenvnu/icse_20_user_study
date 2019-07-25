@Override public void execute(@NotNull String pattern){
  MPSProject project=MPSCommonDataKeys.MPS_PROJECT.getData(DataManager.getInstance().getDataContext((Component)_context.getEditorContext().getEditorComponent()));
  RefactoringContext refactoringContext=RefactoringContext.createRefactoringContext(myRefactoring,Collections.emptyList(),Collections.emptyList(),_context.getNode(),project);
  RefactoringAccess.getInstance().getRefactoringFacade().execute(refactoringContext);
}
