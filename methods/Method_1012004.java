public void execute(final RefactoringContext refactoringContext){
  ThreadUtils.assertEDT();
  myLog.assertLog(refactoringContext.getSelectedProject().getModelAccess().canRead(),"Read access");
  boolean success=refactoringContext.getRefactoring().init(refactoringContext);
  if (success) {
    findUsagesAndRun(refactoringContext);
  }
}
