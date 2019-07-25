public void refactor(final RefactoringContext refactoringContext){
  SNode method=refactoringContext.getSelectedNode();
  new SafeDeleteMethod(refactoringContext.getCurrentScope(),method).doRefactor();
}
