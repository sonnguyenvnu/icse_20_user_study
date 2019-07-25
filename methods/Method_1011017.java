public void refactor(final RefactoringContext refactoringContext){
  for (  ChangeMethodSignatureRefactoring ref : ListSequence.fromList(((List<ChangeMethodSignatureRefactoring>)refactoringContext.getParameter("myRefactorings")))) {
    ref.doRefactoring();
  }
}
